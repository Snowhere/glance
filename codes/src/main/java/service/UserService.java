package service;

import com.jfinal.plugin.redis.Redis;
import model.User;
import util.CacheName;

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
        List<User> users= User.model.find("select id from `user`");
        for (User user : users) {
            userIds.add(user.getLong("id"));
        }
    }

    /**
     * 创建新用户
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
     * 获取用户数量
     * @return
     */
    public int getUserNumber() {
        return userIds.size();
    }
}
