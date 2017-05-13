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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemoramaActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view_memorama);

        layoutManager = new LinearLayoutManager(this);
        if(recyclerView1 != null) {
            recyclerView1.setLayoutManager(layoutManager);
        }
        adapter = new ComplexRecyclerViewAdapter(3, this);
        adapter.getItemViewType(3);
        recyclerView1.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MemoramaActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.crear_set_flashcards, null);


                TextView descripcionL = (TextView) mView.findViewById(R.id.etiqueta);
                descripcionL.setText("Nuevo Set de Memorama");


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
                            Toast.makeText(MemoramaActivity.this,
                                    "Falta definir un nombre para el set de cartas", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(mDescripcion.getText().toString().isEmpty())
                            {
                                Toast.makeText(MemoramaActivity.this,
                                        "Falta agregar información que describa al set de flashcards", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                //View mView = getLayoutInflater().inflate(R.layout.add_flashcards, null)
                                String pNombre = mNombre.getText().toString();
                                String pDescripcion = mDescripcion.getText().toString();

                                Context context = view.getContext();
                                Intent intent = new Intent(context, add_memorama.class);
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

    public void onClickBotonPlay (View v)
    {
        int position = (int) v.getTag();

        FlashcardsDBHelper oo = new FlashcardsDBHelper(this);
        String nombre = oo.obtenerND(0, position, EsquemaMemorama.EntradaMemorama.TABLE_NAME);

        //String Psrposition = v.getTag().toString();
        //String pNombre = mNombre.getText().toString();
        Context context = v.getContext();

        Intent showMem = new Intent(context, showMemorama.class);
        showMem.putExtra("Nombre", nombre);
        context.startActivity(showMem);

    }
}
