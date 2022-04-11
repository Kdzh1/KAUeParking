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

        // attributes in this class
        ImageButton newFine = findViewById(R.id.addNewTktBtn); //the button of new ticket (its imgBtn not just Btn)
        ImageButton instructionBtn =(ImageButton)findViewById(R.id.instructionBtn);
        ImageButton infoBtn=(ImageButton)findViewById(R.id.infoBtn);
        //

        // Instructions
        instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openInstruction(); }
        });
        //

        // Add new ticket
        newFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFine();
            }
        });
        //

        // My information
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openMyinformation(); }
        });
        //
    }
    public void openInstruction(){
        Intent Insintent= new Intent(this, Instruction.class); // Instruction Intent
        startActivity(Insintent);
    }
    public void openNewFine(){

            Intent Tktintent = new Intent(this, newFine.class); // Add new Ticket Intent
            startActivity(Tktintent);

    }
    public void openMyinformation(){

        Intent Infointent = new Intent(this, myinformation.class); // Add new Ticket Intent
        startActivity(Infointent);

    }

}
