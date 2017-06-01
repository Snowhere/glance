package service;

import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfplugin.mail.MailKit;
import model.Code;
import model.User;
import model.UserAuth;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * User
 *
 * @author STH
 * @create 2017-05-26
 **/
public class UserService {
    //缓存用户id列表
    public static List<Long> userIds = new LinkedList<>();

    static {
        List<User> users = User.dao.find("select id from `user`");
        for (User user : users) {
            userIds.add(user.getLong("id"));
        }
    }

    /**
     * 创建新用户
     *
     * @param model
     * @return
     */
    public boolean createUser(User model) {
        boolean flag = model.save();
        if (flag) {
            userIds.add(model.getLong("id"));
        }
        return flag;
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Before(Tx.class)
    public boolean register(String nickName, String username, String password,String type) {
        UserAuth userAuth = new UserAuth();
        User user = new User();
        Date now = new Date();

        userAuth.set("identifier", username);
        userAuth.set("credential", HashKit.md5(password));
        userAuth.set("create_time", now);
        userAuth.set("type", type);

        user.set("nickname", nickName);
        user.set("create_time", now);

        if (user.save()) {
            userAuth.set("user_id", user.getLong("id"));
            return userAuth.save();
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
        UserAuth userAuth = UserAuth.dao.getUser( username, HashKit.md5(password));
            if (userAuth != null) {
            User user = User.dao.findById(userAuth.getLong("user_id"));
            if (user != null) {
                session.setAttribute("user", user);
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户数量
     *
     * @return
     */
    public int getUserNumber() {
        return userIds.size();
    }

    /**
     * 发送验证邮件
     *
     * @param address
     */
    public boolean email(String address) {
        MailKit.send(address, null, "test", "content test");
        return true;
    }

    /**
     * 个人信息
     * @param userId
     * @return
     */
    public Map<String, Object> info(Long userId) {
        Map<String, Object> info = new HashMap<>();
        User user = User.dao.findById(userId);
        List<UserAuth> userAuths = UserAuth.dao.findByUserId(userId);
        List<Code> codes = Code.dao.findBySubmitter(userId);
        return  info;
    }

}
