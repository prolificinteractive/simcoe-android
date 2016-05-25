package com.prolificinteractive.simcoe.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Annotate methods using this annotation to prevent the creation of a sub-method. Typically when
 * you don't need to check parameters of the call.
 */
@Target(METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Ignore {
}
