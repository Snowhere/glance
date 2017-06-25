package controller;

import Entity.CodeEntity;
import Entity.PageEntity;
import annotation.DI;
import annotation.Role;
import model.Code;
import service.CodeService;
import util.Response;

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

    public void codeSearch() {
        Response response = new Response();
        int pageNum = getParaToInt("pageNum", 1);
        int pageSize = getParaToInt("pageSize", 20);
        PageEntity<CodeEntity> codes = codeService.search(pageNum, pageSize);
        response.setObj(codes);
        renderJson(response);
    }
    /**
     * 语言列表
     */
    public void getLanguageList() {
        renderJson(Code.LANGUAGE_LIST);
    }
}
