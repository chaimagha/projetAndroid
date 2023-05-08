package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class animalList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<animal> list;
    private MyAdapter myAdapter;
    Button button=findViewById( R.id.button );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_animallist );


        recyclerView = findViewById( R.id.userlist );
        database = FirebaseDatabase.getInstance().getReference("Animal");
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this) );
        list=new ArrayList<>();
         myAdapter = new MyAdapter(this,list);
       recyclerView.setAdapter( myAdapter);

        database.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    animal animal = dataSnapshot.getValue( animal.class );
                    list.add( animal );
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

     button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(animalList.this, Confirmation.class));
         }
     });


    }





}






