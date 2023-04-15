package com.lijian.reflect;

import com.lijian.jackson.Person;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectTest {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Ref ref = new Ref();
        ref.setName("tt");
        Method[] methods = Ref.class.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getParameterCount()==0) {

                Object value = methods[i].invoke(ref, null);
                System.out.println(value);
            }

        }

    }
public static class Ref {
        String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
}
