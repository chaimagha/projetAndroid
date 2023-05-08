package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<animal> list;
    Button button;
    public MyAdapter(animalList animalList, ArrayList<animal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( (R.layout.item),parent,false) ;
        return new MyViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       animal user= list.get(position);

       holder.Name.setText( user.getName() );
       holder.age.setText( user.getAge() );
       holder.image.findViewById( R.id.tvimage );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name , age;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            Name = itemView.findViewById( R.id.tvfirstName );
            age =itemView.findViewById( R.id.tvage);
            image=itemView.findViewById( R.id.tvimage );


        }

    }
}
