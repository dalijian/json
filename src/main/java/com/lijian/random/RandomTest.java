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
}
