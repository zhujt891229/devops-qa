package com.zjt.qas.aspect;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthVerify {
    String value() default "";
}
