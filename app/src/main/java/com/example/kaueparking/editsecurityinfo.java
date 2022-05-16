package com.example.kaueparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class editsecurityinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editsecurityinfo);
        Bundle b = getIntent().getExtras();
        String securityID = b.getString("SecID");
        DBHelper db = new DBHelper(this);
        System.out.println(securityID+" this is sec id");
        Security s = (Security) db.getData("security",securityID);
        if(s!=null){
            
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(" ");
            alert.setMessage("Security not found!");       // IN CASE OF WRONG PASS OR ID THEN POP UP AN ALERT
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.create().show();
        }
    }
}