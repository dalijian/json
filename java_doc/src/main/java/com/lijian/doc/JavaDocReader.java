package com.lijian.doc;

import com.sun.javadoc.RootDoc;
import com.sun.xml.internal.rngom.digested.Main;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import com.hongcheng.javadoc_generator.entity.ClassComment;
//import com.sun.javadoc.RootDoc;
/**
 *     java文件doc读取器
 * */
public class JavaDocReader {

    /** 用于接收javadoc解析完成后生成的语法树根节点 */
    private MyDoclet doclet = new MyDoclet();
    /** 类路径，系统会在类路径下去找一些相关的类文件 */
    private List<String> classpath ;
    /** java源文件地址。可以是java源文件的绝对地址，也可以是包名。只能填写一个， */
    private String sourcepath;
    /** 是否递归处理子包，只有在sourcepath为包名时才起作用，subpackages不为空是才是true。默认false */
    private boolean isSubpackages = false;
    /** 要递归的子包 */
    private String subpackages ;
    /** 需要处理的类字段可见性，默认公有 */
    private Modifier fieldModifier = Modifier.PUBLIC;
    /** 需要处理的类方法可见性，默认公有 */
    private Modifier methodModifier = Modifier.PUBLIC;
    /** jdk的tools.jar的地址 */
    private String jdkToolsJarPath = JavaDocReader.class.getResource("/").getPath() + "/tools.jar";
    
    
    /**
     *     构造函数
     *     @param javaPackage 目标jar包，非空必填
     *    @param subpackages 需要递归处理的子包，可以为空
     *    @param classPath 相关的jar包的地址，绝对地址，javaPackage必须要能在这些路径中找到，非空必填
     * */
    public JavaDocReader(String javaPackage,String subpackages,List<String> classPath) {
        this.init(javaPackage, subpackages, classPath, Modifier.PUBLIC, Modifier.PUBLIC);
    }
    
    

    
    /**
     *     构造函数
     *     @param javaPackage 目标jar包，非空必填
     *    @param subpackages 需要递归处理的子包，可以为空
     *    @param classPath 相关的jar包的地址，绝对地址，javaPackage必须要能在这些路径中找到，非空必填
     *    @param fieldModifier 需要处理的类字段可见性，非空
     *    @param methodModifier 需要处理的类方法可见性，非空
     * */
    public JavaDocReader(String javaPackage,String subpackages,List<String> classPath
            ,Modifier fieldModifier,Modifier methodModifier) {
        this.init(javaPackage, subpackages, classPath, fieldModifier, methodModifier);
    }
    
    
    /**
     *     构造函数
     *     @param javaFilePath java文件地址，非空必填，绝对路径
     *    @param classpath 源文件中引用的相关类的jar包地址，可选
     * */
    public JavaDocReader(String javaFilePath,List<String> classpath ) {
        this.init(javaFilePath, null, classpath, Modifier.PUBLIC, Modifier.PUBLIC);
    }
    
    
    
    
    /**
     *     构造函数
     *     @param javaFilePath java文件地址，非空必填，绝对路径
     *    @param classpath 源文件中引用的相关类的jar包地址，可选
     *    @param fieldModifier 需要处理的类字段可见性，非空
     *    @param methodModifier 需要处理的类方法可见性，非空
     * */
    public JavaDocReader(String javaFilePath,List<String> classpath
            ,Modifier fieldModifier,Modifier methodModifier) {
        this.init(javaFilePath, null, classpath, fieldModifier, methodModifier);
    }
    
    
    
    
    /**
     *     构造函数
     *     @param sourcepath .java源文件地址，非空。可以是java源文件的绝对地址，也可以是包名。<br>
     *         如果是java源文件的绝对地址，只能填写一个地址，不能填写多个地址。<br>
     *         如果是包名，该包必须能在classpath下找到，只能填写一个包名<br><br>
     *     @param classpath 类路径，系统会在类路径下去找一些相关的类文件，可以为空
     *    @param subpackages 是否递归处理子包，只有在sourcepath为包名时才起作用，可以为空
     *    @param fieldModifier 需要处理的类字段可见性，非空
     *    @param methodModifier 需要处理的类方法可见性，非空
     * */
    private void init(String sourcepath,String subpackages,List<String> classpath
            ,Modifier fieldModifier,Modifier methodModifier) {
        
        this.checkNotEmpty(sourcepath, "目标java文件不能为空");
        classpath = this.checkNotEmpty(classpath)?new LinkedList<String>(classpath):new LinkedList<String>();
        classpath.add(this.getJdkToolsJarPath());
        
        this.classpath = classpath;
        this.sourcepath = sourcepath;
        this.subpackages = subpackages;
        this.fieldModifier = fieldModifier == null?this.fieldModifier:fieldModifier;
        this.methodModifier = methodModifier == null?this.methodModifier:methodModifier;
        this.isSubpackages = this.checkNotEmpty(subpackages);
    }
    
    /**
     *     初始化参数
     *     @return String [] javadoc需要的参数数组
     * */
    private String [] initAgrs() {
        
        List<String> args = new LinkedList<String>();
        args.add("-encoding");
        args.add("utf-8");
        
        args.add("-doclet");
        args.add(MyDoclet.class.getName());
        
        args.add("-docletpath");
        args.add(MyDoclet.class.getResource("/").getPath());
        
        if(this.isSubpackages()) {
            args.add("-subpackages");
            args.add(this.getSubpackages());
        }
        
        StringBuilder sb = new StringBuilder();
        for(String classpath: this.getClasspath()) {
            sb.append(classpath).append(";");
        }
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        args.add("-classpath");
        args.add(sb.toString());
        
        if(this.fieldModifier == Modifier.PRIVATE || this.methodModifier == Modifier.PRIVATE) {
            args.add("-private");
        }else if(this.fieldModifier == Modifier.PROTECTED || this.methodModifier == Modifier.PROTECTED) {
            args.add("-protected");
        }else {
            args.add("-public");
        }
        
        args.add(this.sourcepath);
        
        return args.toArray(new String[args.size()]);
    }
    
    
    /**
     *     执行javadoc，解析源文件
     *     */
    private void executeJavadoc() {
        String[] initAgrs = this.initAgrs();
        com.sun.tools.javadoc.Main.execute(initAgrs);
    }
    
    
    /**
     *     获取类注释信息
     *     @return  List<ClassComment> 
     * */
    public List<ClassComment> execute(){
        this.executeJavadoc();
        RootDoc root = MyDoclet.getRoot();
        if(root == null) {
            return new LinkedList<ClassComment>();
        }
        RootClassParser parser = new RootClassParser(this.getFieldModifier(),this.getMethodModifier());
        List<ClassComment> parseResult = parser.parse(root);
        return parseResult;
    }
    
    
    
    public String getJdkToolsJarPath() {
        return jdkToolsJarPath;
    }


    public void setJdkToolsJarPath(String jdkToolsJarPath) {
        this.jdkToolsJarPath = jdkToolsJarPath;
    }


    public String getSubpackages() {
        return subpackages;
    }

    public void setSubpackages(String subpackages) {
        this.subpackages = subpackages;
    }

    public MyDoclet getDoclet() {
        return doclet;
    }

    public List<String> getClasspath() {
        return classpath;
    }

    public String getSourcepath() {
        return sourcepath;
    }

    public boolean isSubpackages() {
        return isSubpackages;
    }

    public Modifier getFieldModifier() {
        return fieldModifier;
    }

    public Modifier getMethodModifier() {
        return methodModifier;
    }

    public void setDoclet(MyDoclet doclet) {
        this.doclet = doclet;
    }

    public void setClasspath(List<String> classpath) {
        this.classpath = classpath;
    }

    public void setSourcepath(String sourcepath) {
        this.sourcepath = sourcepath;
    }

    public void setSubpackages(boolean isSubpackages) {
        this.isSubpackages = isSubpackages;
    }

    public void setFieldModifier(Modifier fieldModifier) {
        this.fieldModifier = fieldModifier;
    }

    public void setMethodModifier(Modifier methodModifier) {
        this.methodModifier = methodModifier;
    }



    
    
    


    @SuppressWarnings("rawtypes")
    private void checkNotEmpty(Object arg,String exceptionMsg) {
        if(exceptionMsg == null) {
            exceptionMsg = "参数不能为空。";
        }
        if(arg == null) {
            throw new NullPointerException(exceptionMsg);
        }
        if(arg instanceof String) {
            String argStr = (String)arg;
            if(argStr.isEmpty()) {
                throw new IllegalArgumentException(exceptionMsg);
            }
        }else if(arg instanceof Collection) {
            Collection collection = (Collection)arg;
            if(collection.isEmpty()) {
                throw new IllegalArgumentException(exceptionMsg);
            }
        }else if(arg instanceof Map) {
            Map map = (Map)arg;
            if(map.isEmpty()) {
                throw new IllegalArgumentException(exceptionMsg);
            }
        }
    }
    
    @SuppressWarnings("rawtypes")
    private boolean checkNotEmpty(Object arg) {
        if(arg == null) {
            return false;
        }
        if(arg instanceof String) {
            String argStr = (String)arg;
            if(argStr.isEmpty()) {
                return false;
            }
        }else if(arg instanceof Collection) {
            Collection collection = (Collection)arg;
            if(collection.isEmpty()) {
                return false;
            }
        }else if(arg instanceof Map) {
            Map map = (Map)arg;
            if(map.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    
    
}