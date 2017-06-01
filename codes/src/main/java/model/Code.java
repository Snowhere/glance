package model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * code
 *
 * @author STH
 * @create 2017-05-12
 **/
public class Code extends Model<Code> {
    public static Code dao = new Code();

    public List<Code> findBySubmitter(Long userId) {
        return find("select * from `code` where submitter=", userId);
    }
}
