package com.lijian.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    private ArrayList<Apple> appleList;


    @Before
    public void init() {
        this.appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    @Test
    //使用stream 初始化 map
    public void initMap() {
        Map<String, Long> initMap = Stream.of("A", "B", "C", "D").collect(Collectors.toMap(x -> x, x -> 0L));

    }


    @Test
    public void initMap2() {


        Map<String, String> headerMap = Stream.of("运营中心名称"
                , "单位名称"
                , "单位服务模式"
                , "未接通总数"
                , "接通总数"
                , "拨打电话次数"
                , "接入时间"
                , "报警描述"


        ).collect(Collectors.toMap(x -> x, x -> ""));

        System.out.println(headerMap);
    }

    @Test
    public void ListToMap() {

        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        System.out.println(appleMap);
    }

    //     将 map 中 key 相同的 value  封装 成 List
    @Test
    public void ListToMap2() {
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 自定义的 函数型接口 将key 相同的 value 转为 List
         */
        BinaryOperator<List<String>> mergeList = (a1, a2) -> {
            a1.addAll(a2);
            return a1;
        };
        Map<Integer, List<String>> result = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> Stream.of(a.getName()).collect(Collectors.toList()), mergeList));
        System.out.println(result);

//        Map<Integer, String> resultMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a.getName(), (k1, k2) -> k1 + "," + k2));
//        System.out.println(resultMap);
    }

    @Test
    public void group() {
        //List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));

        System.err.println("groupBy:" + groupBy);
//        {1=[Apple{id=1, name='苹果1', money=3.25, num=10}, Apple{id=1, name='苹果2', money=1.35, num=20}], 2=[Apple{id=2, name='香蕉', money=2.89, num=30}], 3=[Apple{id=3, name='荔枝', money=9.99, num=40}]}
    }

    @Test
    public void filter() {
        //保存复合条件的
        System.out.println(appleList);
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("苹果1")).collect(Collectors.toList());

        System.err.println("filterList:" + filterList);
//[Apple{id=1, name='苹果1', money=3.25, num=10}]
    }

    @Test
    public void sum() {
        //计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney);  //totalMoney:17.48
    }

    @Test
    public void count() {
        //计算 数量
        int sum = appleList.stream().mapToInt(Apple::getNum).sum();
        System.err.println("sum:" + sum);  //sum:100
    }

    @Test
    public void replace() {
//        使用Map 函数式接口  做为 replace , lamda  表达式 没发复用，而且面对复杂条件  lamda 表达式 写的太长不易阅读，还是将 lamda
//        写成 函数式接口 好 ， 可以单独 复用
        List<Apple> list = appleList.stream().map((x) -> {
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
    public void functionReplace() {
        Function<Apple, Apple> rep = apple -> {
            if (apple.getId() > 2) {
                apple.setId(2);

            }
            return apple;
        };
        List<Apple> list2 = appleList.stream().map(rep).collect(Collectors.toList());
        System.out.println(list2);

    }

    @Test
    public void flatToMap() {
        List<Apple> list = Stream.of(appleList, appleList).flatMap(x -> x.stream()).collect(Collectors.toList());
//    System.out.println(list);
        //flatMap  可以用来 降维 ，
        List<String> stringList = list.stream().flatMap(x -> Stream.of(x.name)).distinct().collect(Collectors.toList());
        System.out.println(stringList);
    }

    ////Collectors.groupBy  可以用来 增维
    @Test
    public void groupp() {
        Map<String, Map<Integer, Map<Integer, Map<BigDecimal, List<Apple>>>>> list = appleList.stream().collect(Collectors.groupingBy(x -> x.getName()
                , Collectors.groupingBy(x -> x.getNum()
                        , Collectors.groupingBy(x -> x.getId()
                                , Collectors.groupingBy(x -> x.getMoney())))));
        System.out.println(list);
    }

    @Test
    public void testCollect() {

        Map<String, Map<Integer, List<Apple>>> peopleByStateAndCity
                = Stream.of(appleList, appleList).flatMap(x -> x.stream()).collect(Collectors.groupingBy(x -> x.getName(), Collectors.groupingBy(x -> x.getNum())));
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
    public void min() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
    }

    @Test
    public void mapToList() {

        Map<Integer, Apple> map = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        List<Apple> list = map.values().parallelStream().collect(Collectors.toList());
        System.out.println(list);
        list = new ArrayList(map.values());
        System.out.println(list);
    }

    @Test
    public void join() {
        String result = Stream.of("lijian", "GG").collect(Collectors.joining(","));
        System.out.println(result);
    }

    @Test
    public void testListAdd() {
        appleList.add(0, new Apple());
        System.out.println(appleList);
    }

    @Test
    public void testdisctinct() {

        System.out.println(Stream.of("a,b,c,d,f,s,a".split(",")).collect(Collectors.toList()));
        System.out.println(Stream.of("a,b,c,d,f,s,a".split(",")).distinct().collect(Collectors.toList()));
    }


    // 根据指定的 key, 去重 map
    @Test
    public void MapDistinctByKey() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map2 = new LinkedHashMap<>();

        map2.put("createTime", "123456");
        map2.put("name", "lijian");


        Map<String, Object> map3 = new LinkedHashMap<>();

        map3.put("createTime", "123456");
        map3.put("name", "lijian");

        Map<String, Object> map4 = new LinkedHashMap<>();

        map4.put("createTime", "654321");
        map4.put("name", "GG");


        Map<String, Object> map5 = new LinkedHashMap<>();

        map2.put("createTime", "126");
        map2.put("name", "KK");


        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);


//        Predicate<Map<String,Object>> distinctByKey = map -> {
//            Map<Object,Boolean> seen = new ConcurrentHashMap<>();
//            return t -> seen.putIfAbsent(map.get("createTime"), Boolean.TRUE) == null;
//        };
//        list.stream().filter(distinctByKey).collect(Collectors.toList());
    }


//    根据指定字段去重
    @Test
    public void distinct(){

            List<Book> list = new ArrayList<>();
            {list.add(new Book("Core Java", "200"));
                list.add(new Book("Core Java", "200"));
                list.add(new Book("Core Java", "300"));
                list.add(new Book("Learning Freemarker", "150"));
                list.add(new Book("Spring MVC", "200"));
                list.add(new Book("Hibernate", "300"));
            }
            list.stream().filter(distinctByKey(b -> b.getName()))
                    .forEach(b -> System.out.println(b.getName()+ "," + b.getPrice()));
    }
    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        System.out.println("这个函数将应用到每一个item");

        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    @Test
    public void treeMapDistinct(){
        List<Book> list = new ArrayList<>();
        {
            list.add(new Book("Core Java", "200"));
            list.add(new Book("Core Java", "300"));
            list.add(new Book("Learning Freemarker", "150"));
            list.add(new Book("Spring MVC", "200"));
            list.add(new Book("Hibernate", "300"));
        }
        //使用TreeSet去重
        List<Book> unique1 = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName()))),
                        ArrayList::new));

        System.out.println(unique1);

    }

    @Test
    public void InegeterMax() {
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

    @Test
    public void test(){
        List<String> result = Stream.of(",li,jian".split(","))
                .filter(x -> StringUtils.isNotBlank(x))
                .collect(Collectors.toList());
        result.replaceAll(s -> s);
        System.out.println(result);

    }
}

class Book {

    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Book(String name, String price) {

        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}