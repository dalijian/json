package com.lijian.algorithms.charpter_1;

public class UF {


    protected int[] id;

    protected int count;

    public UF(int count) {
        this.count = count;
        this.id = new int[count];

        for (int i = 0; i < count; i++) {
            id[i] = i;
        }

    }

    public UF() {
    }

    public int count(){

        return count;

    }

    public boolean connected(int p, int q) {

        return find(p)==find(q);
    }

    public int find(int p) {
        return 0;
    }

    public void union(int p, int q) {


    }


}
