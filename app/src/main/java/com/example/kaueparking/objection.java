package com.example.kaueparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class objection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView Status,Id,Price,Time,Location;
        Button btn;
        DBHelper db = new DBHelper(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_make_objection);
        Bundle b = getIntent().getExtras();
        int ticketID=b.getInt("ticketID");
        String price = b.getString("ticketPrice");
        String time = b.getString("ticketTime");
        String location = b.getString("ticketLocation");
        int status = b.getInt("ticketStatus");
        Status = findViewById(R.id.status);
        Id= findViewById(R.id.id);
        Price = findViewById(R.id.price);
        Time = findViewById(R.id.time);
        Location = findViewById(R.id.location);
        if(status==0){
            Status.setText("Not Paid");
        }else{
            Status.setText("Paid");
        }

        Id.setText(ticketID+"");
        Price.setText(price);
        Time.setText(time);
        Location.setText(location);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Description = findViewById(R.id.description);
                String s = Description.getText().toString();
                db.addDescription(ticketID+"",s);
                if (db.makeObjection(ticketID+"")){

                    alert.setTitle("Objection");
                    alert.setMessage("Your Objection Sent Successfully");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alert.create().show();
                }
            }
        });

    }
}