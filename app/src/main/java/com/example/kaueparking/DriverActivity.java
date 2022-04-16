package com.example.kaueparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DriverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driverhomepage);
        Bundle b = getIntent().getExtras();
        String driverID = b.getString("id");
        System.out.println(driverID);
        ImageButton instructionBtn = (ImageButton) findViewById(R.id.driverinstructionBtn);
        ImageButton infoBtn = (ImageButton) findViewById(R.id.driverInfoBtn);
        TextView ticketHistory = findViewById(R.id.ticket_history);

        ticketHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHistory(driverID);
            }
        });

        // Instructions
        instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstruction();
            }
        });
        //

        // My information
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyinformation(driverID);
            }
        });


    }

    public void openMyinformation(String id) {

        Intent Infointent = new Intent(this, information.class); // Add new Ticket Intent
        Infointent.putExtra("ID",id);
        startActivity(Infointent);

    }

    public void openInstruction() {
        Intent Insintent = new Intent(this, Instruction.class); // Instruction Intent
        startActivity(Insintent);
    }
    public void openHistory(String id){
        Intent Insintent = new Intent(this, history.class); // Instruction Intent
        Insintent.putExtra("ID",id);
        startActivity(Insintent);
    }
}
