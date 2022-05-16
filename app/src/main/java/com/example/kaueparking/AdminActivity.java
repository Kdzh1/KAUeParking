package com.example.kaueparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        ImageButton infoBtn =  findViewById(R.id.myInfoBtn);
        ImageButton addSec = findViewById(R.id.addNewSecBtn);
        ImageButton obj= findViewById(R.id.objectionBtn);
        addSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSecurity();
            }
        });

        // Objection
        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openObjection(); }
        });

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

        public void openObjection(){
            Intent Adminobjectionintent = new Intent(this, admin_objection.class);
            startActivity(Adminobjectionintent);

        }
        public void openAddSecurity(){
        Intent intent = new Intent(this, securityRegister.class);
        startActivity(intent);
        }
    public void openEditSecurity(){
        Intent intent = new Intent(this, editsecurity.class);
        startActivity(intent);
    }
    public void openRemoveSecurity(){
        Intent intent = new Intent(this, delete_security.class);
        startActivity(intent);
    }
}
