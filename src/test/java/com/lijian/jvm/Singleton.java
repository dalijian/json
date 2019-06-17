package com.lijian.jvm;
// 类加载 的最后一步是初始化，便是为标记为常量值的字段赋值，以及执行<clinit>方法的过程。
//java 虚拟机 会通过加锁来确保类的<clinit> 方法仅被执行一次。
public class Singleton {
    private Singleton(){}
    public static class LazyHolder{
        static final Singleton INSTANCE= new Singleton();

    }
    static {
        System.out.println("LazyHolder.<clinit>");

    }

    public static Object getInstance(boolean flag) {
        if (flag) {
            return new LazyHolder[2];
        }
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----------");
        getInstance(false);

    }
}
