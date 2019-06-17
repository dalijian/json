package com.lijian.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    private  ArrayList<Apple> appleList;


    @Before
    public  void init(){
        this.appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    @Test
    //使用stream 初始化 map
    public void initMap(){
        Map<String, Long> initMap = Stream.of("A", "B", "C", "D").collect(Collectors.toMap(x -> x, x -> 0L));

 
    }
    @Test
    public void ListToMap(){


        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));
        System.out.println(appleMap);
    }

    @Test
    public void group(){
        //List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));

        System.err.println("groupBy:"+groupBy);
//        {1=[Apple{id=1, name='苹果1', money=3.25, num=10}, Apple{id=1, name='苹果2', money=1.35, num=20}], 2=[Apple{id=2, name='香蕉', money=2.89, num=30}], 3=[Apple{id=3, name='荔枝', money=9.99, num=40}]}
    }
    @Test
    public void filter(){
        //保存复合条件的
        System.out.println(appleList);
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("苹果1")).collect(Collectors.toList());

        System.err.println("filterList:"+filterList);
//[Apple{id=1, name='苹果1', money=3.25, num=10}]
    }

    @Test
    public void sum(){
        //计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:"+totalMoney);  //totalMoney:17.48
    }
    @Test
    public void count(){
        //计算 数量
        int sum = appleList.stream().mapToInt(Apple::getNum).sum();
        System.err.println("sum:"+sum);  //sum:100
    }
    @Test
    public void replace(){
//        使用Map 函数式接口  做为 replace , lamda  表达式 没发复用，而且面对复杂条件  lamda 表达式 写的太长不易阅读，还是将 lamda
//        写成 函数式接口 好 ， 可以单独 复用
      List<Apple> list=  appleList.stream().map((x) -> {
            if (x.getId() > 2) {
                x.setId(2);
            }
            return x;
        }).collect(Collectors.toList());
        System.out.println(list);

//        Predicate<Integer> isEven = new Predicate<Integer>() {
//            public boolean test(Integer obj) {
//                return obj % 2 == 0;
//            }
//        };


    }
    ////        写成 函数式接口 好 ， 可以单独 复用
@Test
public void functionReplace(){
    Function<Apple,Apple>  rep = apple -> {
        if (apple.getId() > 2) {
            apple.setId(2);

        }
        return apple;
    };
    List<Apple> list2 =appleList.stream().map(rep).collect(Collectors.toList());
    System.out.println(list2);

}
@Test
public void flatToMap(){
    List<Apple> list =Stream.of(appleList, appleList).flatMap(x -> x.stream()).collect(Collectors.toList());
//    System.out.println(list);
    //flatMap  可以用来 降维 ，
    List<String> stringList =list.stream().flatMap(x->Stream.of(x.name)).distinct().collect(Collectors.toList());
    System.out.println(stringList);
}
////Collectors.groupBy  可以用来 增维
@Test
public  void groupp(){
    Map<String, Map<Integer, Map<Integer, Map<BigDecimal, List<Apple>>>>> list = appleList.stream().collect(Collectors.groupingBy(x -> x.getName()
            , Collectors.groupingBy(x -> x.getNum()
                    , Collectors.groupingBy(x -> x.getId()
                            , Collectors.groupingBy(x -> x.getMoney())))));
    System.out.println(list);
}
@Test
public void testCollect(){

    Map<String, Map<Integer, List<Apple>>> peopleByStateAndCity
        = Stream.of(appleList,appleList).flatMap(x->x.stream()).collect(Collectors.groupingBy(x->x.getName(), Collectors.groupingBy(x->x.getNum())));
    System.out.println(peopleByStateAndCity);
    Map<String, Long> list = appleList.stream().collect(Collectors.groupingBy(x -> x.getName(), Collectors.counting()));
    Map<String, List<Integer>> lists = appleList.stream().collect(Collectors.groupingBy(x -> x.getName()
            , Collectors.mapping(x -> x.getNum(), Collectors.toList())));

}
    @Test
    public void toList() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");

        List<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//        List<String> list2 = stream.collect(() -> new ArrayList(), x -> x.add(), x -> x.addAll());
//        collect(Supplier<R> supplier,
//                BiConsumer<R, ? super T> accumulator,
//                BiConsumer<R, R> combiner
    }

    @Test
    public void min(){
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
    }

    @Test
    public void mapToList(){

        Map<Integer,Apple> map = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));
        List<Apple> list =map.values().parallelStream().collect(Collectors.toList());
        System.out.println(list);
        list =new ArrayList(map.values());
        System.out.println(list);
    }

    @Test
    public void join(){
        String result = Stream.of("lijian", "GG").collect(Collectors.joining(","));
        System.out.println(result);
    }
    @Test
    public void testListAdd(){
        appleList.add(0, new Apple());
        System.out.println(appleList);
    }

    @Test
    public void InegeterMax(){
        System.out.println(Integer.MAX_VALUE);
    }
    class Apple {
        private Integer id;
        private String name;
        private BigDecimal money;
        private Integer num;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Apple(Integer id, String name, BigDecimal money, Integer num) {
            this.id = id;
            this.name = name;
            this.money = money;
            this.num = num;
        }

        public Apple() {
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", money=" + money +
                    ", num=" + num +
                    '}';
        }

    }
}
