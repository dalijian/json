package com.lijian.guava;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.Weigher;
import com.google.common.graph.Graph;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


//你愿意消耗一些内存空间来提升速度。
//        你预料到某些键会被查询一次以上。
//        缓存中存放的数据总量不会超出内存容量
public class CacheTest {
    Logger log = LoggerFactory.getLogger(CacheTest.class);
    @Test
    public void test() throws ExecutionException {

        LoadingCache<String, String> cacheMap = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
                .build(
//                       当 key 不存在时 ， 自动   加载
                        new CacheLoader<String, String>() {
                            public String load(String str)  {
                                return str+1;
                            }
                        });


        System.out.println(cacheMap.asMap());
        cacheMap.put("name", "lijian");
        System.out.println(cacheMap.asMap());


//        asMap视图的任何方法都不能保证缓存项被原子地加载到缓存中。
//        进一步说，asMap视图的原子运算在Guava Cache的原子加载范畴之外，
//        所以相比于Cache.asMap().putIfAbsent(K,
//                V)，Cache.get(K, Callable<V>) 应该总是优先使用。
        String result = cacheMap.get("li", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        System.out.println(result);
    }

//    根据 权重 回收
    @Test
    public void weight(){
                        //如果你的缓存值，占据完全不同的内存空间，你可以使用CacheBuilder.weigher(Weigher)指定一个权重函数，
        // 并且用CacheBuilder.maximumWeight(long)指定最大总重。
        // 在权重限定场景中，除了要注意回收也是在重量逼近限定值时就进行了，
        // 还要知道重量是在缓存创建时计算的，因此要考虑重量计算的复杂度。
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .maximumWeight(100000)
                .weigher(new Weigher<String, String>() {
                    public int weigh(String k, String g) {
                        return g.length();
                    }
                })
                .build(
                        new CacheLoader<String, String>() {
                            public String load(String key) { // no checked exception
                                return createExpensiveGraph(key);
                            }

                            private String createExpensiveGraph(String key) {
                                return key+1;
                            }
                        });
    }

    /**
     * TTL->time to live
     * Access time => Write/Update/Read
     */
    @Test
    public void testEvictionByAccessTime() throws ExecutionException, InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(CacheLoaderCreatetor.createCacheLoader());
        cache.getUnchecked("wangji");
        TimeUnit.SECONDS.sleep(3);
        Employee employee = cache.getIfPresent("wangji"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));
        cache.getUnchecked("guava");

        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

    }

    /**
     * Write time => write/update
     */
    @Test
    public void testEvictionByWriteTime() throws ExecutionException, InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(CacheLoaderCreatetor.createCacheLoader());
        cache.getUnchecked("guava");
        TimeUnit.SECONDS.sleep(2);
        Employee employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        cache.put("guava", new Employee("guava", "guava" + "dept", "guava" + "id")); //手动插入
        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

        cache.put("guava", new Employee("guava", "guava" + "dept", "guava" + "id"));
        TimeUnit.SECONDS.sleep(2);
        employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));

    }

//        基于引用清除
    @Test
    public void testWeakKey() throws ExecutionException, InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .weakValues()
                .weakKeys()
                .softValues()
                .build(CacheLoaderCreatetor.createCacheLoader());
        cache.getUnchecked("guava");
        cache.getUnchecked("wangji");

        System.gc();
        TimeUnit.MILLISECONDS.sleep(100);
        Employee employee = cache.getIfPresent("guava"); //不会重新加载创建cache
        System.out.println("被销毁：" + (employee == null ? "是的" : "否"));
    }

//    清除监听器

//    默认情况下，监听器方法是在移除缓存时同步调用的。
//    因为缓存的维护和请求响应通常是同时进行的，代价高昂的监听器方法在同步模式下会拖慢正常的缓存请求。
//    在这种情况下，你可以使用RemovalListeners.asynchronous(RemovalListener, Executor)把监听器装饰为异步操作

    @Test
    public void testCacheRemovedNotification() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        RemovalListener<String, String> listener = notification ->
        {
            if (notification.wasEvicted()) {
                RemovalCause cause = notification.getCause();
                System.out.println("remove cacase is :" + cause.toString());
                System.out.println("key:" + notification.getKey() + "value:" + notification.getValue());
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)// 添加删除监听
                .build(loader);
        cache.getUnchecked("wangji");
        cache.getUnchecked("wangwang");
        cache.getUnchecked("guava");
        cache.getUnchecked("test");
        cache.getUnchecked("test1");
    }



//    刷新表示为键加载新值，这个过程可以是异步的。
//    在刷新操作进行时，缓存仍然可以向其他线程返回旧值，
//    而不像回收操作，读缓存的线程必须等待新值加载完成

    @Test
    public void testCacheRefresh() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CacheLoader<String, Long> cacheLoader = CacheLoader
                .from(k -> {
                    counter.incrementAndGet();
                    System.out.println("创建 key :" + k);
                    return System.currentTimeMillis();
                });
        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS) // 2s后重新刷新
                .build(cacheLoader);


        Long result1 = cache.getUnchecked("guava");
        TimeUnit.SECONDS.sleep(3);
        Long result2 = cache.getUnchecked("guava");
        System.out.println(result1.longValue() != result2.longValue() ? "是的" : "否");

    }

}

class Employee {
    private final String name;
    private final String dept;
    private final String empID;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getEmpID() {
        return empID;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", this.getName()).add("Department", getDept())
                .add("EmployeeID", this.getEmpID()).toString();
    }

}

