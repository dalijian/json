package com.lijian.doc;

import java.util.List;



/**
 * java类的相关信息
 */
public class ClassComment {
    /** 类的全类名 */
    private String className;
    /** 类的简单类名 */
    private String simpleClassName;
    /** 类注释 */
    private String classComment;
    /** 字段相关信息 */
    private List<FieldComment> fields;
    /** 方法相关信息 */
    private List<MethodComment> methods;
    
    
    
    public String getClassName() {
        return className;
    }
    public String getSimpleClassName() {
        return simpleClassName;
    }
    public String getClassComment() {
        return classComment;
    }
    public List<FieldComment> getFields() {
        return fields;
    }
    public List<MethodComment> getMethods() {
        return methods;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }
    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }
    public void setFields(List<FieldComment> fields) {
        this.fields = fields;
    }
    public void setMethods(List<MethodComment> methods) {
        this.methods = methods;
    }
    @Override
    public String toString() {
        return "{className: " + className + ", simpleClassName: " + simpleClassName + ", classComment: " + classComment
                + ", fields: " + fields + ", methods: " + methods + "}";
    }
    
    
    
}