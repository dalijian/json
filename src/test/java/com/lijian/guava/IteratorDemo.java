package com.lijian.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class IteratorDemo {


    @Test
    public void iter(){

        Iterator iterator =new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }

    @Test
    public void iters(){

    }

    @Test
    public void filterTest(){
        List<String> list = new ArrayList<>();
        Iterator<String> startPElements = Iterators.filter(list.iterator(), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("P");
            }
        });
    }
}
