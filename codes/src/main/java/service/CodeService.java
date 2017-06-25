package service;

import Entity.CodeEntity;
import Entity.PageEntity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * code
 *
 * @author STH
 * @create 2017-06-02
 **/
public class CodeService {
    public PageEntity<CodeEntity> search(int pageNum, int pageSize) {

        ArrayList<CodeEntity> codeEntities = new ArrayList<>();
        for(int i=0;i<pageSize;i++) {
            CodeEntity codeEntity = new CodeEntity();
            codeEntity.setTitle(pageNum + "");
            codeEntity.setCode(" public File[] findFiles(String filePath, String matchString) {\n" +
                    "     File file = new File(filePath);\n" +
                    "     File[] files = file.listFiles(new MyFileNameFilter(matchString));\n" +
                    "     return files;\n" +
                    " }\n" +
                    "\n" +
                    " class MyFileNameFilter implements FilenameFilter {\n" +
                    "     private String value;\n" +
                    "     public MyFileNameFilter(String value) {\n" +
                    "         this.value = value;\n" +
                    "     }\n" +
                    "\n" +
                    "     @Override\n" +
                    "     public boolean accept(File dir, String name) {\n" +
                    "         return name.contains(value);\n" +
                    "     }\n" +
                    " }");
            codeEntity.setTags(Arrays.asList("info","java","file"));
            codeEntity.setValue("zan 6");
            codeEntity.setLanguage("java");
            codeEntities.add(codeEntity);
        }
        return new PageEntity<CodeEntity>(codeEntities,pageNum,pageSize,1000);
    }
}
