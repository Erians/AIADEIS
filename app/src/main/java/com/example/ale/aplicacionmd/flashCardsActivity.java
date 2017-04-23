package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class flashCardsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                /*
                    se obtiene el contexto y se crea un Intent este "intenta" o trata de abrir otro activity,
                    el primer parametro de intent es donde estas en este caso es el contexto y
                    el segundo parametro es la actividad que deseas abrir
                    por ultimo en contexto usas el metodo para abrir una activity y pones en el el intent.
                    */
                Context context = view.getContext();
                Intent intentFlashcardsEdit = new Intent(context, flashCardsEditActivity.class);
                  context.startActivity(intentFlashcardsEdit);

            }
        });
    }

}
