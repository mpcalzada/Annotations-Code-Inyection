package com.mcalzada.alertas.escritura;

import com.mcalzada.anotaciones.ClaseInyectable;
import com.mcalzada.anotaciones.Inyectable;

/**
 * Created by Marco Calzada on 10/08/2017.
 */
@ClaseInyectable
public class Logs
{
    @Inyectable
    public void logCritical(String descripcion)
    {
        System.out.println("Log de error critico: " + descripcion);
    }

    @Inyectable(estatus = Inyectable.Status.ERROR)
    public void logError(String descripcion, String tipo)
    {
        System.out.println("Log de tipo " + tipo + " el error es: " + descripcion);
    }

    @Inyectable(estatus = Inyectable.Status.WARN, mensaje = "Warn")
    public void logWarn()
    {
        System.out.println("Log de Warning");
    }

    @Inyectable(estatus = Inyectable.Status.INFO, mensaje = "Info")
    public void logInfo()
    {
        System.out.println("Log Informativo");
    }
}
