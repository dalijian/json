package com.lijian.design_pattern.action.visitor;

import java.util.Iterator;

public class ListVisitor implements Visitor {
    private String currentdir = "";
    @Override
    public void visit(File file) {                  
        System.out.println(currentdir + "/" + file);
    }
    // 数据 遍历 不要 写 在 数据 处理 上
    @Override
    public void visit(Directory directory) {  
        System.out.println(currentdir + "/" + directory);
//        String savedir = currentdir;
//        currentdir = currentdir + "/" + directory.getName();
//        Iterator it = directory.iterator();
//        while (it.hasNext()) {
//            Entry entry = (Entry)it.next();
//            entry.accept(this);
//        }
//        currentdir = savedir;
    }
}