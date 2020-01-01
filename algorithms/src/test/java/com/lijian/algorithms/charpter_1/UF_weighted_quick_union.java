package com.lijian.algorithms.charpter_1;

public class UF_weighted_quick_union extends UF {


    public UF_weighted_quick_union(int count) {

        count = count;

        id = new int[count];

        for (int i = 0; i < count; i++) {
            id[i]=i;

        }

        sz = new int[count];

        for (int i = 0; i < count; i++) {
            sz[i] = i;
        }


    }



    private int [] sz;


    public int find(int p) {

        while (p != id[p]) {

            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {

        int i = find(p);
        int j = find(q);

        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] =j;
            sz[j] += sz[i];

        }else{

            id[j]=i;
            sz[i] = sz[j];

        }
        count --;
    }

}
