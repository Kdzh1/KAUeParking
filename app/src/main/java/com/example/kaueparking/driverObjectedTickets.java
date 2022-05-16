package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class driverObjectedTickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_objected_tickets2);


        DBHelper db = new DBHelper(this);
        Bundle b = getIntent().getExtras();
        String ID = b.getString("ID");

        ArrayList tick = db.getAllTicket(ID);
        ArrayList ObjectedTickt=new ArrayList();
        Ticket[] OBT;


        for (int i=0; i < tick.size();i++){

            Ticket tmp= (Ticket) tick.get(i);
            if (tmp.getApproved() != 1){
                ObjectedTickt.add(tmp);
            }
        }

        OBT=new Ticket[tick.size()];

        for (int j = 0; j < OBT.length; j++) {


            OBT[j]= (Ticket) tick.get(j);
        }

        int xml=R.layout.objected_row;

        DriverObjectedAdapter driverObjectedAdapter= new DriverObjectedAdapter (driverObjectedTickets.this,xml,OBT);

        ListView listView= findViewById(R.id.ObjectedListview);

        listView.setAdapter(driverObjectedAdapter);
    }
}