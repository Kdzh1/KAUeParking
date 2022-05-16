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

public class DriverObjectedAdapter extends ArrayAdapter {

    Context adapterContext;
    int adapterResource;
    Ticket[] adapterTickets;

    public DriverObjectedAdapter(@NonNull Context context, int resource, @NonNull Ticket[] tickets) {
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

        TextView ticketId =  row.findViewById(R.id.DriverObjected_Tid);
        TextView ticketPrice=row.findViewById(R.id.DriverObjected_price);
        TextView ticketTime = row.findViewById(R.id.DriverObjected_time);
        TextView ticketLocation = row.findViewById(R.id.DriverObjected_location);
        TextView adminRespons= row.findViewById(R.id.DriverObjectedAdmin_respons);
        TextView ticketPaid = row.findViewById(R.id.DriverObjected_status);
        Ticket tick=adapterTickets[position];


        if (tick.getApproved()==0){
            adminRespons.setText("Not checked yet");

        }else if (tick.getApproved()==2){
            adminRespons.setText("Ticket is approved");
        }else{
            adminRespons.setText("Ticket is dropped");
        }

        ticketId.setText(tick.getId()+"");
        ticketPrice.setText(tick.getPrice());
        ticketTime.setText(tick.getTime());
        ticketLocation.setText(tick.getLocation());

        if (tick.getStatus()==0){
            ticketPaid.setText("Not Paid");
        }else{
            ticketPaid.setText("Paid");
        }




        return row;
    }



}
