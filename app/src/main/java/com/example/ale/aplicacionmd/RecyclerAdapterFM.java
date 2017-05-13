package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapterFM extends RecyclerView.ViewHolder
{
    //private String[] names = {"Set 1", "Set 2"};
    //private String[] description = {"Set 1...desc", "Set 2... desc"};

    public int currentItem;
    public TextView itemName;
    public TextView itemDesc;
    public ImageButton play;



    public RecyclerAdapterFM(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.item_name);
        itemDesc = (TextView) itemView.findViewById(R.id.item_desc);
        play = (ImageButton) itemView.findViewById(R.id.play_button);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int position = getAdapterPosition();
                //Snackbar.make(v, "Click detected on item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}


