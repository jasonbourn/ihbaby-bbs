package com.ihbaby.config;

import java.lang.annotation.*;

/**
 * Created by qiang on 2017/03/13.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserPerm {

    boolean value() default true;

    String param() default "";
}
