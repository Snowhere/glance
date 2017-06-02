package service;

import com.jfinal.plugin.activerecord.Page;
import model.Code;

/**
 * code
 *
 * @author STH
 * @create 2017-06-02
 **/
public class CodeService {
    public void getCodes(int pageNum,int pageSize) {
        Page<Code> codes = Code.dao.paginate(pageNum, pageSize, "select * from `code` ", "where submitter=?", "");
    }
}
