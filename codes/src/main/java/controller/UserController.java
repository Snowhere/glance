package controller;

import com.jfinal.core.Controller;
import service.UserService;

public class UserController extends Controller {
    UserService userService = new UserService();
    /**
     * 登录
     */
    /**
     * 登录
     */
    public void toLogin() {
        renderJsp("login.jsp");
    }
    public void login() {
        boolean captcha = validateCaptcha("captcha");
        System.out.println(captcha);
        redirect("/");
    }

    /**
     * 注册
     */
    public void register() {
        render("");
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
    public void email(){
        String address = getPara();
        userService.email(address);
    }
}
