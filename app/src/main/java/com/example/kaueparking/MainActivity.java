package com.example.kaueparking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = findViewById(R.id.login);
        TextView singup = findViewById(R.id.Singup);
        Context context = this;


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
                verifyUser(text,pass,context);
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSingup();
            }
        });
    }

    private void openSingup() {

        Intent intent=new Intent(this,register.class);
        startActivity(intent);
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
    public void verifyUser(String id, String pass, Context context){

        DBHelper db = new DBHelper(context);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        if (id.startsWith("0")) {  // MEANS THAT IS AN ADMIN TRYING TO LOG IN

            try {

                Admin a;
                a = (Admin) db.getData("admin", id); //BRING THE DATA FROM DATABASE TO COMPARE IT

                if (a.verifyPass(pass)) {
                    openAdmin(id);                    // IF THE PASSWORD IS CORRECT THEN GO TO NEXT MENU
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

        } else if (id.startsWith("1")) {
            try {
                Security s = (Security) db.getData("security", id);
                if (s.verifyPass(pass)) {
                    openSecurity(id);
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
                Driver d = (Driver) db.getData("driver", id);
                if (d.verifyPass(pass)) {
                    openDriver(id);
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
    public Context theContext(){
        return this;
    }
}