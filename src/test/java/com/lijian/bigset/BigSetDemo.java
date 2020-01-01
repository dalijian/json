package com.lijian.bigset;

import java.util.BitSet;

public class BigSetDemo {


    public static void main(String[] args) {
        BitSet bm = new BitSet();

        System.out.println(bm.isEmpty()+"--"+bm.size());

        bm.set(0);

        System.out.println(bm.isEmpty()+"--"+bm.size());

        bm.set(1);

        System.out.println(bm.isEmpty()+"--"+bm.size());

        System.out.println(bm.get(65));

        System.out.println(bm.isEmpty()+"--"+bm.size());

        bm.set(65);

        System.out.println(bm.isEmpty()+"--"+bm.size());

        int kk = 1 << 11;
        System.out.println(kk);

        System.out.println("1<<11="+(1<<11));
        System.out.println("11<1="+(11<<1));

//        将数字 A 的第 k 位设置为1：A = A | (1 << (k - 1))
//        将数字 A 的第 k 位设置为0：A = A & ~(1 << (k - 1))
//        检测数字 A 的第 k 位：A & (1 << (k - 1)) != 0
//        用于理解bitmap中代码


    }
}
