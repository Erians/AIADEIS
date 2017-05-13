package com.example.ale.aplicacionmd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import static com.example.ale.aplicacionmd.R.id.flashcard;

/**
 * Created by ale_c on 5/11/2017.
 */

public class showFlashcards extends AppCompatActivity implements Animation.AnimationListener {
    public TextView itemCardText;
    public int cont_type = 2;
    public int cont_position = 0;
    public int sizeSet;
    public String texto;
    FlashcardsDBHelper opener = new FlashcardsDBHelper(this);
    Animation move;
    Animation slideUp;
    Animation slideDown;
    Animation moveltr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_cards);

        final String nombreSet = getIntent().getExtras().getString("Nombre");
        texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
        sizeSet = opener.sizeINT(nombreSet, EsquemaFlashcard.EntradaFlashcard.TABLE_NAME);

        itemCardText = (TextView) findViewById(R.id.card_text);
        itemCardText.setText(texto);

        move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        move.setAnimationListener(this);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slideUp.setAnimationListener(this);
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slideDown.setAnimationListener(this);
        moveltr = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveltr);
        moveltr.setAnimationListener(this);



        final CardView flashcard = (CardView) findViewById(R.id.card_view);
        flashcard.setOnTouchListener(new OnSwipeTouchListener(showFlashcards.this) {

            //Izquierda
            public void onSwipeRight() {

                flashcard.startAnimation(moveltr);

                cont_type = 2;
                if (cont_position == 0) {
                    cont_position = sizeSet - 1;
                } else {
                    cont_position--;
                }


                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);



            }


            //Derecha
            public void onSwipeLeft() {

                flashcard.startAnimation(move);
                cont_type = 2;
                if (cont_position + 1 == sizeSet) {
                    cont_position = 0;
                } else {
                    cont_position++;
                }

                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);


            }


            //Se realiza de abajo hacia arriba
            public void onSwipeTop() {


                if (cont_type == 2) {
                    cont_type = 3;
                } else {
                    if (cont_type == 3) {
                        cont_type = 2;
                    }
                }


                flashcard.startAnimation(slideUp);
                flashcard.startAnimation(slideDown);

                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);
            }


            //Se realiza de arriba hacia abajo
            public void onSwipeBottom() {
                if (cont_type == 2) {
                    cont_type = 3;
                } else {
                    if (cont_type == 3) {
                        cont_type = 2;
                    }
                }

                flashcard.startAnimation(slideUp);
                flashcard.startAnimation(slideDown);

                texto = opener.obtenerLados(cont_type, cont_position, nombreSet);
                itemCardText.setText(texto);
            }
        });
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == slideUp) {

        }
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }

    @Override
    public void onAnimationStart(Animation animation)
    {

        if (animation == move) {
            //Toast.makeText(getApplicationContext(), "Animation hello", Toast.LENGTH_SHORT).show();
        }
    }
}

