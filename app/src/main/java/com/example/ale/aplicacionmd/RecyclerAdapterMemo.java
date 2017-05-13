package com.example.ale.aplicacionmd;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ale_c on 5/12/2017.
 */

public class RecyclerAdapterMemo extends RecyclerView.ViewHolder
{

    private int[] images = {R.drawable.backcard};
    //public int currentItem;
    public ImageView itemImage;
    public TextView itemTitle;
    public ImageView itemChange;
    public CardView Carta;
    public String other="";
    public String text;
    Context context;

    public Context getContext() {
        return context;
    }

    FlashcardsDBHelper oo = new FlashcardsDBHelper(getContext());
    //public RelativeLayout RL;
    //public CardView mCardView;


    public RecyclerAdapterMemo(final View itemView, final String SetName) {
        super(itemView);
        itemImage = (ImageView) itemView.findViewById(R.id.tile_picture);
        itemTitle = (TextView) itemView.findViewById(R.id.tile_title);
        itemChange = (ImageView) itemView.findViewById(R.id.tile_change);
        Carta = (CardView) itemView.findViewById(R.id.carta);




        /*

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Snackbar.make(v, "Click detected on item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        */

    }
}
