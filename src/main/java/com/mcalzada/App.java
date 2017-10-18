package com.mcalzada;

import com.mcalzada.anotaciones.ClaseInyectable;
import com.mcalzada.inyeccion.Inyectar;

/**
 * Created by Marco Calzada on 16/08/2017.
 */
public class App
{
    public static void main(String[] args)
    {
        Inyectar inyectar = new Inyectar();
        inyectar.clasesAnotadas(ClaseInyectable.class).forEach(ruta -> inyectar.realizarInyeccion(ruta));
    }
}
