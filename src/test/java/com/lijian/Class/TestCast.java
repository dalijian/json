package com.lijian.Class;

/**
 * Created by shengke on 2016/10/22.
 */
class A {
    public static void show() {
        System.out.println("Class A show() function");
    }
}

class B extends A {
    public static void show() {
        System.out.println("Class B show() function");
    }
}

public class TestCast {

    public static void main(String[] args) {

        TestCast cls = new TestCast();
        Class c = cls.getClass();
        System.out.println(c);

        Object obj = new A();
        B b1 = new B();
        b1.show();

        // casts object
        A a = new A();
        a = A.class.cast(b1);

        System.out.println(obj.getClass());
        System.out.println(b1.getClass());
        System.out.println(a.getClass());
    }
}