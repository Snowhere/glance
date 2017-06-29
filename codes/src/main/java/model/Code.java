package model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.Arrays;
import java.util.List;

/**
 * code
 *
 * @author STH
 * @create 2017-05-12
 **/
public class Code extends Model<Code> {
    public static Code dao = new Code();

    public static String LAN_C      = "C";
    public static String LAN_JAVA   = "Java";
    public static String LAN_C2     = "C++";
    public static String LAN_OC     = "Objective-C";
    public static String LAN_C3     = "C#";
    public static String LAN_PHP    = "PHP";
    public static String LAN_SQL    = "SQL";
    public static String LAN_VB     = "Visual Basic";
    public static String LAN_PY     = "Python";
    public static String LAN_JS     = "JavaScript";
    public static String LAN_PERL   = "Perl";
    public static String LAN_RUBY   = "Ruby";
    public static String LAN_PL     = "PL/SQL";
    public static String LAN_OP     = "Delphi";
    public static String LAN_NET    = ".Net";
    public static String LAN_LISP   = "Lisp";
    public static String LAN_PA     = "Pascal";
    public static String LAN_HTML   = "HTML";
    public static String LAN_CSS    = "CSS";
    public static String LAN_SWIFT  = "Swift";
    public static String LAN_MAT    = "Matlab";
    public static String LAN_SHELL  = "Shell";
    public static String LAN_GO     = "Go";
    public static String LAN_SCALA  = "Scala";
    public static String LAN_R      = "R";
    public static String LAN_OTHER  = "其他";

    public static List<String> LANGUAGE_LIST = Arrays.asList(LAN_HTML,LAN_CSS,LAN_SWIFT,LAN_MAT,LAN_SHELL,LAN_GO,LAN_SCALA,LAN_R,LAN_C, LAN_JAVA, LAN_C2, LAN_OC, LAN_C3, LAN_PHP, LAN_SQL, LAN_NET, LAN_PY, LAN_JS, LAN_PERL, LAN_RUBY, LAN_PL, LAN_OP, LAN_VB, LAN_LISP, LAN_PA, LAN_OTHER);

    public List<Code> findBySubmitter(Long userId) {
        return find("select * from `code` where submitter=?", userId);
    }

    public Page<Code> pageBySubmitter(int pageNum, int pageSize, Long userId) {
        return paginate(pageNum, pageSize, "select * from `code` ", "where submitter=? order by create_time desc", userId);
    }

    public Page<Code> pageByLanguage(int pageNum, int pageSize, String language) {
        return paginate(pageNum, pageSize, "select * from `code` ", "where type=? order by weight desc", language);
    }

    public List<Code> getTopCode() {
        return find("select * from `code` limit 0,5");
    }
}
