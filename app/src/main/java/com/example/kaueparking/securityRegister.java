package com.example.kaueparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class securityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_register);
        Button signUpBtn = findViewById(R.id.SignUp);

        DBHelper db = new DBHelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Security s = new Security();
                EditText name = findViewById(R.id.Name);
                EditText pass = findViewById(R.id.Pass);
                EditText phone = findViewById(R.id.Phone);
                s.setName(name.getText().toString());
                s.setPassword(pass.getText().toString());
                s.setPhone(phone.getText().toString());
                s.setId(db.getNewSecurityID());
                if (s.verifyInfo()) {
                    alert.setTitle("New Security ID is: ");
                    alert.setMessage(db.getNewSecurityID());       // IN CASE OF WRONG PASS OR ID THEN POP UP AN ALERT
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alert.create().show();
                    if (db.insertData(s)) {
                        Toast.makeText(getApplicationContext(), "New security added successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are filled out", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}