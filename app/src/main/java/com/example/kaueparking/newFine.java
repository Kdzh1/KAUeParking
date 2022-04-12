package com.example.kaueparking;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class newFine extends AppCompatActivity {
    ImageView plateImage, violationImage;
    TextView plateInfo, time, price;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fine);
        plateImage = findViewById(R.id.plateImage); // To Control image
        violationImage = findViewById(R.id.violationImage);
        plateInfo = findViewById(R.id.plate);
        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        submit = findViewById(R.id.submit);

        DBHelper db = new DBHelper(this);
        Ticket ticket = new Ticket();
        // Check if permission is granted for camera
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { // If the permission is not granted
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101); // requestCode can accept any value

        }
    }

    public void doProcess(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Open the camera through the intent
        startActivityForResult(intent, 101); // the reason why selecting ForResult is after taking the picture, process will occur
        // After this command is executed, onActivityResult method will automatically called


        // ### Get Location ###


        FusedLocationProviderClient fusedLocationProviderClient;

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(newFine.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getLocation(fusedLocationProviderClient);

        } else {
            ActivityCompat.requestPermissions(newFine.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void getLocation(FusedLocationProviderClient f) {
        TextView tv2, tv3;

        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        f.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {


                    try {
                        Geocoder geocoder = new Geocoder(newFine.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);

                        tv2.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name :</b></font>"
                        + addresses.get(0).getCountryName()));

                        tv3.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</b></font>"
                                + addresses.get(0).getAddressLine(0)));


                        ticket.setLocation(addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    Ticket ticket = new Ticket() ;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        DBHelper db = new DBHelper(this);


        // The remaining steps after taking a picture will be here
        // Extracting the text from the picture...
        if (requestCode==101) {

            Bundle bundle = data.getExtras(); // From this bundle object we can extract the image
            Bitmap bitmap = (Bitmap) bundle.get("data"); // Here we have the taken picture
            plateImage.setImageBitmap(bitmap); // Display the taken picture
            // Process the image
            // 1.To create a FirebaseVisionImage object from a Bitmap object:
            FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
            // 2.Get an instance of FirebaseVision
            FirebaseVision firebaseVision = FirebaseVision.getInstance();
            // 3.Create an instance of FirebaseVisionTextRecognizer
            FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = firebaseVision.getOnDeviceTextRecognizer();
            //4.Create a task to process the image
            Task<FirebaseVisionText> task = firebaseVisionTextRecognizer.processImage(firebaseVisionImage);
            //5.If the task is success
            task.addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {

                    String s = firebaseVisionText.getText();

                    String[] text = s.split(" ");
                    String numbers = "";
                    String letters = "";
                    for (int i = 0; i < text.length; i++) {
                        if (text[i].length() > 3) {
                            numbers = text[i];

                        } else if (text[i].length() == 3) {
                            letters = text[i];
                        }
                    }
                    String ss = numbers + letters;

                    ticket.setId(Integer.parseInt(db.getTicketID())+1);
                    ticket.setPrice("150");
                    ticket.setPlate(ss);
                    plateInfo.setText(ss);
                    String currentTime = "" + Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes();
                    ticket.setTime(currentTime);
                    time.setText(currentTime); // displaying hours and minutes only
                    price.setText("150");


                    ticket.setDriverID(db.getDriverID(ss));

                }
            });
            //6.if the task failed
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else if (requestCode==102){
            Bundle bundle = data.getExtras(); // From this bundle object we can get the image
            Bitmap bitmap = (Bitmap) bundle.get("data"); // Here we have the taken picture
            violationImage.setImageBitmap(bitmap);
            ticket.setViolationImg(bitmap);

        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.insertData(ticket);
            }
        });
    }

    public void doProcess2(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,102);

    }


    public void submit(View view, Ticket t) {

    }


}