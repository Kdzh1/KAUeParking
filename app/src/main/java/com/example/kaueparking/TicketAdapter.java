package com.example.kaueparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TicketAdapter extends ArrayAdapter {
    Context adapterContext;
    int adapterResource;
    Ticket[] tickets;

    public TicketAdapter(@NonNull Context context, int resource, @NonNull Ticket[] tickets) {
        super(context, resource, tickets);

        adapterContext=context;
        adapterResource=resource;
        tickets=this.tickets;


    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row ;
        LayoutInflater rowInf=LayoutInflater.from(adapterContext);
        row=rowInf.inflate(adapterResource,parent,false);

        TextView ticketId = (TextView) row.findViewById(R.id.ticket_id);
        TextView ticketPrice=row.findViewById(R.id.ticket_price);
        TextView ticketTime = row.findViewById(R.id.ticket_time);
        TextView ticketLocation = row.findViewById(R.id.ticket_location);
        Button objectionBT=row.findViewById(R.id.objectionBT);

        Ticket tick=tickets[position];

        // take the values from each ticket and desplay it
        ticketId.setText(tick.getId());
        ticketPrice.setText(tick.getPrice());
        ticketTime.setText(tick.getTime());
        ticketLocation.setText(tick.getLocation());




        return row;
    }

}
