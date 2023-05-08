package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AjoutAnimal extends AppCompatActivity {

    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArrayList<animal> animalsItemArrayList;
    Admin_Animal_Recycler adapter;

    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animalsItemArrayList = new ArrayList<>();

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(AjoutAnimal.this);
            }
        });

        readData();
    }

    private void readData() {

        databaseReference.child("animal").orderByChild("name").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               animalsItemArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    animal animal = dataSnapshot.getValue(animal.class);
                    animalsItemArrayList.add(animal);
                }
                adapter = new Admin_Animal_Recycler(AjoutAnimal.this,animalsItemArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public class ViewDialogAdd {
        public void showDialog(Context context) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.activity_add);

            EditText image = dialog.findViewById(R.id.tvimage);
            EditText name = dialog.findViewById(R.id.tvfirstName);
            EditText age= dialog.findViewById(R.id.tvage);


            Button buttonAdd = dialog.findViewById(R.id.btAdd);
            Button buttonCancel = dialog.findViewById(R.id.btCancel);

            btAdd.setText("Ajouter");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Name = name.getText().toString();
                    String Image=image.getText().toString();
                    String Age = age.getText().toString();

                    if (Name.isEmpty() || Image.isEmpty() || Age.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("animal").child(Name).setValue(new animal(name,image,age));
                        Toast.makeText(context, "DONE!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}