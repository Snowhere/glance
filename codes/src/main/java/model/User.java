package model;

import com.jfinal.plugin.activerecord.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * code
 *
 * @author STH
 * @create 2017-05-12
 **/
public class User extends Model<User> {
    public static User dao = new User();

    //缓存用户id列表
    public static List<Long> USER_IDS = new LinkedList<>();
    {
        List<User> users = find("select id from `user`");
        for (User user : users) {
            USER_IDS.add(user.getLong("id"));
        }
    }
}
