package com.lijian.algorithms;
// 实现 各种不同数据元素之间独立的比较策略
public interface Strategy {
    //判断两个数据元素是否相等
    public boolean equal(Object obj1, Object obj2);

    /**
     * 比较两个数据元素的大小
     * 如果 obj1 < obj2 返回-1
     * 如果 obj1 = obj2 返回 0
     * 35* 如果 obj1 > obj2 返回 1
     */
    public int compare(Object obj1, Object obj2);
}