package model;

import com.jfinal.plugin.activerecord.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * code
 *
 * @author STH
 * @create 2017-05-12
 **/
public class UserAuth extends Model<UserAuth> {
    public static UserAuth dao = new UserAuth();

    public static String TYPE_LOCAL     = "local";
    public static String TYPE_WECHAT    = "wechat";
    public static String TYPE_QQ        = "qq";
    public static String TYPE_GITHUB    = "github";


    public static List<String> USER_NAMES = new ArrayList<>();

    {
        List<UserAuth> allUser = find("select identifier from `user_auth` where type=?", TYPE_LOCAL);
        for (UserAuth user : allUser) {
            USER_NAMES.add(user.getStr("identifier"));
        }
    }



    public List<UserAuth> findByUserId(Long userId) {
        return find("select * from `user_auth` where user_id=?", userId);
    }

    public User getUser(String identifier, String credential) {
        User user = null;
        UserAuth userAuth = findFirst("select * from `user_auth` where identifier=? and credential=?", identifier, credential);
        if (userAuth != null) {
            user = User.dao.findById(userAuth.getLong("user_id"));
        }
        return user;
    }

    public boolean checkUser(String identifier, String credential) {
        return null != getUser(identifier, credential);
    }
}
