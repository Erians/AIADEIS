package com.example.ale.aplicacionmd;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by ale_c on 5/11/2017.
 */

public class showFlashcards extends AppCompatActivity
{

    public CardView itemFC;
    public TextView itemCardText;
    public int cont_type=2;
    public int cont_position=0;
    public int sizeSet;
    public String texto;

    FlashcardsDBHelper opener = new FlashcardsDBHelper(this);


    //final int position = getIntent().getExtras("Nombre");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_cards);



        final String nombreSet = getIntent().getExtras().getString("Nombre");
        texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
        sizeSet=opener.sizeINT(nombreSet, EsquemaFlashcard.EntradaFlashcard.TABLE_NAME);



        itemCardText = (TextView) findViewById(R.id.card_text);

        itemCardText.setText(texto);



        //Cadena.setText(texto);

        //itemCardText = (TextView) findViewById(R.id.card_text);
        //OnSwipeTouchListener MovementsOnScreen = new OnSwipeTouchListener(this);

        CardView flashcard = (CardView) findViewById(R.id.card_view);
        flashcard.setOnTouchListener(new OnSwipeTouchListener(showFlashcards.this)
        {

            //Izquierda
            public void onSwipeRight()
            {

                if (cont_position==0)
                {
                    cont_position = sizeSet-1;
                }
                else
                {
                    cont_position--;
                }

                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);

            }


            //Derecha
            public void onSwipeLeft()
            {

                if (cont_position + 1 == sizeSet)
                {
                    cont_position = 0;
                }
                else
                {
                    cont_position++;
                }

                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);

            }

        });

        /*
        flashcard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Snackbar.make(view, "Click detected on item", Snackbar.LENGTH_LONG).show();

                if (cont_type == 2)
                {
                    cont_type = 3;
                    notify();
                }
                else
                {
                    if (cont_type == 3)
                    {
                        cont_type = 2;
                        notify();
                    }
                }

            }

        });
        */
    }

    public void ClickFlashcard (View view)
    {
        Snackbar.make(view, "Click detected on item", Snackbar.LENGTH_LONG).show();
    }

}
