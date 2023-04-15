package com.lijian.test.poi.world.world2pdf;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * 修改 class文件 重新打包
 */
public class Test {
//1.项目中引入依赖
//            <dependency>
//    <groupId>org.javassist</groupId>
//    <artifactId>javassist</artifactId>
//    <version>3.27.0-GA</version>
//</dependency>
//
    //    2.修改指定类中的返回值
//    3.把aspose-words-20.12.0-jdk17.jar后缀改成rar/zip等能解压的格式，解压为aspose-words-20.12.0-jdk17（文件名随意）
//            4.把刚刚生成zzZDz.class文件替换到com.aspose.words中
//5.删除aspose-words-20.12.0-jdk17中META-INF中的.RSA和.SF后缀的文件
//6.进入aspose-words-20.12.0-jdk17的根目录，执行命令jar cvfm aspose-words-20.12-jdk17-crack.jar META-INF/MANIFEST.MF com/
//            7.将生成的jar文件放到maven库中，mvn install:install-file -Dfile="/Volumes/KESU/安装程序/aspose-words-20.12.0-java/lib/aspose-words-20.12-jdk17-crack.jar" -DgroupId=com.aspose -DartifactId=aspose-words -Dversion=20.12 -Dpackaging=jar
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool.getDefault().insertClassPath("aspose-words-20.12.0-jdk17.jar");
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzZDZ");//找到指定类
        //找到指定方法
        CtMethod zzZ4u = zzZJJClass.getDeclaredMethod("zzZ4n");
        CtMethod zzZ4t = zzZJJClass.getDeclaredMethod("zzZ4m");
        //修改返回值
        zzZ4u.setBody("{return 1;}");
        zzZ4t.setBody("{return 1;}");
        //输出到指定路径
        zzZJJClass.writeFile("aspose-words-20.12.0-java");
    }
}
