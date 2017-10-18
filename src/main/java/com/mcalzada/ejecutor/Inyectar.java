package com.mcalzada.ejecutor;


import com.google.common.reflect.ClassPath;
import com.ks.lib.Configuracion;
import com.mcalzada.anotaciones.Inyectable;
import com.mcalzada.suceptible.Logs;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Marco Calzada on 10/08/2017.
 */
public class Inyectar
{
    private final String RUTA = Configuracion.getRuta() + "src\\main\\java\\";
    private static ArrayList<String> clases;

    public Inyectar()
    {
        clases = new ArrayList<>();
    }

    public void realizarInyeccion(String clase)
    {
        try
        {
            for (Method method : Logs.class.getClassLoader().loadClass((clase)).getMethods())
            {
                if (method.isAnnotationPresent(Inyectable.class))
                {
                    try
                    {
                        for (Annotation anno : method.getDeclaredAnnotations())
                        {
                            for (Annotation an : method.getAnnotationsByType(Annotation.class))
                            {
                                //System.out.println("\n\nAnnotation in Method '" + method + "' : " + anno + "  " + method.getParameterTypes());
                            }

                            switch (method.getParameterCount())
                            {
                                case 0:
                                    //method.invoke(new Logs());
                                    break;
                                case 1:
                                    //method.invoke(new Logs(), "Hola mundo");
                                    break;
                                case 2:
                                    //method.invoke(new Logs(), "Hola mundo", "Cruel");
                                    break;
                            }
                        }
                        Inyectable anotacion = method.getAnnotation(Inyectable.class);
                        System.out.println("Detalles:\nEstatus: " + anotacion.estatus() + " \nMensaje: " + anotacion.mensaje());
                    }
                    catch (Throwable ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (SecurityException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void analizarPaquete(String paquete, Class anotacion)
    {
        try
        {
            ClassPath classPath = ClassPath.from(Inyectar.class.getClassLoader());
            classPath.getTopLevelClassesRecursive(paquete).stream().filter((ClassPath.ClassInfo classInfo) ->
            {
                String rutaActual = RUTA + classInfo.getName().replace(".", "\\");
                System.out.println("Analizando: " + classInfo.getName());
                try
                {
                    File file = new File(rutaActual);
                    if (file.exists())
                    {
                        if (file.isDirectory())
                        {
                            analizarPaquete(classInfo.getName(), anotacion);
                        }
                    }
                    else
                    {
                        rutaActual += ".java";
                        file = new File(rutaActual);

                        if (file.exists())
                        {
                            if (file.isFile())
                            {
                                if (Class.forName(classInfo.getName()).isAnnotationPresent(anotacion))
                                {
                                    clases.add(Class.forName(classInfo.getName()).getName());
                                    return true;
                                }
                            }
                        }
                    }
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }

                return false;

            }).map(c ->
            {
                System.out.println("El metodo MAP: " + c.getName());
                return c.load().getPackage().getAnnotation(anotacion);
            }).forEach(a ->
            {

                System.out.println(a);
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> clasesAnotadas(Class anotacion)
    {
        analizarPaquete("com.mcalzada", anotacion);
        return clases;
    }
}