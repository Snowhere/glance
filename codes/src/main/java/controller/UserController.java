package controller;

import annotation.DI;
import com.jfinal.kit.StrKit;
import model.User;
import model.UserAuth;
import service.UserService;
import util.ErrorEnum;
import util.Response;
import validate.UserValidate;

import java.util.Map;

public class UserController extends BaseController {
    @DI
    UserService userService;
    @DI
    UserValidate userValidate;

    //登录页
    public void login() {
        renderJsp("login.jsp");
    }

    //登出(跳首页)
    public void logout() {
        removeSessionAttr("user");
        System.out.println(getSessionAttr("user"));
        redirect("/");
    }

    //注册页
    public void register() {
        renderJsp("register.jsp");
    }

    //验证码
    public void captcha() {
        renderCaptcha();
    }

    /**
     * 个人信息
     * /user/userId
     * 区分本人和其他人查看
     * jsp
     */
    public void index() {
        User currentUser = getSessionAttr("user");
        Long userId = getParaToLong();
        Map<String, Object> info = userService.info(userId);
        setAttrs(info);
        renderJsp("info.jsp");
    }

    /**
     * 登录
     * ajax
     */
    public void userLogin() {
        Response response = new Response();
        String username = getPara("username");
        String password = getPara("password");
        if (!validateCaptcha("captcha")) {
            renderJson(response.setError(ErrorEnum.CAPTCHA));
            return;
        }
        if (!userService.login(this.getSession(), username, password)) {
            response.setError(ErrorEnum.USER_LOGIN);
        }
        renderJson(response);
    }


    /**
     * 注册
     * ajax
     */
    public void userRegister() {
        Response response = new Response();
        String nickname = getPara("nickname");
        String sex = getPara("sex");
        String username = getPara("username");
        String password = getPara("password");
        String type = getPara("type");
        boolean captcha = validateCaptcha("captcha");

        if (!StrKit.notBlank(nickname, username, password)) {
            renderJson(response.setError(ErrorEnum.BLANK));
            return;
        }
        if (!captcha) {
            renderJson(response.setError(ErrorEnum.CAPTCHA));
            return;
        }
        if (!userValidate.checkName(username)) {
            renderJson(response.setError(ErrorEnum.USER_NICKNAME));
            return;
        }
        if (!userService.register(nickname, username, password, type)) {
            response.setError(ErrorEnum.USER_REGISTER);
        }
        renderJson(response);
    }

    /**
     * 检查用户名是否唯一
     * ajax
     */
    public void uniqueUserName() {
        Response response = new Response();
        String username = getPara("username");
        if (UserAuth.USER_NAMES.contains(username)) {
            response.setError(ErrorEnum.USER_UNIQUE_USERNAME);
        }
        renderJson(response);
    }


    public void getPopularUsers() {
        Response response = new Response();
        renderJson(response);
    }


    /**
     * 发送验证邮件
     */
    public void email() {
        String address = getPara("email");
        log.info("123");
        userService.email(address);
    }
}
