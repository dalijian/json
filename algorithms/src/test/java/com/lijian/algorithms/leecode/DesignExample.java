package com.lijian.algorithms.leecode;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class DesignExample {

    class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
    @Test
    public void LRUCacheTest(){

        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int result = cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        int result2 = cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        int result3 = cache.get(1);       // 返回 -1 (未找到)
        int result4 = cache.get(3);       // 返回  3
        int result5 = cache.get(4);       // 返回  4

    }
}
