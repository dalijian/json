package com.lijian.doc;

import java.util.Collections;
import java.util.List;

//import com.hongcheng.javadoc_generator.entity.ClassComment;

public class Test {
    public static void main(String[] args) throws Exception {
        String jarPath = "C:\\Users\\HongCheng\\Desktop\\11.jar";
        JavaDocReader javaDocReader = new JavaDocReader( "com.lijian.java_doc"
                ,"com.lijian.java_doc"
                ,Collections.singletonList(jarPath),Modifier.PRIVATE,Modifier.PUBLIC );
        List<ClassComment> execute = javaDocReader.execute();
        
        WordExport wordExport = new WordExport();
        wordExport.export(execute,"11.docx");
    }
}