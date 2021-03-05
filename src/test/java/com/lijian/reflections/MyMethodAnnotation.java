package com.lijian.reflections;

import java.lang.annotation.Annotation;

public class MyMethodAnnotation implements Annotation {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
