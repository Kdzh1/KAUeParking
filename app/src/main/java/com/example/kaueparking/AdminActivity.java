package com.example.kaueparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhomepage);


        ImageButton infoBtn = (ImageButton) findViewById(R.id.myInfoBtn);


        // My information
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyinformation();
            }


        });

    }
        public void openMyinformation() {

            Intent Infointent = new Intent(this, myinformation.class); // Add new Ticket Intent
            startActivity(Infointent);

        }
}
