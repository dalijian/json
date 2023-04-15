package com.lijian.design_pattern.structure.proxy;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    public static void main(String[] args) {
        Hello simpleHello = new SimpleHello();
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                // 需要 执行 代理接口 的 实现类 ，不然 proxy 为 null
              return   method.invoke(simpleHello, args);

            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[]{Hello.class}, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }


//    public static void main(String[] args) {
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println(method);
////                if (method.getName().equals("morning")) {
////                    System.out.println("Good morning, " + args[0]);
////                }
//                method.invoke(proxy, args);
//                return null;
//            }
//        };
//        Driver driver = (Driver) Proxy.newProxyInstance(
//                Driver.class.getClassLoader(), // 传入ClassLoader
//                new Class[] { Driver.class }, // 传入要实现的接口
//                handler); // 传入处理调用方法的InvocationHandler
//        try {
//            driver.acceptsURL("lijian");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void traceBinarySearch() {
        Object[] elements = new Object[1000];

        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Comparable proxy = (Comparable) Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        int result = Arrays.binarySearch(elements, key);

        if (result > 0) {
            System.out.println(elements[result]);
        }
    }
    @Test
    public void traceBinarySearch2() {
            StringBuilder proxy = (StringBuilder) Proxy.newProxyInstance(null, new Class[]{CharSequence.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return method.invoke(args);
                }
            });
        proxy.append("11");

            proxy.length();
    }

    @Test
    public void autoCloseTest() throws FileNotFoundException {
        FileInputStreamSelf byteArrayInputStream = new FileInputStreamSelf(new File("C:\\Users\\lijian\\IdeaProjects\\json\\design_pattern\\src\\test\\java\\com\\lijian\\design_pattern\\structure\\proxy\\readme.md"));

        try {
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        AutoCloseable proxy = (AutoCloseable) Proxy.newProxyInstance(null, new Class[]{AutoCloseable.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println(proxy);
//                return method.invoke(args);
//            }
//        });

    }

    public static class FileInputStreamSelf extends FileInputStream {


        public FileInputStreamSelf(File file) throws FileNotFoundException {
            super(file);
        }

        @Override
        public void close() throws IOException {
            super.close();
        }
    }
}

