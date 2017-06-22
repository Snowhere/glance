package controller;

import annotation.DI;
import annotation.Role;
import model.Code;
import service.CodeService;

public class CodeController extends BaseController {
    @DI
    CodeService codeService;

    /**
     * 列表
     */
    @Role(role = Role.RoleType.REGISTER, type = Role.RenderType.HTML)
    public void list() {
        render("");
    }

    /**
     * 详情
     */
    public void detail() {
        render("");
    }

    public void search() {
        renderJsp("list.jsp");
    }
    /**
     * 语言列表
     */
    public void getLanguageList() {
        renderJson(Code.LANGUAGE_LIST);
    }
}
