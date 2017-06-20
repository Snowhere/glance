package controller;

public class IndexController extends BaseController {
    public void index() {
        setSessionAttr("user", 123);
        renderJsp("index.jsp");
    }
}
