package com.lijian.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import org.junit.Test;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Demo {

    @Test
    public void helloworld() throws IllegalAccessException, InstantiationException {

        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        String str = dynamicType.newInstance().toString();
        System.out.println(str);
    }


    @Test
    public void build(){
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy().subclass(Object.class)
                .name("example.Type").make();
    }

    @Test
    public void dynamicType(){

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }

                })
                .subclass(Object.class)
                .make();
    }



    @Test
    public void toStringTest() throws IllegalAccessException, InstantiationException {

        String toString = new ByteBuddy()
                .subclass(Object.class)
                .name("example.Type")
                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
    }
}

class Foo {
    String bar() { return "bar"; }
}
