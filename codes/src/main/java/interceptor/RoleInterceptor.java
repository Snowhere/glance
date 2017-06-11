package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import model.User;
import util.ErrorEnum;
import util.Response;
import annotation.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色权限拦截
 * 处理用Role注解的方法
 * @author STH
 * @create 2017-05-12
 **/
public class RoleInterceptor implements Interceptor {


    @Override
    public void intercept(Invocation invocation) {
        Role role1 = invocation.getController().getClass().getAnnotation(Role.class);
        Role role2 = invocation.getMethod().getAnnotation(Role.class);
        Role role = role2 == null ? role1 : role2;
        if (role != null) {
            Controller controller = invocation.getController();
            User user = controller.getSessionAttr("user");
            Role.Level level = role.level();
            Role.RenderType type = role.type();
            if (!getLevel(invocation).contains(level)) {
                if (type == Role.RenderType.JSON) {
                    controller.renderJson(new Response(ErrorEnum.ROLE));
                } else {
                    controller.setAttr("msg", ErrorEnum.ROLE.getMsg());
                    controller.render("");
                }
                return;
            }

        }
        invocation.invoke();
    }

    /**
     * 获取当前用户权限列表
     *
     * @param invocation
     * @return
     */
    private Set<Role.Level> getLevel(Invocation invocation) {
        Set<Role.Level> levels = new HashSet<>();
        User user = invocation.getController().getSessionAttr("user");
        if (user != null) {
            levels.add(Role.Level.REGIST);
        }
        return levels;
    }
}
