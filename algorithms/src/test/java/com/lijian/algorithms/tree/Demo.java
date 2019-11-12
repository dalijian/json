package com.lijian.algorithms.tree;

import org.junit.Test;

public class Demo {

    @Test
    public void print(){
        int tree =0;
        printTree( tree);
    }

    private void printTree(int tree) {
        System.out.println(tree);
        printTree(tree);
    }
}
