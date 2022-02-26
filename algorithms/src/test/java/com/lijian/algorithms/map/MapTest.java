package com.lijian.algorithms.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

public class MapTest {


    @Test
    public void mapTest(){


        HashMap<Long, Long> map = new HashMap<>(1, 0.000001F);


        while (true) {
            long l = 0;
            l++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(new Random().nextLong(), new Random().nextLong());
            if (l > 1000) {
                break;
            }
        }
    }
    @Test
    public void mapTest2(){


        HashMap<Long, Long> map = new HashMap<>(1, 100000000F);
        long l=0;

        while (true) {

            l++;

            map.put(new Random().nextLong(), new Random().nextLong());

            if (l > 1000000L) {
                break;
            }
        }
    }
}
