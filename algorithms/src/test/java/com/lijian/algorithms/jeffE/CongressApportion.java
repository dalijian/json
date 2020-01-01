package com.lijian.algorithms.jeffE;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class CongressApportion {


    @Test
    public void apportion(){
        int[] pop = new int[50];
        int [] rep= new int[50];
        int r=0;
        LinkedList<Long> queue = new LinkedList<>();

        for (int i = 0; i < pop.length; i++) {
            rep[i] =1;
            queue.add(Math.round(pop[i] / Math.sqrt(2)));
            r=r-1;

        }
        for (int i = 0; i < pop.length; i++) {
            Long s = queue.stream().max(Comparator.comparing(x -> String.valueOf(x))).get();
//            rep[]
        }

    }
}
