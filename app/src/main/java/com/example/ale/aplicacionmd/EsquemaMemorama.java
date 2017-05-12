package com.example.ale.aplicacionmd;

import android.provider.BaseColumns;

/**
 * Created by ale_c on 5/11/2017.
 */

public class EsquemaMemorama
{
    public static abstract class EntradaMemorama implements BaseColumns
    {
        public static final String TABLE_NAME="memorama";

        public static final String ID = "ID";
        public static final String Nombre = "Nombre";
        public static final String Descripcion = "Descripcion";
        public static final String Par_1 = "Par_1";
        public static final String Par_2 = "Par_2";
    }
}
