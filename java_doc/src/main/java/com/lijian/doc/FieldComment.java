package com.lijian.doc;

/**
 * java类中字段的相关信息
 */
public class FieldComment {
    /** 字段类型 */
    private String clasz;
    /** 类的简单类名 */
    private String simpleClassName;
    /** 字段注释 */
    private String fieldComment;
    /** 字段名 */
    private String fieldName;
    /** 默认值，必须是final修饰的基本数据类型及其包装类 */
    private Object defaultValue;
    
    
    public String getSimpleClassName() {
        return simpleClassName;
    }
    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }
    public String getClasz() {
        return clasz;
    }
    public String getFieldComment() {
        return fieldComment;
    }
    public String getFieldName() {
        return fieldName;
    }
    public Object getDefaultValue() {
        return defaultValue;
    }
    public void setClasz(String clasz) {
        this.clasz = clasz;
    }
    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
    @Override
    public String toString() {
        return "{clasz: " + clasz + ", simpleClassName: " + simpleClassName + ", fieldComment: " + fieldComment
                + ", fieldName: " + fieldName + ", defaultValue: " + defaultValue + "}";
    }
    
    
}