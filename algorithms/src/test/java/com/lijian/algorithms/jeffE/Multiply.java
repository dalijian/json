package com.lijian.algorithms.jeffE;

import com.google.common.cache.CacheBuilder;
import com.google.common.math.IntMath;
import org.junit.Test;

public class Multiply {

    // duplation and mediation
    @Test
    public void peasantMultiply(){

        int x = 89;
        int y = 1002;

        int product=0;
        while (x > 0) {
            // x  is odd
            if (IntMath.mod(x, 2) == 1) {
                product+= y;
            }
            y=y+y;
            x=x/2;
        }
        System.out.println(product);
    }


    @Test
    public void peasantMultiplyRecursion(){


        int x=5;
        int y=10;

        int product = resursion(x, y);

        System.out.println(product);
    }

    private int resursion(int x, int y) {

        if (x == 0) {
            return 0;
        }

        if (IntMath.mod(x, 2) == 0) {
            return (x / 2) * (y + y);
        }
        if (IntMath.mod(x, 2) == 1) {
            return (x / 2) * (y + y) + y;
        }
        return 0;
    }
}
