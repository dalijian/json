package com.lijian.algorithms.charpter_1;

public class UF_quick_find extends  UF {


    public UF_quick_find(int count) {
        super(count);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {


        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {

            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }
}
