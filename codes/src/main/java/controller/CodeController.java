package controller;

import com.jfinal.core.Controller;
import model.Code;
import service.CodeService;

public class CodeController extends Controller {
    CodeService codeService = new CodeService();
    /**
     * 列表
     */
    public void list() {
        render("");
    }

    /**
     * 详情
     */
    public void detail() {
        render("");
    }

    /**
     * 语言列表
     */
    public void getLanguageList() {
        renderJson(Code.LANGUAGE_LIST);
    }
}
