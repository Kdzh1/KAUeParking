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
        Bundle b = getIntent().getExtras();
        String adminID = b.getString("id");
        System.out.println(adminID);
        ImageButton infoBtn = (ImageButton) findViewById(R.id.myInfoBtn);


        // My information
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyinformation(adminID);
            }


        });

    }
        public void openMyinformation(String id) {

            Intent Infointent = new Intent(this, information.class); // Add new Ticket Intent
            Infointent.putExtra("ID",id);
            startActivity(Infointent);

        }
}
