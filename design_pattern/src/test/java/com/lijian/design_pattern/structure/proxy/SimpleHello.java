package com.lijian.design_pattern.structure.proxy;

public class SimpleHello implements Hello {
    @Override
    public void morning(String name) {
        System.out.println(this.getClass().getName());
    }
}
