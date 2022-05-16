package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editsecurity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editsecurity);
        EditText secID = findViewById(R.id.SecurityID);
        Button searchBtn = findViewById(R.id.search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditSecInfo(secID.toString());
            }
        });
    }
    public void openEditSecInfo(String id){
        Intent intent = new Intent(this, editsecurityinfo.class);
        intent.putExtra("SecID",id);
        startActivity(intent);
    }
}