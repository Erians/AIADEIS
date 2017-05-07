package com.example.ale.aplicacionmd;

import java.util.UUID;

/**
 * Created by ale_c on 5/5/2017.
 */

public class Flashcards
{
    private String ID;
    private String Nombre;
    private String Descripcion;
    private String Lado_1;
    private String Lado_2;

    public Flashcards(String Nombre, String Descripcion, String Lado_1, String Lado_2)
    {
        this.ID= UUID.randomUUID().toString();
        this.Nombre=Nombre;
        this.Descripcion=Descripcion;
        this.Lado_1=Lado_1;
        this.Lado_2=Lado_2;
    }

    public String getID()
    {
        return ID;
    }

    public String getNombre()
    {
        return Nombre;
    }

    public String getDescripcion()
    {
        return Descripcion;
    }

    public String getLado_1()
    {
        return Lado_1;
    }

    public String getLado_2()
    {
        return Lado_2;
    }
}
