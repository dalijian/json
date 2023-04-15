package com.lijian.doc;

import java.util.List;


/**
 * java类中方法的相关信息
 */
public class MethodComment {
    /** 方法注释 */
    private String methodComment;
    /**  方法名 */
    private String methodName;
    /**  参数 */
    private List<FieldComment> params;
    /**  返回值 */
    private FieldComment returnEntity;
    
    
    public String getMethodComment() {
        return methodComment;
    }
    public String getMethodName() {
        return methodName;
    }
    public List<FieldComment> getParams() {
        return params;
    }
    public FieldComment getReturnEntity() {
        return returnEntity;
    }
    public void setMethodComment(String methodComment) {
        this.methodComment = methodComment;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public void setParams(List<FieldComment> params) {
        this.params = params;
    }
    public void setReturnEntity(FieldComment returnEntity) {
        this.returnEntity = returnEntity;
    }
    @Override
    public String toString() {
        return "{methodComment: " + methodComment + ", methodName: " + methodName + ", params: " + params
                + ", returnEntity: " + returnEntity + "}";
    }
    
    
}