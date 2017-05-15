package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class add_flashcard extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flashcards);

        final TextView mNombreContent= (TextView) findViewById(R.id.nombre_set_cont);
        final TextView mDescripcionContent = (TextView) findViewById(R.id.descripcion_set_cont);

        final String message1 = getIntent().getExtras().getString("Nombre");
        final String message2= getIntent().getExtras().getString("Descripcion");

        Context context;
        //context

        mNombreContent.setText(message1);
        mDescripcionContent.setText(message2);

        Button mAceptar = (Button) findViewById(R.id.agregar_flashcard);
        Button TerminarSet = (Button) findViewById(R.id.terminar_set);

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

                         if(lado_1.getText().toString().isEmpty())
                         {
                             Toast.makeText(add_flashcard.this,"Falta agregar el contenido del lado 1", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                             if (lado_2.getText().toString().isEmpty())
                             {
                                 Toast.makeText(add_flashcard.this,"Falta agregar el contenido del lado 2", Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 Opener.agregar(message1, message2, lado1, lado2);
                                 Toast.makeText(add_flashcard.this,"Se han agregado los datos con éxito", Toast.LENGTH_SHORT).show();
                                 lado_1.setText("");
                                 lado_2.setText("");
                             }
                         }
                     }
        });

        TerminarSet.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View view)
            {
                Intent intent = new Intent(getApplicationContext(), flashCardsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alertaRegresar = new AlertDialog.Builder(add_flashcard.this);
        View mView = getLayoutInflater().inflate(R.layout.back_button_pressed, null);
        Button mAceptar = (Button) mView.findViewById(R.id.si_button);
        Button mCancelar = (Button) mView.findViewById(R.id.no_button);
        alertaRegresar.setView(mView);
        final AlertDialog dialog = alertaRegresar.create();
        dialog.show();

        mAceptar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                dialog.dismiss();
                Context context = view.getContext();
                Intent getBack = new Intent(context, flashCardsActivity.class);
                context.startActivity(getBack);
            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                dialog.dismiss();
            }
        });

    }
}
