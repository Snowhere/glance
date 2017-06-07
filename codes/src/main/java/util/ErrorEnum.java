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

    USER_LOGIN(12345, "用户名或密码错误"),
    USER_REGISTER(213, "注册失败"),
    USER_UNIQUE_USERNAME(213, "用户名重复"),

    CODE_CODE(1234,"代码有问题");

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
