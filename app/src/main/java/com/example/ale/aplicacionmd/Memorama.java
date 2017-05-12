package com.example.ale.aplicacionmd;

import java.util.UUID;

/**
 * Created by ale_c on 5/11/2017.
 */

public class Memorama

{
    private String ID;
    private String Nombre;
    private String Descripcion;
    private String Par_1;
    private String Par_2;

    public Memorama(String Nombre, String Descripcion, String Par_1, String Par_2)
    {
        this.ID= UUID.randomUUID().toString();
        this.Nombre=Nombre;
        this.Descripcion=Descripcion;
        this.Par_1=Par_1;
        this.Par_2=Par_2;
    }

    public String getID()
    {
        return ID;
    }

    public String getNombre() { return Nombre; }

    public String getDescripcion()
    {
        return Descripcion;
    }

    public String getLado_1()
    {
        return Par_1;
    }

    public String getLado_2()
    {
        return Par_2;
    }
}
