package controller;

import com.jfinal.core.Controller;
import service.UserService;
import util.ErrorEnum;
import util.Response;

public class UserController extends Controller {
    UserService userService = new UserService();

    /**
     * 登录页
     */
    public void login() {
        renderJsp("login.jsp");
    }

    /**
     * 注册页
     */
    public void register() {
        renderJsp("login.jsp");
    }

    public void info() {
        renderJsp("info.jsp");
    }

    /**
     * 登录ajax
     */
    public void userLogin() {
        Response response = new Response();
        String username = getPara("username");
        String password = getPara("password");
        boolean captcha = validateCaptcha("captcha");

        if (captcha) {
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
        boolean captcha = validateCaptcha("captcha");

        if (captcha) {
            boolean login = userService.register(nickname, username, password);
            if (!login) {
                response.setError(ErrorEnum.REGISTER);
            }
        } else {
            response.setError(ErrorEnum.IMG);
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
