package util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * common response
 *
 * @author STH
 * @create 2017-05-12
 **/
@Data
@AllArgsConstructor
public class Response {
    private boolean success;
    private int code;
    private String msg;
    private Object obj;

    /**
     * 默认true
     */
    public Response() {
        this(true, 0, "成功", null);
    }

    public Response(ErrorEnum errorEnum) {
        this(false, errorEnum.getCode(), errorEnum.getMsg(), null);
    }

    public Response setError(ErrorEnum errorEnum) {
        this.success = false;
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
        return this;
    }
}
