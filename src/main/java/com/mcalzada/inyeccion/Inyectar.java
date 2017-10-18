package com.mcalzada.inyeccion;

import com.mcalzada.anotaciones.Inyectable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Marco Calzada on 10/08/2017.
 */
public class Inyectar
{
    public void realizarInyeccion(String clase)
    {
        try
        {
            Class ejecutor = Class.forName(clase);

            for (Method method : ejecutor.getClassLoader().loadClass((clase)).getMethods())
            {
                if (method.isAnnotationPresent(Inyectable.class))
                {

                    for (Annotation ignored : method.getDeclaredAnnotations())
                    {
                        switch (method.getParameterCount())
                        {
                            case 0:
                                method.invoke(ejecutor.newInstance());
                                break;
                            case 1:
                                method.invoke(ejecutor.newInstance(), "el valor es nulo");
                                break;
                            case 2:
                                method.invoke(ejecutor.newInstance(), "el valor es nulo", "Null Pointer");
                                break;
                        }
                    }
                }
            }
        }
        catch (SecurityException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> clasesAnotadas(Class anotacion)
    {
        return Escaneo.analizarPaquete("com.mcalzada", anotacion);
    }
}