package com.example.kaueparking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button)findViewById(R.id.login);
        DBHelper db = new DBHelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userID = (EditText) findViewById(R.id.userid);
                EditText password = findViewById(R.id.userpass);

                String text = userID.getText().toString();
                String pass = password.getText().toString();
                if(text.startsWith("0")){           // MEANS THAT IS AN ADMIN TRYING TO LOG IN
                    Admin a;
                    a = (Admin) db.getData("admin",text); //BRING THE DATA FROM DATABASE TO COMPARE IT

                    if(a!= null && a.getPassword().equals(pass)){
                        openAdmin(text);                    // IF THE PASSWORD IS CORRECT THEN GO TO NEXT MENU
                    }else{

                        alert.setTitle(" ");
                        alert.setMessage("Wrong ID or Password");       // IN CASE OF WRONG PASS OR ID THEN POP UP AN ALERT
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alert.create().show();
                    }
                }else if (text.startsWith("1")){
                    Security s = new Security();
                    s = (Security) db.getData("security", text);
                    if (s!=null && s.getPassword().equals(pass)){
                        openSecurity(text);
                    }else{

                        alert.setTitle(" ");
                        alert.setMessage("Wrong ID or Password");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alert.create().show();
                    }
                }else{
                    Driver d = new Driver();
                    d= (Driver) db.getData("driver", text);
                    if (d!=null && d.getPassword().equals(pass)){
                        openDriver(text);
                    }else{
                        alert.setTitle(" ");
                        alert.setMessage("Wrong ID or Password");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        alert.create().show();
                    }

                }
            }
        });
    }


    private void openAdmin(String id){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
        intent.putExtra("id",id);
    }

    private void openSecurity(String id){
        Intent intent = new Intent(this, SecurityActivity.class);
        startActivity(intent);
        intent.putExtra("id",id);
    }
    private void openDriver(String id){
        Intent intent = new Intent(this, DriverActivity.class);
        startActivity(intent);
        intent.putExtra("id",id);
    }
}