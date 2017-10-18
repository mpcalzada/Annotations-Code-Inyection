package com.mcalzada.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Marco Calzada on 16/08/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClaseInyectable
{
    enum Status
    {
        INFO, WARN, ERROR, CRITICAL
    }

    Status estatus() default Status.INFO;

    String mensaje() default "Sin novedad";
}
