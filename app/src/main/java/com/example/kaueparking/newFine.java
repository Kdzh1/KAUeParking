package com.example.kaueparking;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.Calendar;
import java.util.Date;

public class newFine extends AppCompatActivity {
    ImageView imageView;
    TextView plateInfo, time, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fine);
        imageView = findViewById(R.id.imageId); // To Control image
        plateInfo = findViewById(R.id.plate);
        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        // Check if permission is granted for camera
        if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){ // If the permission is not granted
            requestPermissions(new String[]{Manifest.permission.CAMERA},101); // requestCode can accept any value

        }
    }

    public void doProcess(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Open the camera through the intent
        startActivityForResult(intent , 101); // the reason why selecting ForResult is after taking the picture, process will occur
        // After this command is executed, onActivityResult method will automatically called
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // The remaining steps after taking a picture will be here
        // Extracting the text from the picture...
        Bundle bundle = data.getExtras(); // From this bundle object we can extract the image
        Bitmap bitmap = (Bitmap) bundle.get("data"); // Here we have the taken picture
        imageView.setImageBitmap(bitmap); // Display the taken picture
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
                String [] text = s.split(" ");
                String numbers="";
                String letters="";
                for (int i = 0; i < text.length; i++) {
                    if (text[i].length()>3){
                        numbers=text[i];

                    }else if (text[i].length()==3){
                        letters= text[i];
                    }
                }
                String ss = numbers+"\t"+letters;
                plateInfo.setText(ss);
                String currentTime = ""+Calendar.getInstance().getTime().getHours()+":"+Calendar.getInstance().getTime().getMinutes();
                time.setText(currentTime); // displaying hours and minutes only
                price.setText("150");


            }
        });
        //6.if the task failed
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}