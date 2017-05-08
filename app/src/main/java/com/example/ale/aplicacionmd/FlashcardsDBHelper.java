package com.example.ale.aplicacionmd;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.AudioEffect;
import android.support.design.widget.Snackbar;
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


    public String obtenerND (int type, int position)
    {
        SQLiteDatabase db = this.getReadableDatabase();

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

    public List<String> sizeDB ()
    {
        SQLiteDatabase db = context.openOrCreateDatabase("Flashcards.db", context.MODE_PRIVATE, null);

        //Cursor cursor = db.rawQuery("SELECT * FROM " + EsquemaFlashcard.EntradaFlashcard.TABLE_NAME, null);
        Cursor cursor = db.rawQuery("SELECT * FROM flashcard", null);

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

}
