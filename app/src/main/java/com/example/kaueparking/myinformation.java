package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class myinformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView name, id, password , phone;
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        setContentView(R.layout.activity_myinformation);
        String ID= getIntent().getStringExtra("ID");
        DBHelper db = new DBHelper(this);
        if (ID.startsWith("0")){
            Admin a = (Admin) db.getData("admin",ID);
            name.setText(a.getName());
            id.setText(a.getId());
            password.setText(a.getPassword());
            phone.setText(a.getPhone());
        }else if (ID.startsWith("1")){
            Security s = (Security) db.getData("security",ID);
            name.setText(s.getName());
            id.setText(s.getId());
            password.setText(s.getPassword());
            phone.setText(s.getPhone());
        }else{
            Driver d = (Driver) db.getData("driver",ID);
            name.setText(d.getName());
            id.setText(d.getId());
            password.setText(d.getPassword());
            phone.setText(d.getPhone());
        }

    }





}