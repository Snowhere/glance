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
public class User extends Model<User> {
    public static User dao = new User();

    public List<User> getUsers(List<String> userIds) {
        List<User> result = new ArrayList<>();
        if(userIds!=null&&!userIds.isEmpty()){
            //result =this.("select * from `user` where id in (?)", ","));
        }
        return result;
    }
}
