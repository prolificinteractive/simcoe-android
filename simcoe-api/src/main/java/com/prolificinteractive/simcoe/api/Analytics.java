package com.prolificinteractive.simcoe.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Annotate your analytics class with this annotation. Creates a class that extend your analytics
 * class and provide different handy debug tools. Use the builder to create a new class and set
 * the different parameters.
 */
@Target(TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Analytics {
}
