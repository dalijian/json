package com.lijian.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {


        public static void main(String[] args) {

            // 创建两个Person对象，它们的id分别是101和102。
            Person2 p1 = new Person2(101);
            Person2 p2 = new Person2(102);
            // 新建AtomicReference对象，初始化它的值为p1对象
            AtomicReference ar = new AtomicReference(p1);
            //更改p1的id.
            p1.setId(106);
            // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
            ar.compareAndSet(p1, p2);

            Person2 p3 = (Person2)ar.get();
            System.out.println("p3 is "+p3);
            System.out.println("p3.equals(p1)="+p3.equals(p1));
        }


   private static class Person2 {
        volatile long id;

        public Person2(long id) {
            this.id = id;
        }

       @Override
       public String toString() {
           return "Person2{" +
                   "id=" + id +
                   '}';
       }

       public void setId(long id) {
            this.id = id;
        }

    }
}
