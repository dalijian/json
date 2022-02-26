package com.lijian.annotation;

import com.lijian.validation.NotSelfBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({  ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotSelfBlank {

	String message() default "";


}

