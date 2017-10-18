package com.mcalzada.prueba;

/**
 * Created by Marco Calzada on 15/08/2017.
 */
public class C extends B
{
    C(int c, int d)
    {
        super("Prueba", 1);
        quantity = c;
        total = d;
    }

    void productcost()
    {
        System.out.println("The quantity is" + quantity);
        System.out.println("The total cost is" + total);
    }
}