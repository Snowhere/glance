package validate;

import java.util.regex.Pattern;

/**
 * code
 *
 * @author STH
 * @create 2017-05-31
 **/
public class UserValidate {

    private static final String USER_NAME = "^[a-zA-Z0-9_]*$";
    private static final String NUMBER = "^\\d+$";
    private static final String UNDER_LINE = "^_+$";

    /**
     * 合法用户名
     * 数字、字母、下划线
     * 4-16位
     * 不能是纯数字或纯下划线
     *
     * @param text
     * @return
     */
    public boolean checkName(String text) {
        boolean format = Pattern.compile(USER_NAME).matcher(text).matches();
        boolean length = text.length() < 17 && text.length() > 3;
        boolean line = !Pattern.compile(UNDER_LINE).matcher(text).matches();
        boolean number = !Pattern.compile(NUMBER).matcher(text).matches();
        return format && length && line && number;
    }
}
