package service;

import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.redis.Redis;
import com.jfplugin.mail.MailKit;
import model.Code;
import model.User;
import model.UserAuth;
import util.CacheName;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User
 *
 * @author STH
 * @create 2017-05-26
 **/
public class UserService {

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Before(Tx.class)
    public boolean register(String nickName, String username, String password, String type) {
        UserAuth userAuth = new UserAuth();
        User user = new User();
        Date now = new Date();

        userAuth.set("identifier", username);
        userAuth.set("credential", HashKit.md5(password));
        userAuth.set("create_time", now);
        userAuth.set("type", type);

        user.set("name", nickName);
        user.set("create_time", now);

        if (user.save()) {
            userAuth.set("user_id", user.getLong("id"));
            if (userAuth.save()){
                //缓存用户名
                if (type.equals(UserAuth.TYPE_LOCAL)) {
                    Redis.use().rpush(CacheName.USERS, username);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 登录
     *
     * @param session
     * @param username
     * @param password
     * @return
     */
    public boolean login(HttpSession session, String username, String password) {
        User user = UserAuth.dao.getUser(username, HashKit.md5(password));
        if (user != null) {
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    /**
     * 获取用户数量
     *
     * @return
     */
    public long getUserNumber() {
        return Redis.use().llen(CacheName.USERS);
    }

    /**
     * 发送验证邮件
     *
     * @param address
     */
    public boolean email(String address) {
        MailKit.send(address, null, "test", "content test");
        System.out.println("send email");
        return true;
    }

    /**
     * 个人信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> info(Long userId) {
        Map<String, Object> info = new HashMap<>();
        User user = User.dao.findById(userId);
        List<UserAuth> userAuths = UserAuth.dao.findByUserId(userId);
        List<Code> codes = Code.dao.findBySubmitter(userId);
        return info;
    }
    /**
     * 检查用户名是否唯一
     * ajax
     */
    public boolean uniqueUserName(String username) {
        List<String> usernames = Redis.use().lrange(CacheName.USERS,0,-1);
        return usernames == null || !usernames.contains(username) ;
    }
}
