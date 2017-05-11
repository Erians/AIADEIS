package com.example.ale.aplicacionmd;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_cards);


        FlashcardsDBHelper opener = new FlashcardsDBHelper(this);

        sizeSet=opener.sizeINT("nuevo");

        texto = opener.obtenerND(cont_type,cont_position);
        TextView Cadena = (TextView) findViewById(R.id.card_text);

        Cadena.setText(texto);

        //itemCardText = (TextView) findViewById(R.id.card_text);
        //OnSwipeTouchListener MovementsOnScreen = new OnSwipeTouchListener(this);


        CardView flashcard = (CardView) findViewById(R.id.card_view);
        flashcard.setOnTouchListener(new OnSwipeTouchListener(showFlashcards.this)
        {
            public void onSwipeRight()
            {
                Toast.makeText(showFlashcards.this, "right" + sizeSet, Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft()
            {
                Toast.makeText(showFlashcards.this, "left", Toast.LENGTH_SHORT).show();
            }

        });

        flashcard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }

        });

    }

}
