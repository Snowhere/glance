package controller;

import annotation.DI;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import util.BeanFactory;

import java.lang.reflect.Field;

/**
 * Controller基类
 * 根据注解依赖注入service
 * 初始化一个log
 */
public abstract class BaseController extends Controller {

    protected Log log = Log.getLog(this.getClass());

    public BaseController() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getAnnotation(DI.class) != null) {
                Object o = BeanFactory.getInstance().getBean(field.getType());
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    field.set(this, o);
                } catch (IllegalAccessException e) {
                    log.error(this.getClass().getName() + " inject fail: " + field.getName());
                }
            }
        }
    }
}
