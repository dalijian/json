package com.lijian.data_structure.List;

public class DefaultStrategy implements Strategy {
    @Override
    public boolean equal(Object o1, Object o2) {
        return false;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
