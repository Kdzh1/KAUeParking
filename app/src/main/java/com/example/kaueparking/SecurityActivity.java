package com.example.kaueparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecurityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securityhomepage);
        TextView newFine = findViewById(R.id.userID);
        newFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFine();
            }
        });
    }
    public void openNewFine(){

            Intent intent = new Intent(this, newFine.class);
            startActivity(intent);

    }
}
