package interceptor;

import annotation.Role;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import model.User;
import util.ErrorEnum;
import util.Response;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色权限拦截
 * 处理用Role注解的方法
 *
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
            Role.RoleType roleType = role.role();
            Role.RenderType renderType = role.type();
            if (!checkRole(invocation, role)) {
                if (renderType == Role.RenderType.JSON) {
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
    private Set<Role.RoleType> getLevel(Invocation invocation) {
        Set<Role.RoleType> roleTypes = new HashSet<>();
        User user = invocation.getController().getSessionAttr("user");
        if (user != null) {
            roleTypes.add(Role.RoleType.REGISTER);
        }
        return roleTypes;
    }

    /**
     * 检查是否有权限
     *
     * @param invocation
     * @param role
     * @return
     */
    private boolean checkRole(Invocation invocation, Role role) {
        Set<Role.RoleType> roleTypes = getLevel(invocation);
        return roleTypes.contains(role.role());
    }
}
