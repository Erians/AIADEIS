package com.example.ale.aplicacionmd;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.AudioEffect;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.ale.aplicacionmd.EsquemaFlashcard.EntradaFlashcard.Descripcion;
import static com.example.ale.aplicacionmd.EsquemaFlashcard.EntradaFlashcard.Lado_1;
import static com.example.ale.aplicacionmd.EsquemaFlashcard.EntradaFlashcard.Lado_2;
import static com.example.ale.aplicacionmd.EsquemaFlashcard.EntradaFlashcard.Nombre;

/**
 * Created by ale_c on 5/5/2017.
 */

public class FlashcardsDBHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Flashcards.db";
    Context context;

    public FlashcardsDBHelper(Context context)
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {


        db.execSQL("CREATE TABLE flashcard ( "
        + EsquemaFlashcard.EntradaFlashcard.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + EsquemaFlashcard.EntradaFlashcard.Nombre + " TEXT NOT NULL, "
        + EsquemaFlashcard.EntradaFlashcard.Descripcion + " TEXT NOT NULL, "
        + EsquemaFlashcard.EntradaFlashcard.Lado_1 + " TEXT NOT NULL, "
        + Lado_2 + " TEXT NOT NULL, "
        + " UNIQUE (" + EsquemaFlashcard.EntradaFlashcard.ID + "))");

        /*
        db.close();
        */
        db.execSQL("CREATE TABLE memorama ( "
                + EsquemaMemorama.EntradaMemorama.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EsquemaMemorama.EntradaMemorama.Nombre + " TEXT NOT NULL, "
                + EsquemaMemorama.EntradaMemorama.Descripcion + " TEXT NOT NULL, "
                + EsquemaMemorama.EntradaMemorama.Par_1 + " TEXT NOT NULL, "
                + EsquemaMemorama.EntradaMemorama.Par_2 + " TEXT NOT NULL, "
                + " UNIQUE (" + EsquemaMemorama.EntradaMemorama.ID + "))");
        //db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }



    public void agregar(String nombre, String descripcion, String lado_1, String lado_2)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EsquemaFlashcard.EntradaFlashcard.Nombre, nombre);
        values.put(EsquemaFlashcard.EntradaFlashcard.Descripcion, descripcion);
        values.put(EsquemaFlashcard.EntradaFlashcard.Lado_1, lado_1);
        values.put(Lado_2, lado_2);

        db.insert("flashcard", null, values);
        db.close();
    }


    public void agregarMemo (String nombre, String descripcion, String par_1, String par_2)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EsquemaMemorama.EntradaMemorama.Nombre, nombre);
        values.put(EsquemaMemorama.EntradaMemorama.Descripcion, descripcion);
        values.put(EsquemaMemorama.EntradaMemorama.Par_1, par_1);
        values.put(EsquemaMemorama.EntradaMemorama.Par_2, par_2);

        db.insert("memorama", null, values);
        db.close();
    }


    public String obtenerND (int type, int position, String NomTabla)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Nombre, Descripcion FROM " + NomTabla, null);

        String [][] array = new String[4][cursor.getCount()];
        int i=0;

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Nombre"));
            String description = cursor.getString(cursor.getColumnIndex("Descripcion"));

            int flag = 0;
            for (int a= 0; a<i;a++)
            {
                if (array[0][a].equals(name))
                {
                    flag++;
                }
            }

            if (flag == 0)
            {
                array[0][i] = name;
                array[1][i] = description;
                i++;
            }
        }
        db.close();
        cursor.close();
        //i=0;
        return array[type][position];
    }

    public List<String> sizeDB (String NomTabla)
    {
        SQLiteDatabase db = context.openOrCreateDatabase("Flashcards.db", context.MODE_PRIVATE, null);

        //Cursor cursor = db.rawQuery("SELECT * FROM " + EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null);
        Cursor cursor = db.rawQuery("SELECT DISTINCT Nombre FROM " + NomTabla, null);

        List<String> tabla = new ArrayList<>();
        int i=0;

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Nombre"));

            tabla.add(name);

            i++;
        }
        db.close();

        return tabla;
    }

    public int sizeINT(String SetName, String NomTabla)
    {
        int size=0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ NomTabla + " WHERE Nombre = '"+SetName+"'", null);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Nombre"));

            size++;
        }
        db.close();
        cursor.close();

        return size;
    }


    public String obtenerLados (int type, int position, String Nombre)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Lado_1, Lado_2 FROM " + EsquemaFlashcard.EntradaFlashcard.TABLE_NAME + " WHERE Nombre = '" + Nombre + "' ", null);

        String [][] array = new String[4][cursor.getCount()];
        int i=0;

        while (cursor.moveToNext())
        {
            String lado1 = cursor.getString(cursor.getColumnIndex("Lado_1"));
            String lado2 = cursor.getString(cursor.getColumnIndex("Lado_2"));


                array[2][i] = lado1;
                array[3][i] = lado2;
                i++;

        }
        db.close();
        cursor.close();
        //i=0;
        return array[type][position];
    }

    public String obtenerPares (int position, String Nombre, String NomTabla)
    {
        int size=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ NomTabla + " WHERE Nombre = '"+Nombre+"'", null);
        int sizeArray = 2 * cursor.getCount();
        String [][] array = new String[2 * cursor.getCount()][2];
        String [][] secondArray = new String[2 * cursor.getCount()][2];
        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Par_1"));
            String name2 = cursor.getString(cursor.getColumnIndex("Par_2"));
            array[size][0]=name;
            array[sizeArray - size -1][0]=name2;
            size++;
        }
        db.close();
        cursor.close();

        int cont=0;
        for (int a=0;a<sizeArray;a=a+2)
        {
            if(cont%2 == 0)
            {
                secondArray[sizeArray-cont-1][0]=array[a][0];
                secondArray[cont][0] = array[a+1][0];
            }
            else {
                if (cont % 2 != 0) {
                    secondArray[sizeArray - cont - 1][0] = array[a + 1][0];
                    secondArray[cont][0] = array[a][0];
                }
            }
            cont++;
        }
        return secondArray[position][0];
    }


    public String determinarPareja (String par, String SetMem)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM memorama WHERE Nombre = '"+SetMem+"'", null);
        int sizeArray = cursor.getCount();
        int size=0;
        int value1send=0;
        int value2send=0;
        String [][] array = new String[cursor.getCount()][2];

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Par_1"));
            String name2 = cursor.getString(cursor.getColumnIndex("Par_2"));
            array[size][0]=name;
            array[size][1]=name2;
            size++;
        }
        db.close();
        cursor.close();


        for (int a=0;a<sizeArray;a++)
        {
            if(par.equals(array[a][0]))
            {
                value1send = a;
                value2send = 1;
            }
            else {
                if (par.equals(array[a][1])) {
                    value1send = a;
                    value2send = 0;
                }
            }
        }
        return array[value1send][value2send];
    }


    public void ejecutarComando (String SetName, String TableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("DELETE FROM "+ SetName +" WHERE Nombre = '" + TableName + "'");
        db.close();
    }
}
