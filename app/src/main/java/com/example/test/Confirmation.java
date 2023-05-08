package com.example.test;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Confirmation extends AppCompatActivity {
  TextView Retour= findViewById(R.id.Retour);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation );

        Retour.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Confirmation.this, animalList.class));
            }
        });


    }


    }