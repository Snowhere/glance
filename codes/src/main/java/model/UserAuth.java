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

    public static String TYPE_LOCAL     = "local";
    public static String TYPE_WECHAT    = "wechat";
    public static String TYPE_QQ        = "qq";
    public static String TYPE_GITHUB    = "github";

    public static UserAuth dao = new UserAuth();

    public List<String> allUserName = new ArrayList<>();

    {
        List<UserAuth> allUser = find("select identifier from `user_auth` where type=?", TYPE_LOCAL);
        for (UserAuth user : allUser) {
            allUserName.add(user.getStr("identifier"));
        }
    }


    public List<UserAuth> findByUserId(Long userId) {
        return find("select * from `user_auth` where user_id=?", userId);
    }

    public UserAuth getUser(String identifier, String credential) {
        return findFirst("select * from `user_auth` where identifier=? and credential=?", identifier, credential);
    }

    public boolean checkUser(String identifier, String credential) {
        return null != getUser(identifier, credential);
    }
}
