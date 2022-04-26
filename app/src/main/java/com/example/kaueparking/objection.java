package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class objection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_make_objection);
        Bundle b = getIntent().getExtras();
        String ticketID=b.getString("ticketID");
        String price = b.getString("ticketPrice");
        String time = b.getString("ticketTime");
        String location = b.getString("ticketLocation");
        String status = b.getString("ticketStatus");

    }
}