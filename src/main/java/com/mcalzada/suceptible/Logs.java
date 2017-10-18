package com.mcalzada.suceptible;

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
        System.out.println("PANIC CRITICAL KERNEL ERROR " + descripcion);
    }

    @Inyectable(estatus = Inyectable.Status.ERROR, mensaje = "Papas")
    public void logError(String descripcion, String tipo)
    {
        System.out.println("Se presento" + tipo + " un error: " + descripcion);
    }

    @Inyectable(estatus = Inyectable.Status.WARN, mensaje = "Papas")
    public void logWarn()
    {
        System.out.println("Warning");
    }

    @Inyectable(estatus = Inyectable.Status.INFO, mensaje = "Papas")
    public void logInfo()
    {
        System.out.println("Informativo");
    }
}
