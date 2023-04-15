package com.lijian.java_doc;

public class JavaDoc {
    public static void main(String[] args) {
        com.sun.tools.javadoc.Main.execute(new String[]{
                "-doclet",MyDoclet.class.getName(),
                "-encoding","utf-8",
                "-docletpath","C:\\Users\\lijian\\IdeaProjects\\json\\src\\main\\java\\com\\lijian\\java_doc\\bean",
                "-classpath","D:\\java-EE\\Java\\jdk1.8.0_131\\lib\\tools.jar,C:\\Users\\lijian\\IdeaProjects\\json\\src\\main\\java\\com\\lijian\\java_doc\\bean"
        });
    }
}
