package com.mcalzada;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Marco Calzada on 10/08/2017.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inyectable
{
    enum Status
    {
        INFO, WARN, ERROR, CRITICAL
    }

    Status estatus() default Status.INFO;

    String mensaje() default "Sin novedad";
}
