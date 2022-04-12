package com.example.kaueparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SecurityActivity extends AppCompatActivity {

    //imgBtn=Image Button , Btn=Button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securityhomepage);
        Bundle b = getIntent().getExtras();
        String securityID = b.getString("id");
        System.out.println(securityID);
        // attributes in this class
        ImageButton newFine = findViewById(R.id.addNewTktBtn); //the button of new ticket (its imgBtn not just Btn)
        ImageButton instructionBtn = (ImageButton) findViewById(R.id.instructionBtn);
        ImageButton infoBtn = (ImageButton) findViewById(R.id.infoBtn);
        //

        // Add new ticket
        newFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFine();
            }
        });
        //

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
                openMyinformation(securityID);
            }
        });
        //
    }

    public void openInstruction() {
        Intent Insintent = new Intent(this, Instruction.class);
        startActivity(Insintent);
    }

    public void openMyinformation(String id) {

        Intent Infointent = new Intent(this, information.class);
        Infointent.putExtra("ID",id);
        startActivity(Infointent);

    }

    public void openNewFine() {

        Intent Tktintent = new Intent(this, newFine.class); // Add new Ticket Intent
        startActivity(Tktintent);

    }
}
