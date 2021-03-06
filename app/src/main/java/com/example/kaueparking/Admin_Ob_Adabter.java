package com.example.kaueparking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Admin_Ob_Adabter extends ArrayAdapter {

    Context adapterContext;
    int adapterResource;
    Ticket[] adapterTickets;

    public Admin_Ob_Adabter(@NonNull Context context, int resource, @NonNull Ticket[] tickets) {
        super(context, resource, tickets);

        adapterContext=context;
        adapterResource=resource;
        adapterTickets=tickets;

    }

    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row ;
        LayoutInflater rowInf=LayoutInflater.from(adapterContext);
        row=rowInf.inflate(adapterResource,parent,false);

        TextView ticketId =  row.findViewById(R.id.DOT_Tid);
        TextView ticketPrice=row.findViewById(R.id.DOT_price);
        TextView ticketTime = row.findViewById(R.id.DOT_time);
        TextView ticketLocation = row.findViewById(R.id.DOT_location);
        Button detail=row.findViewById(R.id.admin_respons);
        TextView ticketPaid = row.findViewById(R.id.DOT_status);
        Ticket tick=adapterTickets[position];


        //if(tick.getApproved()==2){
           // objectionBT.setVisibility(View.INVISIBLE);
        //}
        // take the values from each ticket and desplay it
        ticketId.setText(tick.getId()+"");
        ticketPrice.setText(tick.getPrice());
        ticketTime.setText(tick.getTime());
        ticketLocation.setText(tick.getLocation());
        if (tick.getStatus()==0){
            ticketPaid.setText("Not Paid");
        }else{
            ticketPaid.setText("Paid");
        }
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(adapterContext,admin_detailed_objection.class);
                intent.putExtra("ticketID",tick.getId()+"");

                adapterContext.startActivity(intent);


            }
        });



        return row;
    }



}
