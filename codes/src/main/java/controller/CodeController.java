package controller;

import Entity.CodeEntity;
import Entity.PageEntity;
import annotation.DI;
import annotation.Role;
import model.Code;
import model.User;
import service.CodeService;
import util.Response;

import java.util.Date;
import java.util.List;

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

    public void search() {
        renderJsp("list.jsp");
    }

    /**
     * 详情
     */
    public void detail() {
        Long id = getParaToLong();
        Code code = Code.dao.findById(id);
        if (code == null) {
            renderError(404);
        }else {
            setAttr("code", code);
            render("detail.jsp");
        }

    }

    public void post() {
        List<String> languageList = Code.LANGUAGE_LIST;
        setAttr("languages", languageList);
        renderJsp("post.jsp");
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

    /**
     * 提交
     */
    public void doPost() {
        Code code = getModel(Code.class);
        User user = getSessionAttr("user");
        code.set("submitter", user.get("id"));
        code.set("create_time", new Date());
        code.set("update_time", new Date());
        codeService.saveCode(code);
    }
}
