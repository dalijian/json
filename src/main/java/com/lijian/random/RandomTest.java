package com.lijian.random;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomTest {
    static  Random random = new Random(10000);
    @Test
    public void random(){

        for (int i = 0; i < 100; i++) {

            int id = 1000000000+random.nextInt(100000000);
            System.out.println(id);

        }
    }

    @Test
    public void random2(){

        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int id = random.nextInt(1000);
            System.out.println(id);

        }
    }

    @Test
    public  void gaussianTest(){
        for (int i = 0; i < 100; i++) {
            System.out.println("milliseconds:"+System.currentTimeMillis());
            System.out.println("nano:"+System.nanoTime());
        }

    }

    /**
     * 正太分布
     * 99.7% 3 个 标准差
     *  若 要 求  99.7% 在 标准差 范围 low~high 内 ，那么 标准差 是 多少 才 满足
     * @param low
     * @param high
     * @return
     */
    public double gaussian(int low ,int high ){

        for (int i = 0; i < 1000; i++) {
            System.out.println(Math.sqrt(0.5)*random.nextGaussian() + (high-low)/2+low);

        }
        return 0D;
    }
}
