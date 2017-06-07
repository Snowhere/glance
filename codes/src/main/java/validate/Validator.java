package validate;

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
    private static final String URL = "(http|ftp|https|www)[^\u4e00-\u9fa5\\s]*?\\.([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\\\+&amp;%\\$#\\=~])*";


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


}
