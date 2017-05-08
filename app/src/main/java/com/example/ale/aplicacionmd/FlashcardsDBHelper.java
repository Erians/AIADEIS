package com.example.ale.aplicacionmd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.AudioEffect;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

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

    public FlashcardsDBHelper(Context context)
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + EsquemaFlashcard.EntradaFlashcard.TABLE_NAME + " ( "
        + EsquemaFlashcard.EntradaFlashcard.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + EsquemaFlashcard.EntradaFlashcard.Nombre + " TEXT NOT NULL, "
        + EsquemaFlashcard.EntradaFlashcard.Descripcion + " TEXT NOT NULL, "
        + EsquemaFlashcard.EntradaFlashcard.Lado_1 + " TEXT NOT NULL, "
        + Lado_2 + " TEXT NOT NULL, "
        + " UNIQUE (" + EsquemaFlashcard.EntradaFlashcard.ID + "))");

        /*
        ContentValues values = new ContentValues();

        values.put(EsquemaFlashcard.EntradaFlashcard.ID, "1");
        values.put(EsquemaFlashcard.EntradaFlashcard.Nombre, "Historia");
        values.put(EsquemaFlashcard.EntradaFlashcard.Descripcion, "Revolución francesa, y Enrique VIII");
        values.put(EsquemaFlashcard.EntradaFlashcard.Lado_1, "Religión creada por Enrique VIII");
        values.put(EsquemaFlashcard.EntradaFlashcard.Lado_2, "Anglicana");

        db.insert(EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null, values);
        */
    }
/*
    public long guardarFlashcards(Flashcards FC)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null, FC.toContentValues());
    }

    public ContentValues toContentValues()
    {
        ContentValues values = new ContentValues();
        values.put(EsquemaFlashcard.EntradaFlashcard.ID, ID);
        values.put(EsquemaFlashcard.EntradaFlashcard.Nombre, Nombre);
        values.put(EsquemaFlashcard.EntradaFlashcard.Descripcion, Descripcion);
        values.put(EsquemaFlashcard.EntradaFlashcard.Lado_1, Lado_1);
        values.put(EsquemaFlashcard.EntradaFlashcard.Lado_2, Lado_2);
        return values;

    }
    */

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

        db.insert(EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null, values);
        db.close();
    }


    public String obtenerND (int type, int position)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {EsquemaFlashcard.EntradaFlashcard.Nombre, EsquemaFlashcard.EntradaFlashcard.Descripcion, EsquemaFlashcard.EntradaFlashcard.Lado_1, Lado_2};

        Cursor cursor =
                db.rawQuery("SELECT * FROM " + EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null);

        String [][] array = new String[4][cursor.getCount()];
        int i=0;

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("Nombre"));
            String description = cursor.getString(cursor.getColumnIndex("Descripcion"));

            array[0][i] = name;
            array[1][i] = description;

            i++;
        }
        db.close();

        return array[type][position];
    }
}
