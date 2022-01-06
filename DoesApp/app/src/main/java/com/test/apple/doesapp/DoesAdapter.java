package com.test.apple.doesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyDoes> myDoes;



    public DoesAdapter(Context context, ArrayList<MyDoes> myDoes) {
        this.context = context;
        this.myDoes = myDoes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.titleDoes.setText(myDoes.get(i).getTitleDoes());
        viewHolder.descDoes.setText(myDoes.get(i).getDescDoes());
        viewHolder.dateDoes.setText(myDoes.get(i).getDateDoes());

        final String getTitleDoes = myDoes.get(i).getTitleDoes();
        final String getDescDoes = myDoes.get(i).getDescDoes();
        final String getDateDoes = myDoes.get(i).getDateDoes();
        final String getKeyDoes = myDoes.get(i).getKeyDoes();



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //your code here ...
                    Intent intent = new Intent(context,EditTaskDesk.class);
                    intent.putExtra("titleDoes", getTitleDoes);
                    intent.putExtra("descDoes", getDescDoes);
                    intent.putExtra("dateDoes", getDateDoes);
                    intent.putExtra("keyDoes", getKeyDoes);

                    context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleDoes, descDoes, dateDoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleDoes = (TextView) itemView.findViewById(R.id.titleDoes);
            descDoes = (TextView) itemView.findViewById(R.id.descDoes);
            dateDoes = (TextView) itemView.findViewById(R.id.dateDoes);
        }
    }
}
