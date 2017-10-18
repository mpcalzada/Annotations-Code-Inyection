package com.mcalzada.prueba;

import com.mcalzada.prueba.B;

/**
 * Hello world!
 */
public class App
{
    public static void main(String[] args)
    {
        B b = new B("AF", 1);
        C c = new C(1, 2);

        b.productdetails();
        c.productcost();
    }
}
