package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class admin_objection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_objection);

// Get the Objected Tickets from the database

        DBHelper db = new DBHelper(this);

        ArrayList tick = db.getObjectedTickets();


        Ticket[] tickets = new Ticket[tick.size()];
        for (int i = 0; i < tickets.length; i++) {
            tickets[i]=(Ticket) tick.get(i);
        }

        int xml=R.layout.admin_row;

        Admin_Ob_Adabter adminAdabter=new Admin_Ob_Adabter(admin_objection.this,xml,tickets);

        ListView AdmineListView=findViewById(R.id.Admin_Listview);

        AdmineListView.setAdapter(adminAdabter);




    }

}