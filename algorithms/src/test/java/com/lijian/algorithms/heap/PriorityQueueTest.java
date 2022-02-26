package com.lijian.algorithms.heap;

import org.junit.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    @Test
    public  void priorityQueue() {
        // 默认 由 小到大
        PriorityQueue<String> q = new PriorityQueue<String>();
        //入列
        q.offer("1");
        q.offer("2");
        q.offer("5");
        q.offer("3");
        q.offer("4");

        //出列
        System.out.println(q.poll());  //1
        System.out.println(q.poll());  //2
        System.out.println(q.poll());  //3
        System.out.println(q.poll());  //4
        System.out.println(q.poll());  //5
    }


    @Test
    public  void priorityQueueDesc() {
        // 由 大到小
        PriorityQueue<String> q = new PriorityQueue<String>((o1, o2) -> -o1.compareTo(o2));
        //入列
        q.offer("1");
        q.offer("2");
        q.offer("5");
        q.offer("3");
        q.offer("4");

        //出列
        System.out.println(q.poll());  //1
        System.out.println(q.poll());  //2
        System.out.println(q.poll());  //3
        System.out.println(q.poll());  //4
        System.out.println(q.poll());  //5
    }
}
