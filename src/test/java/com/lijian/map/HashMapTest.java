package com.lijian.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapTest {

    @Test
    public void test(){
        Map<Person,String> personMap = new HashMap<>();
        personMap.put(new Person("lijian", 20), "lijian");
        personMap.put(new Person("lijian", 20), "23");
        System.out.println(personMap);

    }
//    根据value  排序
    @Test
    public void sortByValue(){
        Map<String, Integer> map = new HashMap<>();
        map.put("王二", 8);
        map.put("沈吴", 2);
        map.put("小菜", 3);
        map.put("大鸟", 1);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry s : entrySet)
        {
            System.out.println(s.getKey()+"--"+s.getValue());
        }

        System.out.println("============排序后============");

        //////借助list实现hashMap排序//////

        //注意 ArrayList<>() 括号里要传入map.entrySet()
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                //按照value值，重小到大排序
//                return o1.getValue() - o2.getValue();

                //按照value值，从大到小排序
//                return o2.getValue() - o1.getValue();

                //按照value值，用compareTo()方法默认是从小到大排序
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        //注意这里遍历的是list，也就是我们将map.Entry放进了list，排序后的集合
        for (Map.Entry s : list)
        {
            System.out.println(s.getKey()+"--"+s.getValue());
        }

    }

    @Test
    public void test2(){


        Map<Integer, Integer> map = Stream.of("1123356743".split("")).collect(Collectors.toMap(x -> Integer.valueOf(x), y -> 0, (k1, k2) -> k1));

        // 默认 是  insert-order ,   显示 支持 access-order
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>(map);

        System.out.println(linkedHashMap);
        linkedHashMap.put(1, 7);
        System.out.println(linkedHashMap);

        System.out.println("**************");

// 10是初始大小，0.75是装载因子，true是表示按照访问时间排序
        HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);

        m.put(3, 26);
        m.get(5);

        for (Map.Entry e : m.entrySet()) {
            System.out.println(e.getKey());
        }
        System.out.println(m);
    }

}

    class Person {

        private String  name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

