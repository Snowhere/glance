package interceptor;

import annotation.DI;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import service.AdService;
import service.CodeService;
import service.UserService;

/**
 * @author STH
 * @create 2017-06-29
 **/
public class ConstantInfoInterceptor implements Interceptor {
    @DI
    UserService userService;
    @DI
    CodeService codeService;
    @DI
    AdService adService;

    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        if (controller.getSessionAttr("topCodes") == null) {
            controller.setSessionAttr("topCodes", codeService.getTopCode());
        }
        if (controller.getSessionAttr("topUsers") == null) {
            controller.setSessionAttr("topUsers", userService.getTopUser());
        }
        if(controller.getSessionAttr("ads")==null){
            controller.setSessionAttr("ads", adService.getAd());
        }
        invocation.invoke();
    }
}
