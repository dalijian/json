package com.lijian.algorithms.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MiddleNum {

//1. 小顶堆 中的数据 大于 大 顶堆 中的 数据
    PriorityQueue<Integer> maxqueue;
    PriorityQueue<Integer> minqueue;
    Integer size =0;

    public MiddleNum(PriorityQueue<Integer> maxqueue, PriorityQueue<Integer> minqueue) {
        this.maxqueue = maxqueue;
        this.minqueue = minqueue;
    }

    public void add(Integer integer) {

        size++;
        Integer min = minqueue.peek();
        Integer max = maxqueue.peek();

        if (min==null||integer < min) {
            minqueue.offer(integer);
        }else {
            maxqueue.offer(integer);
        }

        // 调整 大小顶堆
        while (minqueue.size() > ((size-1) / 2) ) {
            maxqueue.offer(minqueue.remove());
        }

        while (maxqueue.size() > size / 2 ) {
            minqueue.offer(maxqueue.remove());
        }


    }

    public Integer getMiddle() {

//        1. 当前 总数 是 偶数  n/2 在 大顶堆，n/2 在 小 顶 堆      1，2，3，4，5，6 （123 小顶堆） （456大顶堆）
//        2. 当 总数 是 奇数 时 n/2 在 大顶堆， n/2 +1 在 小 顶 堆  1，2，3，4，5，6，7（1234 小顶堆）（456 大顶堆）

        if (size % 2 == 0) {
          return   (minqueue.peek() +maxqueue.peek())/2;
        }
        return  minqueue.peek();


    }

    public static void main(String[] args) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.comparing(x ->- x));
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.comparing(x -> x));


        MiddleNum middleNum = new MiddleNum(maxQueue, minQueue);


        middleNum.add(1);
        middleNum.add(10);
        middleNum.add(8);
        middleNum.add(6);
        middleNum.add(3);
        middleNum.add(6);
        middleNum.add(4);
        System.out.println(middleNum.getMiddle());
    }
}