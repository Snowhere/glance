package service;

import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfplugin.mail.MailKit;
import model.User;
import model.UserAuth;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        List<User> users = User.model.find("select id from `user`");
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
    public boolean register(String nickName, String username, String password) {
        UserAuth userAuth = new UserAuth();
        User user = new User();
        Date now = new Date();

        userAuth.set("identifier", username);
        userAuth.set("credential", HashKit.md5(password));
        userAuth.set("create_time", now);

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
        UserAuth userAuth = UserAuth.model.findFirst("select * from `user_auth` where identifier=? and credential=?", username, HashKit.md5(password));
        if (userAuth != null) {
            User user = User.model.findById(userAuth.getLong("user_id"));
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
}
