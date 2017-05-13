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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int card = 0, MF=1, Memo = 2, showMemo = 3;
    private int type=0;
    private String[] titles = {"Flashcards", "Memorama"};
    private int[] images = {R.drawable.bag_2, R.drawable.bag_1};
    private String[] names = {"Set de prueba"};
    private String[] description = {"Crea un nuevo set"};
    private String[] namesM = {"Set de prueba", "Set 5"};
    private String[] descriptionM = {"Crea uno nuevo", "Set 5... desc"};
    private int[] imagesMemo = {R.drawable.backcard};

    Context context;
    List <String> tabla1 = new ArrayList<>();
    List <String> tabla2 = new ArrayList<>();
    public CardView mCardView;
    String NJuego;
    int size;


    public ComplexRecyclerViewAdapter(int type, Context context)
    {
        this.context = context;
        FlashcardsDBHelper oo = new FlashcardsDBHelper(context);
        this.type = type;
        try
        {
            tabla1 = oo.sizeDB(EsquemaFlashcard.EntradaFlashcard.TABLE_NAME);
            tabla2 = oo.sizeDB(EsquemaMemorama.EntradaMemorama.TABLE_NAME);
        }
        catch (Exception ex)
        {

        }
    }

    public ComplexRecyclerViewAdapter (int type, Context context, String NJuego)
    {
        FlashcardsDBHelper oo = new FlashcardsDBHelper(context);
        this.type = type;
        this.context = context;
        this.NJuego = NJuego;
        size = oo.sizeINT(NJuego, EsquemaMemorama.EntradaMemorama.TABLE_NAME);

    }

    @Override
    public int getItemCount()
    {
        if (type == 1)
        {
            return 2;
        }
        else
        {
            if(type == 2)
            {
                if(tabla1.size() == 0)
                {
                    return 1;
                }
                else
                {
                    return tabla1.size();
                }
            }else{
                if (type== 3){
                    if(tabla2.size() == 0)
                    {
                        return 1;
                    }
                    else
                    {
                        return tabla2.size();
                    }
                }
                else
                {
                    if(type == 4)
                    {
                        return 2*size;
                    }
                    else
                    {
                        return 2;
                    }
                }
            }
        }
    }


    @Override
    public int getItemViewType(int position)
    {
        if (type == 1)
        {
            return card;
        }
        else
        {
            if(type == 2)
            {
                return MF;
            }else{
                if (type== 3){
                    return Memo;
                }else
                {
                    if(type == 4)
                    {
                        return showMemo;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        RecyclerView.ViewHolder viewHolder;
        //LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType)
        {
            case card:
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
                viewHolder = new RecyclerAdapter(v1);
                break;
            case MF:
                View v2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_mem_flash, viewGroup, false);
                mCardView = (CardView) v2.findViewById(R.id.card_view);
                viewHolder = new RecyclerAdapterFM(v2);
                break;
            case Memo:
                // set mem flash cambiar layout
                View v3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_mem_flash, viewGroup, false);
                viewHolder = new RecyclerAdapterFM(v3);
                break;
            case showMemo:
                View v4 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.memocar, viewGroup, false);
                viewHolder = new RecyclerAdapterMemo(v4, NJuego);
                break;
            default:
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
                viewHolder = new RecyclerAdapter(v);
                break;
        }
        context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        switch (viewHolder.getItemViewType())
        {
            case card:
                RecyclerAdapter vh1 = (RecyclerAdapter) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case MF:
                RecyclerAdapterFM vh2 = (RecyclerAdapterFM) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            case Memo:
                RecyclerAdapterFM vh3 = (RecyclerAdapterFM) viewHolder;
                configureViewHolder3(vh3, position);
                break;
            case showMemo:
                RecyclerAdapterMemo vh4 = (RecyclerAdapterMemo) viewHolder;
                configureViewHolder4(vh4, position);
                break;
            default:
                RecyclerAdapter vh = (RecyclerAdapter) viewHolder;
                configureViewHolder1(vh, position);
                break;
        }


    }


    private void configureDefaultViewHolder(RecyclerAdapter vh, int position)
    {
        vh.itemTitle.setText(titles[position]);
        vh.itemImage.setImageResource(images[position]);
    }

    private void configureViewHolder1(RecyclerAdapter vh1, int position)
    {
        vh1.itemTitle.setText(titles[position]);
        vh1.itemImage.setImageResource(images[position]);
    }

    private void configureViewHolder2(RecyclerAdapterFM vh2, int position)
    {


        FlashcardsDBHelper open = new FlashcardsDBHelper(context);

        try {
            vh2.itemName.setText(open.obtenerND(0, position, EsquemaFlashcard.EntradaFlashcard.TABLE_NAME));
            vh2.itemDesc.setText(open.obtenerND(1, position, EsquemaFlashcard.EntradaFlashcard.TABLE_NAME));
            vh2.play.setTag(position);
            vh2.play.setVisibility(View.VISIBLE);
        }catch (Exception ex)
        {
            vh2.itemName.setText(names[position]);
            vh2.itemDesc.setText(description[position]);
            vh2.play.setTag(position);
            vh2.play.setVisibility(View.INVISIBLE);
        }

    }
    private void configureViewHolder3(RecyclerAdapterFM vh3, int position)
    {
        FlashcardsDBHelper open = new FlashcardsDBHelper(context);

        try {
            vh3.itemName.setText(open.obtenerND(0, position, EsquemaMemorama.EntradaMemorama.TABLE_NAME));
            vh3.itemDesc.setText(open.obtenerND(1, position, EsquemaMemorama.EntradaMemorama.TABLE_NAME));
            vh3.play.setTag(position);
            vh3.play.setVisibility(View.VISIBLE);
        }catch (Exception ex)
        {
            vh3.itemName.setText(namesM[position]);
            vh3.itemDesc.setText(descriptionM[position]);
            vh3.play.setTag(position);
            vh3.play.setVisibility(View.INVISIBLE);
        }
    }


    private void configureViewHolder4(RecyclerAdapterMemo vh4, int position)
    {
        FlashcardsDBHelper open = new FlashcardsDBHelper(context);

        try {
            vh4.itemImage.setImageResource(imagesMemo[0]);
            vh4.itemTitle.setText(open.obtenerPares(position, NJuego, EsquemaMemorama.EntradaMemorama.TABLE_NAME));
            vh4.itemTitle.setTag(position);
            vh4.Carta.setTag(position);
            vh4.itemChange.setTag(position);
            vh4.itemImage.setTag(position);
            vh4.itemTitle.setTag(position);
            //vh4.itemDesc.setText(open.obtenerND(1, position, EsquemaMemorama.EntradaMemorama.TABLE_NAME));
            //vh4.play.setTag(position);
        }catch (Exception ex)
        {
            //vh4.itemName.setText(namesM[position]);
            //vh4.itemDesc.setText(descriptionM[position]);
            //vh4.play.setTag(position);
        }
    }
}
