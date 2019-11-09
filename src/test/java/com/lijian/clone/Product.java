package com.lijian.clone;

public interface  Product extends Cloneable {
    abstract void use(String s);
    public abstract Product createClone();

}
