package com.example.kaueparking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = findViewById(R.id.login);
        DBHelper db = new DBHelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userID = findViewById(R.id.userid);
                EditText password = findViewById(R.id.userpass);
                try {
                    Integer.parseInt(userID.getText().toString()); // to make sure that the ID is only numbers


                }catch (NumberFormatException e){
                    System.out.println(e);
                    Toast.makeText(getApplicationContext(), "ID Should be numbers only!", Toast.LENGTH_LONG).show();
                }



                String text = userID.getText().toString();
                String pass = password.getText().toString();

                if (text.startsWith("0")) {  // MEANS THAT IS AN ADMIN TRYING TO LOG IN
                    try {


                        Admin a;
                        a = (Admin) db.getData("admin", text); //BRING THE DATA FROM DATABASE TO COMPARE IT

                        if (a.getPassword().equals(pass)) {
                            openAdmin(text);                    // IF THE PASSWORD IS CORRECT THEN GO TO NEXT MENU
                        } else {

                            alert.setTitle(" ");
                            alert.setMessage("Wrong ID or Password");       // IN CASE OF WRONG PASS OR ID THEN POP UP AN ALERT
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            alert.create().show();
                        }
                    }catch (NullPointerException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "No such admin ID!", Toast.LENGTH_LONG).show();
                    }catch (SQLiteException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "Error in database", Toast.LENGTH_LONG).show();
                    }

                } else if (text.startsWith("1")) {
                    try {
                        Security s = (Security) db.getData("security", text);
                        if (s.getPassword().equals(pass)) {
                            openSecurity(text);
                        } else {

                            alert.setTitle(" ");
                            alert.setMessage("Wrong ID or Password");
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            alert.create().show();
                        }
                    }catch (NullPointerException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "No such security ID!", Toast.LENGTH_LONG).show();
                    }catch (SQLiteException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "Error in database", Toast.LENGTH_LONG).show();
                    }

                } else {
                    try{
                    Driver d = (Driver) db.getData("driver", text);
                    if (d.getPassword().equals(pass)) {
                        openDriver(text);
                    } else {
                        alert.setTitle(" ");
                        alert.setMessage("Wrong ID or Password");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alert.create().show();
                    }
                }catch (NullPointerException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "No such driver ID!", Toast.LENGTH_LONG).show();
                    }catch (SQLiteException e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), "Error in database", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }


    private void openAdmin(String id){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }

    private void openSecurity(String id){
        Intent intent = new Intent(this, SecurityActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }
    private void openDriver(String id){
        Intent intent = new Intent(this, DriverActivity.class);

        intent.putExtra("id",id);
        startActivity(intent);
    }
}