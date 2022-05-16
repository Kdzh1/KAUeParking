package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class delete_security extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_security);
        Button delete = findViewById(R.id.deleteBtn);
        EditText securityID= findViewById(R.id.secID);
        DBHelper db = new DBHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = securityID.toString();
                db.deleteData("security",db.getData("security",id));
            }
        });
    }
}