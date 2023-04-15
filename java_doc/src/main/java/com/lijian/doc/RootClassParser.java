package com.lijian.doc;

import java.util.LinkedList;
import java.util.List;

//import com.hongcheng.javadoc_generator.entity.ClassComment;
//import com.hongcheng.javadoc_generator.entity.FieldComment;
//import com.hongcheng.javadoc_generator.entity.MethodComment;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;
/**
 *     RootClass对象的解析器，用于根据RootClass构建我们自己的ClassComment
 * */
public class RootClassParser {
    
    /** 需要处理的类字段可见性，默认公有 */
    private Modifier fieldModifier = Modifier.PUBLIC;
    /** 需要处理的类方法可见性，默认公有 */
    private Modifier methodModifier = Modifier.PUBLIC;
    
    public RootClassParser(Modifier fieldModifier,Modifier methodModifier) {
        this.fieldModifier = fieldModifier;
        this.methodModifier = methodModifier;
    }
    
    /**
     *     解析
     * */
    public List<ClassComment> parse(RootDoc root) {
        if(root == null) {
            return new LinkedList<ClassComment>();
        }
        List<ClassComment> classComments = new LinkedList<ClassComment>();
        ClassDoc[] classes = root.classes();
        for (ClassDoc clasz:classes) {
            ClassComment classComment = new ClassComment();
            classComment.setClassName(clasz.qualifiedTypeName());
            classComment.setSimpleClassName(clasz.simpleTypeName());
            classComment.setClassComment(clasz.commentText());
            classComment.setFields( this.parseFields(clasz.fields()));
            classComment.setMethods( this.parseMethods(clasz.methods()));
            classComments.add(classComment);
        }
        return classComments;
    }
    
    /**
     *     解析字段
     * */
    private List<FieldComment> parseFields(FieldDoc[] fields){
        if(fields == null || fields.length <= 0) {
            return new LinkedList<FieldComment>();
        }
        List<FieldComment> fieldList = new LinkedList<FieldComment>();
        for (FieldDoc field : fields) {
            if(!this.checkModifier(field)) {
                continue;
            }
            FieldComment fieldComment = new FieldComment();
            fieldList.add(fieldComment);
            fieldComment.setClasz(field.type().qualifiedTypeName());
            fieldComment.setSimpleClassName(field.type().simpleTypeName());
            fieldComment.setFieldComment(field.commentText());
            fieldComment.setFieldName(field.name());
            fieldComment.setDefaultValue(field.constantValue());
        }
        return fieldList;
    }
    
    /**
     *     检查字段修饰语，也就是public、protected、private
     *     @return 如果该字段的访问权限修饰语满足我们需要的级别，那就返回true
     * */
    private boolean checkModifier(FieldDoc field) {
        if(this.getFieldModifier().toString().equalsIgnoreCase(field.modifiers())) {
            return true;
        }
        return false;
    }
    
    /**
     *     检查方法修饰语，也就是public、protected、private
     *     @return 如果该方法的访问权限修饰语满足我们需要的级别，那就返回true
     * */
    private boolean checkModifier(MethodDoc method) {
        if(this.getMethodModifier().toString().equalsIgnoreCase(method.modifiers())) {
            return true;
        }
        return false;
    }
    
    
    
    /**
     *     解析方法
     *     */
    private List<MethodComment> parseMethods(MethodDoc[] methods){
        if(methods == null || methods.length <= 0) {
            return new LinkedList<MethodComment>();
        }
        List<MethodComment> methodsList = new LinkedList<MethodComment>();
        for (MethodDoc method : methods) {
            if(!this.checkModifier(method)) {
                continue;
            }
            MethodComment methodComment = new MethodComment();
            methodsList.add(methodComment);
            methodComment.setMethodComment(method.commentText());
            methodComment.setMethodName(method.name());
            methodComment.setReturnEntity(this.parseMethodReturn(method));
            methodComment.setParams(this.parseMethodParam(method));
        }
        return methodsList;
    }
    
    /***
     *     解析方法的返回值
     * */
    private FieldComment parseMethodReturn(MethodDoc method){
        // 返回值
        FieldComment returnEntity = new FieldComment();
        returnEntity.setClasz(method.returnType().qualifiedTypeName());
        returnEntity.setSimpleClassName(method.returnType().simpleTypeName());
        for(Tag tag:method.tags()) {
            if(tag.name().equals("@return")) {
                returnEntity.setFieldComment(tag.text());
                break;
            }
        }    
        return returnEntity;
    }
    
    
    /***
     *     解析方法的参数
     * */
    private List<FieldComment> parseMethodParam(MethodDoc method){
        // 参数    
        List<FieldComment> params = new LinkedList<FieldComment>();
        for(Parameter parameter:method.parameters()) {
            FieldComment param = new FieldComment();
            param.setClasz(parameter.type().qualifiedTypeName());
            param.setSimpleClassName(parameter.type().simpleTypeName());
            param.setFieldName(parameter.name());
            for(ParamTag paramTag :method.paramTags()) {
                if(paramTag.parameterName().equals(param.getFieldName())) {
                    param.setFieldComment(paramTag.parameterComment());;
                    break;
                }
            }
            params.add(param);
        }
        return params;
    }

    public Modifier getFieldModifier() {
        return fieldModifier;
    }

    public Modifier getMethodModifier() {
        return methodModifier;
    }

    public void setFieldModifier(Modifier fieldModifier) {
        this.fieldModifier = fieldModifier;
    }

    public void setMethodModifier(Modifier methodModifier) {
        this.methodModifier = methodModifier;
    }
    
    
}