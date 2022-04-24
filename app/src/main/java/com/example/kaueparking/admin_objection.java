package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_objection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_objection);
        Button obj=findViewById(R.id.detail);

        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdmin();
            }
        });

    }
    private void openAdmin(){
        Intent intent = new Intent(this, admin_detailed_objection.class);
        startActivity(intent);

    }
}