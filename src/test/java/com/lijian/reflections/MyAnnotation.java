package com.lijian.reflections;

import java.lang.annotation.Annotation;

public class MyAnnotation implements Annotation {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
