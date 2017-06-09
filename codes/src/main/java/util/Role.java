package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * level角色
 * type返回类型
 *
 * @author STH
 * @create 2017-06-09
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    //角色
    public enum Level{NORMAL,REGIST,BIND, ADMIN}
    //请求返回类型
    public enum RenderType{JSON,HTML}

    public Level level() default Level.NORMAL;
    public RenderType type() default RenderType.JSON;
}
