package controller;

import com.jfinal.core.Controller;
import model.User;
import model.UserAuth;
import service.UserService;
import util.ErrorEnum;
import util.Response;

import java.util.Map;

public class UserController extends Controller {
    UserService userService = new UserService();

    //登录页
    public void login() {
        renderJsp("login.jsp");
    }

    //登出
    public void logout() {
        removeSessionAttr("user");
        redirect("/");
    }

    //注册页
    public void register() {
        renderJsp("register.jsp");
    }

    /**
     * 个人信息
     * /user/userId
     * 区分本人和其他人查看
     */
    public void index() {
        User currentUser = getSessionAttr("user");
        Long userId = getParaToLong();
        Map<String, Object> info = userService.info(userId);
        setAttrs(info);
        renderJsp("info.jsp");
    }

    /**
     * 登录ajax
     */
    public void userLogin() {
        Response response = new Response();
        String username = getPara("username");
        String password = getPara("password");
        if (validateCaptcha("captcha")) {
            boolean login = userService.login(this.getSession(), username, password);
            if (!login) {
                response.setError(ErrorEnum.LOGIN);
            }
        } else {
            response.setError(ErrorEnum.IMG);
        }
        renderJson(response);
    }

    /**
     * 注册ajax
     */
    public void userRegister() {
        Response response = new Response();
        String nickname = getPara("nickname");
        String sex = getPara("sex");
        String username = getPara("username");
        String password = getPara("password");
        String type = getPara("type");
        boolean captcha = validateCaptcha("captcha");

        if (captcha) {
            boolean login = userService.register(nickname, username, password, type);
            if (!login) {
                response.setError(ErrorEnum.REGISTER);
            }
        } else {
            response.setError(ErrorEnum.IMG);
        }

        renderJson(response);
    }

    /**
     * 检查用户名是否唯一
     */
    public void uniqueUserName() {
        Response response = new Response();
        String username = getPara("username");
        if (UserAuth.USER_NAMES.contains(username)) {
            response.setError(ErrorEnum.UNIQUE_USERNAME);
        }
        renderJson(response);
    }

    /**
     * 验证码
     */
    public void captcha() {
        renderCaptcha();
    }

    /**
     * 发送验证邮件
     */
    public void email() {
        String address = getPara();
        userService.email(address);
    }
}
