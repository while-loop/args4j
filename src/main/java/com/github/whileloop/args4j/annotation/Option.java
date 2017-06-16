package com.github.whileloop.args4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {

    /**
     * is a required option?
     */
    boolean required() default false;

    /**
     * short option denoted by "-"
     */
    String shortOpt() default "";

    /**
     * long option denoted by "--"
     */
    String longOpt() default "";

    /**
     * Help description of the option
     */
    String desc() default "";
}
