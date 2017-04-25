package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.ViewHolder
{
    private String[] titles = {"Flashcards", "Memorama"};
    private int[] images = {R.drawable.bag_2, R.drawable.bag_1};
    public int currentItem;
    public ImageView itemImage;
    public TextView itemTitle;


    public RecyclerAdapter(View itemView) {
        super(itemView);
        itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        itemTitle = (TextView) itemView.findViewById(R.id.item_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Snackbar.make(v, "Click detected on item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        /*
        se obtiene el contexto y se crea un Intent este "intenta" o trata de abrir otro activity,
        el primer parametro de intent es donde estas en este caso es el contexto y
        el segundo parametro es la actividad que deseas abrir
        por ultimo en contexto usas el metodo para abrir una activity y pones en el el intent.
        */
                Context context = v.getContext();
                Intent intentFlashcards = new Intent(context, flashCardsActivity.class);
<<<<<<< HEAD
                Intent intentMemorama = new Intent(context, MemoramaActivity.class);
=======
>>>>>>> origin/master

                switch (position) {
                    case 0:
                        context.startActivity(intentFlashcards);
                        break;
                    case 1:
<<<<<<< HEAD
                        context.startActivity(intentMemorama);
=======
>>>>>>> origin/master
                        break;
                    default:
                }
            }
        });
    }
}






