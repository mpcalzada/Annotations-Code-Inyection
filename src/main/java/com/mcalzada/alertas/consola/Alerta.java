package com.mcalzada.alertas.consola;

import com.mcalzada.anotaciones.ClaseInyectable;
import com.mcalzada.anotaciones.Inyectable;

/**
 * Created by Marco Calzada on 21/08/2017.
 */
@ClaseInyectable
public class Alerta
{
    @Inyectable
    public void logCritical(String descripcion)
    {
        System.out.println("ALERTA: Se presento un error critico: " + descripcion);
    }

    @Inyectable(estatus = Inyectable.Status.ERROR, mensaje = "Se presento un error.")
    public void logError(String descripcion, String tipo)
    {
        System.out.println("ALERTA: Se presento " + tipo + " de: " + descripcion);
    }
}