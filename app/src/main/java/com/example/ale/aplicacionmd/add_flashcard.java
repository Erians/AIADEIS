package com.example.ale.aplicacionmd;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class add_flashcard extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flashcards);

        TextView mNombreContent= (TextView) findViewById(R.id.nombre_set_cont);
        TextView mDescripcionContent = (TextView) findViewById(R.id.descripcion_set_cont);

        final String message1 = getIntent().getExtras().getString("Nombre");
        final String message2= getIntent().getExtras().getString("Descripcion");

        mNombreContent.setText(message1);
        mDescripcionContent.setText(message2);

        Button mAceptar = (Button) findViewById(R.id.agregar_flashcard);

        mAceptar.setOnClickListener(new View.OnClickListener()
        {
                     @Override
                     public void onClick (View view)
                     {
                         FlashcardsDBHelper Opener = new FlashcardsDBHelper(add_flashcard.this);

                         TextView lado_1 = (TextView) findViewById(R.id.lado_1_cont);
                         String lado1 = lado_1.getText().toString();
                         TextView lado_2 = (TextView) findViewById(R.id.lado_2_cont);
                         String lado2 = lado_2.getText().toString();

                         Opener.agregar(message1, message2, lado1, lado2);
                     }
        });

    }
}
