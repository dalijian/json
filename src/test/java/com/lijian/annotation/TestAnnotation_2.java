package com.lijian.annotation;

import com.lijian.annotation.bean.Animal;
import com.lijian.annotation.bean.Person;
import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.Set;

public class TestAnnotation_2 {
    public static String PACKAGE_PATH = "com.lijian.annotation";

    @Test
    public void test() throws IllegalAccessException {
        Person person = new Person("Lijian");
        //        URL url = new URL("jar:file:/C:/users/lijian/desktop/classLoader/mysql-connector-java-8.0.20.jar!/");
        //        URLClassLoader loader = new URLClassLoader(Stream.of(url).toArray(URL[]::new));
        // 扫包
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                //                .addClassLoader(loader)
                //                .addUrls(url)
                .forPackages(PACKAGE_PATH) // 指定路径URL
                .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
        );

        Set<Class<? extends Animal>> set = reflections.getSubTypesOf(Animal.class);

        for (Class clazz : set) {
            // 拿到 类 下 的 注解
            Set<Field> fields = reflections.getFieldsAnnotatedWith(NotSelfBlank.class);

        }
        System.out.println("getSubTypesOf:" + set);
        // 获取带有特定注解对应的字段
        Set<Field> fields = reflections.getFieldsAnnotatedWith(NotSelfBlank.class);
        for (Field field : fields) {
            System.out.println(field.get(person));
            Class<?> type = field.getType();
            System.out.println(type.getName());
            System.out.println(type.getSimpleName());
            System.out.println(type.getTypeName());
            Assert.assertEquals(type, String.class);
            field.getName(); // 拿到 字段 值
            // 这里 最好 将 验证 是否 符号 Annotation 方法 放到 另一个 Annotation 中 ，实现 类似 方法 调用 的 功能，不要 在这 写死

            NotSelfBlank myFieldAnnotation = field.getAnnotation(NotSelfBlank.class);
            System.out.println(myFieldAnnotation.message());
        }

        System.out.println("getFieldsAnnotatedWith:" + fields);


    }


    /***
     *  java 1.6 不支持   Reflection  ,  改用 扫描jar包 读 文件
     * @throws IllegalAccessException
     */
    @Test
    public void test2() throws IllegalAccessException {
        Person person = new Person("Lijian");
        ClassPathUtils classPathUtils = new ClassPathUtils();

        Set<Class<?>> set = ClassPathUtils.getClasses(PACKAGE_PATH);


        for (Class clazz : set) {
            // 拿到 类 下 的 注解
            Field[] fields = clazz.getFields();
            for (int i = 0; i < fields.length; i++) {
                validation(fields[i], clazz);
            }

        }
        System.out.println("验证完成");


    }

    static Person person = new Person("lijian", 0.0F, 20);

    private void validation(Field field, Class clazz) throws IllegalAccessException {

        NotSelfBlank myFieldAnnotation = field.getAnnotation(NotSelfBlank.class);
        if (myFieldAnnotation == null) {
            return;

        }
        Class<?> type = field.getType();
        String typeSimpleName = type.getSimpleName();
        switch (typeSimpleName) {

            case "String":
                String value = (String) field.get(person);
                if (value == null || value == "") {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + message);
                }
                break;
            case "float":
                Float aFloat = (Float) field.get(person);
                if (aFloat == null || aFloat == 0F) {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + "." + message);
                }
                break;
            case "Integer":
                Integer integer = (Integer) field.get(person);
                if (integer == null || integer == 0F) {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + message);
                }
                break;
        }

    }

    /**
     * 验证 Annotation
     * @throws IllegalAccessException
     */
    @Test
    public void validationBeanTest() throws IllegalAccessException {

        Person person = new Person("lijian", 12f, 20);

        validationBean(person, Person.class);
    }

    public void validationBean(Object object, Class clazz) throws IllegalAccessException {

        // 拿到 类 下 的 注解
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            validation(fields[i], clazz, object);
        }

        System.out.println("验证完成");


    }

    private void validation(Field field, Class clazz, Object object) throws IllegalAccessException {

        NotSelfBlank myFieldAnnotation = field.getAnnotation(NotSelfBlank.class);
        if (myFieldAnnotation == null) {
            return;
        }
        Class<?> type = field.getType();
        if (type == String.class) {

        }
        if (type == int.class) {
            System.out.println("****************************INTEGER************************");
        }
        String typeSimpleName = type.getSimpleName();
        switch (typeSimpleName) {

            case "String":
                String value = (String) field.get(object);
                if (value == null || value == "") {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + message);
                }
                break;
            case "float":
                Float aFloat = (Float) field.get(object);
                if (aFloat == null || aFloat == 0F) {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + "." + message);
                }
                break;
            case "Integer":
                Integer integer = (Integer) field.get(object);
                if (integer == null || integer == 0F) {
                    String message = myFieldAnnotation.message();
                    String className = clazz.getName();
                    throw new RuntimeException(className + message);
                }
                break;
        }

    }
}
