package com.lijian.spi;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        for (Animal s : animals) {
            s.say();
        }
    }
}