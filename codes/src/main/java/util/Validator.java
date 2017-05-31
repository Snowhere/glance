package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * validator
 *
 * @author STH
 * @create 2017-05-31
 **/
public class Validator {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String EMAIL_ADDRESS = "\\b(^[\'_A-Za-z0-9-]+(\\.[\'_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
    private static final String URL = "(http|ftp|https|www)[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*";
    private static final String USER_NAME = "^[a-zA-Z0-9_]*$";
    private static final String NUMBER = "^\\d+$";
    private static final String UNDER_LINE = "^_+$";

    /**
     * 合法邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_ADDRESS);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 合法url
     * http或https开头
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        boolean flag = true;
        try {
            url = url.trim();
            if (url.startsWith("https://")) {
                url = "http://" + url.substring(8);
            }
            new URL(url);
        } catch (MalformedURLException var6) {
            flag = false;
        }
        return flag;
    }

    /**
     * 包含url
     *
     * @param text
     * @return
     */
    public static boolean containUrl(String text) {
        Pattern pattern = Pattern.compile(URL);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * 抽取url
     *
     * @param text
     * @return
     */
    public static String extractUrl(String text) {
        String result = "";
        Pattern pattern = Pattern.compile(URL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    /**
     * 合法用户名
     * 数字、字母、下划线
     * 4-16位
     * 不能是纯数字或纯下划线
     *
     * @param text
     * @return
     */
    public static boolean userName(String text) {
        boolean format = Pattern.compile(USER_NAME).matcher(text).matches();
        boolean length = text.length() <= 17 && text.length() > 3;
        boolean line = !Pattern.compile(UNDER_LINE).matcher(text).matches();
        boolean number = !Pattern.compile(NUMBER).matcher(text).matches();
        return format && length && line && number;
    }
}
