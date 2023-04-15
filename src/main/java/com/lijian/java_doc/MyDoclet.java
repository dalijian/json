package com.lijian.java_doc;

import com.sun.javadoc.Doclet;
import com.sun.javadoc.RootDoc;

/**
 * 用来获取javadoc解析完成后生成的语法树根节点
 */
public class MyDoclet extends Doclet {


    /**
     * 静态对象，用于接收javadoc解析完成后生成的语法树根节点<br>*在后面我们会用他来获取我们需要的数据
     **/
    private static RootDoc root;

    /*在javadoc解析完java文件后，生成语法树，然后就会调用这个方法去让Docl.et生成doc文档
    //  关键方法，关键对象，rootDoc里面有我们所需要的class对象  和注释，在这里取到后，就可以

    // 用类似反射的api去获取我们的注释啦
     **
     */
    public static boolean start(RootDoc rootDoc) {
        MyDoclet.root = rootDoc;
        return true;
    }


    /**
     * 获取语法树的根节点
     **/
    public static RootDoc getRoot() {
        return root;
    }
}


