package com.sully.annotation;

import java.lang.annotation.*;

/**
 * Created by lei.s on 2017/8/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
