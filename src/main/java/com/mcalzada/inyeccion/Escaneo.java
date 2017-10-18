package com.mcalzada.inyeccion;

import com.google.common.reflect.ClassPath;
import com.ks.lib.Configuracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marco Calzada on 24/08/2017.
 */
public class Escaneo
{
    private static ArrayList<String> clases;
    private static final String RUTA = Configuracion.getRuta() + "src\\main\\java\\";

    static
    {
        clases = new ArrayList<>();
    }


    public static ArrayList analizarPaquete(String paquete, Class anotacion)
    {
        try
        {
            ClassPath classPath = ClassPath.from(Inyectar.class.getClassLoader());
            classPath.getTopLevelClassesRecursive(paquete).stream()
                    .filter((ClassPath.ClassInfo classInfo) -> validarArchivo(classInfo, anotacion))
                    .map(c -> c.load().getPackage().getAnnotation(anotacion))
                    .forEach(annotation -> annotation = null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return clases;
    }

    public static boolean validarArchivo(ClassPath.ClassInfo classInfo, Class anotacion)
    {
        String rutaActual = RUTA + classInfo.getName().replace(".", "\\");
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
                file = new File(rutaActual + ".java");

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
                else
                {
                    file = new File(rutaActual + ".class");
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
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
