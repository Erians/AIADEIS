package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class flashCardsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ComplexRecyclerViewAdapter(2, this);
        adapter.getItemViewType(2);
        recyclerView.setAdapter(adapter);

        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                    se obtiene el contexto y se crea un Intent este "intenta" o trata de abrir otro activity,
                    el primer parametro de intent es donde estas en este caso es el contexto y
                    el segundo parametro es la actividad que deseas abrir
                    por ultimo en contexto usas el metodo para abrir una activity y pones en el el intent.





                */

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(flashCardsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.crear_set_flashcards, null);

                final EditText mNombre = (EditText) mView.findViewById(R.id.nombre_flashcard);
                final EditText mDescripcion = (EditText) mView.findViewById(R.id.descripcion_flashcard);
                Button mAceptar = (Button) mView.findViewById(R.id.aceptar_button);


                mAceptar.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick (View view)
                    {

                        if(mNombre.getText().toString().isEmpty())
                        {
                            Toast.makeText(flashCardsActivity.this,
                                    "Falta definir un nombre para el set de cartas",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                         if(mDescripcion.getText().toString().isEmpty())
                         {
                             Toast.makeText(flashCardsActivity.this,
                                     "Falta agregar información que describa al set de flashcards",
                                     Toast.LENGTH_SHORT).show();
                         }
                         else
                             {
                                 //View mView = getLayoutInflater().inflate(R.layout.add_flashcards, null);

                                 String pNombre = mNombre.getText().toString();
                                 String pDescripcion = mDescripcion.getText().toString();

                                 Context context = view.getContext();
                                 Intent intent = new Intent(context, add_flashcard.class);
                                 intent.putExtra("Nombre", pNombre);
                                 intent.putExtra("Descripcion", pDescripcion);
                                 context.startActivity(intent);


                             }
                        }


                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id==R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
