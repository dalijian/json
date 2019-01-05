package com.lijian.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import org.junit.Test;

public class DemoTest {
    public static void main(String[] args) {

            Model model = new Model("2018-12-1");
            Model model2 = new Model("2018-12-2");
            Model model3 = new Model("2018-12-3");
            Model model4 = new Model("2018-12-4");
            Model model5 = new Model("2018-12-5");
            List<Model> list=new ArrayList<Model>();
            list.add(model3);
            list.add(model2);
            list.add(model);
            list.add(model3);
            list.add(model5);

        System.out.println(list);
        Collections.sort(list, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        System.out.println(list);
        System.out.println("2018-1".matches("2018-12-1"));
    }

    @org.junit.Test
    public void test(){
        Long longTime = new Date().getTime();
        int day = (int) (longTime/(1000*60*60*24));
        System.out.println(day);
        System.out.println(  (longTime/(1000*60*60*24))
        );
    }


    @org.junit.Test
    public void testDate(){
        String str ="2010-5-27";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date =null;
        try {
           date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, 90);
        date=calendar.getTime();
        System.out.println(simpleDateFormat.format(date));
    }

    @org.junit.Test
    public void testA(){
        final List<String> strList = new ArrayList<>();
        strList.add("hello");
        strList.add("world");

//      List<String> unmodifiableStrList =  List.of("lijain", "kk");
        String name="lijain";
        name = null;
        System.gc();

        for (int i = 0; i < 1000000000; i++) {
            ("a"+i).intern();
        }
        try {

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    @Override
   public void  finalize(){
        System.out.println("wellcome");
    };

//    @org.junit.DemoTest
//    public void testB() {
//        Proxy.newProxyInstance(Cat.class.getClassLoader(),animal.class,  new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (method.equals("eat")){
//                    int eat =0;
//                    eat ++;
//
//                }
//                 return method.invoke(proxy,args);
//
//            }
//        });
//    }
    interface  animal{
       void eat();

    }
    class Cat implements animal{

        @Override
        public void eat() {
            System.out.println("cat eat fish");
        }
    }
//    线程安全的计数器
    class Counter {
        private final AtomicLong counter = new AtomicLong();
        public void increase(){
            counter.incrementAndGet();
        }
    }
    @org.junit.Test
    public void finalTest(){
        final String name ;
        String grade;
        name = "lijain";
        grade = "90";
        System.out.println(name);
//        name = "jack";
        grade = "80";


//    class CompactCounter {
//        private volatile long counter ;
//        private static final AtomicLongFieldUpdater<CompactCounter> upcounter = AtomicLongFieldUpdater.newUpdater(CompactCounter.class,"lijain");
//
//        public void increase(){
//            upcounter.incrementAndGet(this);
//        }
    }
    @Test
    public void hashTree(){
        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.put("lijian", "123");
        treeMap.put("ktmoon", "345");
        treeMap.put("zzg", "afd");
        treeMap.put("aas", "bbc");
        treeMap.put("435", "af");
        System.out.println(treeMap);


//        根据key降序排列
        Map<String, String> mapOrderByKeyDesc = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        mapOrderByKeyDesc.put("abc", "123");
        mapOrderByKeyDesc.put("bca", "345");
        mapOrderByKeyDesc.put("cab", "432");
        System.out.println(mapOrderByKeyDesc);

//        根据 value 排序
        Map<String,String> mapOrderByValue = new HashMap<>();
        mapOrderByValue.put("911", "b");
        mapOrderByValue.put("234", "a");
        mapOrderByValue.put("543", "c");
        mapOrderByValue.put("432", "w");
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(mapOrderByValue.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        System.out.println(mapOrderByValue);
        System.out.println(list);
    }
}
