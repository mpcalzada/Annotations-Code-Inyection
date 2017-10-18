package com.mcalzada.prueba;

/**
 * Created by Marco Calzada on 15/08/2017.
 */
public class B extends A
{
    int quantity;
    int total;

    B(String a, int b)
    {

        product = a;
        price = b;
    }

    void productdetails()
    {
        System.out.println("The product name is" + product);
        System.out.println("The price is" + price);
    }
}