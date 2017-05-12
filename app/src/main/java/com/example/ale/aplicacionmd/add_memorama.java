package com.example.ale.aplicacionmd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ale_c on 5/11/2017.
 */

public class add_memorama extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flashcards);

        TextView mNombreContent= (TextView) findViewById(R.id.nombre_set_cont);
        TextView mDescripcionContent = (TextView) findViewById(R.id.descripcion_set_cont);
        TextView tflashcard = (TextView) findViewById(R.id.flashcard);
        Button tagregarFC = (Button) findViewById(R.id.agregar_flashcard);
        Button agregarB = (Button) findViewById(R.id.terminar_set);


        final String message1 = getIntent().getExtras().getString("Nombre");
        final String message2= getIntent().getExtras().getString("Descripcion");

        mNombreContent.setText(message1);
        mDescripcionContent.setText(message2);
        tflashcard.setText("Memorama");
        tagregarFC.setText("Agregar Par de Cartas");
        agregarB.setText("Terminar Juego de Cartas");

        Button mAceptar = (Button) findViewById(R.id.agregar_flashcard);

        mAceptar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                FlashcardsDBHelper Opener = new FlashcardsDBHelper(add_memorama.this);

                TextView lado_1 = (TextView) findViewById(R.id.lado_1_cont);
                String lado1 = lado_1.getText().toString();
                TextView lado_2 = (TextView) findViewById(R.id.lado_2_cont);
                String lado2 = lado_2.getText().toString();

                Opener.agregarMemo(message1, message2, lado1, lado2);
            }
        });
    }
}
