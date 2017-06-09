package util;

import lombok.Getter;

/**
 * 错误代码，四位数字
 * 前两位一级分类，后两位二级分类
 *
 * @author STH
 * @create 2017-05-31
 **/
@Getter
public enum ErrorEnum {
    CAPTCHA                     (1001, "图片验证码错误"),
    BLANK                       (1002, "字段不能为空"),

    ROLE                        (2001, "没有权限"),

    USER_LOGIN                  (3001, "用户名或密码错误"),
    USER_REGISTER               (3002, "注册失败"),
    USER_UNIQUE_USERNAME        (3003, "用户名重复"),
    USER_NICKNAME               (3004, "昵称不合法"),

    CODE_CODE                   (4001,"代码有问题")




    ;

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
