package com.prolificinteractive.simcoe.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target(METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Ignore {
}
