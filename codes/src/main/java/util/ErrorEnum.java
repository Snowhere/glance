package util;

import lombok.Getter;

/**
 * ${DESCRIPTION}
 *
 * @author STH
 * @create 2017-05-31
 **/
@Getter
public enum ErrorEnum {
    IMG(1111, "图片验证码错误"),

    LOGIN(12345, "用户名或密码错误"),
    REGISTER(213, "注册失败"),
    ;
    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    }
