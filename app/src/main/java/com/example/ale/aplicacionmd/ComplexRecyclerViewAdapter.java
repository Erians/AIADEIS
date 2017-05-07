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

import java.util.List;
import java.util.Objects;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int card = 0, MF=1, Memo = 2;
    private int type=0;
    private String[] titles = {"Flashcards", "Memorama"};
    private int[] images = {R.drawable.bag_2, R.drawable.bag_1};
    private String[] names = {"Set 1", "Set 2"};
    private String[] description = {"Set 1...desc", "Set 2... desc"};
    private String[] namesM = {"Set 4", "Set 5"};
    private String[] descriptionM = {"Set 4...desc", "Set 5... desc"};


    public ComplexRecyclerViewAdapter(int type)
    {
        this.type = type;
    }

    @Override
    public int getItemCount()
    {
        return titles.length;
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
                viewHolder = new RecyclerAdapterFM(v2);
                break;
            case Memo:
                // set mem flash cambiar layout
                View v3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_mem_flash, viewGroup, false);
                viewHolder = new RecyclerAdapterFM(v3);
                break;
            default:
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
                viewHolder = new RecyclerAdapter(v);
                break;
        }
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
        vh2.itemName.setText(names[position]);
        vh2.itemDesc.setText(description[position]);
    }
    private void configureViewHolder3(RecyclerAdapterFM vh3, int position)
    {
        vh3.itemName.setText(namesM[position]);
        vh3.itemDesc.setText(descriptionM[position]);
    }


}
