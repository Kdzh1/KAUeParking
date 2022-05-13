package com.example.kaueparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button signUpBtn = findViewById(R.id.SignUp);
        TextView toSignIn = findViewById(R.id.toSignIn);
        DBHelper db = new DBHelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Driver d = new Driver();
                EditText name = findViewById(R.id.Name);
                EditText pass = findViewById(R.id.Pass);
                EditText phone = findViewById(R.id.Phone);
                d.setName(name.getText().toString());
                d.setPassword(pass.getText().toString());
                d.setPhone(phone.getText().toString());
                if (d.verifyInfo()) {
                d.setId(db.getNewSecurityID());
                alert.setTitle("New Security ID is: ");
                alert.setMessage(db.getNewSecurityID());       // IN CASE OF WRONG PASS OR ID THEN POP UP AN ALERT
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert.create().show();

                    if (db.insertData(d)) {
                        Toast.makeText(getApplicationContext(), "New security added successfully", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are filled out", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}