package com.example.ale.aplicacionmd;

import android.provider.BaseColumns;

/**
 * Created by ale_c on 5/5/2017.
 */

public class EsquemaFlashcard
{
    public static abstract class EntradaFlashcard implements BaseColumns
    {
        public static final String TABLE_NAME="flashcard";

        public static final String ID = "ID";
        public static final String Nombre = "Nombre";
        public static final String Descripcion = "Descripcion";
        public static final String Lado_1 = "Lado_1";
        public static final String Lado_2 = "Lado_2";
    }
}
