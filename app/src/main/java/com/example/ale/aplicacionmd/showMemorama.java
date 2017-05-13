package com.example.ale.aplicacionmd;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by ale_c on 5/12/2017.
 */

public class showMemorama extends AppCompatActivity
{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    //String TextFromTheOther = "";

    //public ImageView itemImage;
    //public CardView Carta;
    public String other="";
    public String text;
    public String SetName;
    FlashcardsDBHelper oo = new FlashcardsDBHelper(this);
    TextView itemTitle;
    TextView itemTitle1;
    ImageView itemChange;
    ImageView itemChange1;

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.where_memoappear);

        final String nombreMemo = getIntent().getExtras().getString("Nombre");
        SetName = nombreMemo;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ComplexRecyclerViewAdapter(4,this, nombreMemo);
        recyclerView.setAdapter(adapter);


        /*
        final CardView flashcard = (CardView) findViewById(R.id.carta);
        flashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //int position = getAdapterPosition();
                Snackbar.make(v, "Click detected on item", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        /*
        //RelativeLayout Tarjeta = (RelativeLayout) findViewById(R.id.carta);
        //Tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //int position = getAdapterPosition();
                //Snackbar.make(v, "Click detected on item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Toast.makeText(getApplicationContext(), "Animation hello", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    public void onClickCarta (View v)
    {
        //Toast.makeText(getApplicationContext(), "Animation hello", Toast.LENGTH_SHORT).show();


        int position = (int) v.getTag();

        String text2;

        /*
        TextView itemTitle;
        TextView itemTitle1; = (TextView) v.findViewById(R.id.tile_title) ;
        ImageView itemChange;
        ImageView itemChange1; = (ImageView) v.findViewById(R.id.tile_change);
*/

        //int position = getAdapterPosition();






        if (other.equals(""))
        {
            text = oo.obtenerPares(position, SetName, EsquemaMemorama.EntradaMemorama.TABLE_NAME);
            other = oo.determinarPareja(text, SetName);
            itemTitle = (TextView) v.findViewById(R.id.tile_title);
            itemChange = (ImageView) v.findViewById(R.id.tile_change);



            itemChange.setVisibility(itemChange.VISIBLE);
            itemTitle.setVisibility(itemTitle.VISIBLE);
        }
        else
        {
            Runnable r = new Runnable() {
                @Override
                public void run(){
                    itemChange.setVisibility(itemChange.INVISIBLE);
                    itemTitle.setVisibility(itemTitle.INVISIBLE);
                    itemChange1.setVisibility(itemChange1.INVISIBLE);
                    itemTitle1.setVisibility(itemTitle1.INVISIBLE);
                }
            };

            Handler h = new Handler();


            itemTitle1 = (TextView) v.findViewById(R.id.tile_title);
            itemChange1 = (ImageView) v.findViewById(R.id.tile_change);
            itemTitle1.setVisibility(itemTitle1.VISIBLE);
            itemChange1.setVisibility(itemChange1.VISIBLE);

            text2 = oo.obtenerPares(position, SetName, EsquemaMemorama.EntradaMemorama.TABLE_NAME);
            if(text2.equals(other))
            {
                Snackbar.make(v, "Correcto", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                other="";
            }
            else
            {
                Snackbar.make(v, "Incorrecto", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                h.postDelayed(r, 1000);
                /*
                itemChange.setVisibility(itemChange.INVISIBLE);
                itemTitle.setVisibility(itemTitle.INVISIBLE);
                itemChange1.setVisibility(itemChange1.INVISIBLE);
                itemTitle1.setVisibility(itemTitle1.INVISIBLE);
                */
                other="";
            }

        }



        /*
        FlashcardsDBHelper oo = new FlashcardsDBHelper(this);
        String nombre = oo.obtenerND(0, position, EsquemaFlashcard.EntradaFlashcard.TABLE_NAME);

        //String Psrposition = v.getTag().toString();
        //String pNombre = mNombre.getText().toString();
        Context context = v.getContext();

        Intent showFCs = new Intent(context, showFlashcards.class);
        showFCs.putExtra("Nombre", nombre);
        context.startActivity(showFCs);
        */

    }
}
