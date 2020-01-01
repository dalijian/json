package com.lijian.algorithms.charpter_1;

public class UF_quick_union extends  UF {
    public UF_quick_union(int count) {
        super(count);
    }

    @Override
    public int find(int p) {

        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot]=qRoot;

        count--;

    }
}
