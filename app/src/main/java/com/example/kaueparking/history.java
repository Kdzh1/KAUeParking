package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DBHelper db = new DBHelper(this);
        Bundle b = getIntent().getExtras();
        String ID = b.getString("ID");

        ArrayList tick = db.getTicket(ID);



        Ticket[] tickets=(Ticket[]) tick.toArray();

        int xml=R.layout.row;
        TicketAdapter ticketAdapter = new TicketAdapter(history.this,xml,tickets);

        ListView listView= findViewById(R.id.Listview);

        listView.setAdapter(ticketAdapter);






    }
}