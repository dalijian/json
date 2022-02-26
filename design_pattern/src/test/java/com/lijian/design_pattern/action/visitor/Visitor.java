package com.lijian.design_pattern.action.visitor;

public interface Visitor {
    void visit(File file);
    void visit(Directory directory);
}