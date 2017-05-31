package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * user role
 *
 * @author STH
 * @create 2017-05-12
 **/
public class RoleInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
