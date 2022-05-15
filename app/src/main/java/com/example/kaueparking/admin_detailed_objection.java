package com.example.kaueparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class admin_detailed_objection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detailed_objection);
        TextView driverName, driverID, ticketID, plate, price, time, location,description;
        Button approve,reject;
        ImageView ticketImg;
        Bundle b = getIntent().getExtras();
        String tickID = b.getString("ticketID");
        System.out.println(tickID + " ticket ID from Bundle");
        DBHelper db = new DBHelper(this);
        driverName = findViewById(R.id.DriverName);
        driverID = findViewById(R.id.driverID);
        ticketID = findViewById(R.id.ticketId);
        plate = findViewById(R.id.plateNum);
        price = findViewById(R.id.ticketPrice);
        time= findViewById(R.id.ticketTime);
        location = findViewById(R.id.ticketLocation);
        description = findViewById(R.id.ticketDiscription);
        approve = findViewById(R.id.ticketApprove);
        reject = findViewById(R.id.TicketReject);
        ticketImg = findViewById(R.id.ticketImg);
        Ticket t = (Ticket) db.getData("ticket",tickID);
        Driver d = (Driver) db.getData("driver",t.getDriverID());
        driverName.setText(d.getName());
        driverID.setText(d.getId());
        ticketID.setText(t.getId()+"");
        plate.setText(t.getPlate());
        price.setText(t.getPrice());
        time.setText(t.getTime());
        location.setText(t.getLocation());
        System.out.println(t.getDescription()+" description of ticket");
        description.setText(t.getDescription());
        if (t.getViolationImg()!= null){
            ticketImg.setImageBitmap(t.getViolationImg());
        }else{
            Toast.makeText(getApplicationContext(), "Ticket don't have an image!", Toast.LENGTH_LONG).show();
        }

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.approveTicket(t.getId()+"")){
                    Toast.makeText(getApplicationContext(), "Ticket approved successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.rejectTicket(t.getId()+"")){
                    Toast.makeText(getApplicationContext(), "Ticket rejected successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }



            }
        });



    }



}