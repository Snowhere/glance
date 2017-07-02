package controller;

import lombok.extern.log4j.Log4j;

/**
 * Created by Administrator on 2017/6/30.
 */
public class AboutController extends BaseController {
    public void us() {
        renderJsp("us.jsp");
    }
}
