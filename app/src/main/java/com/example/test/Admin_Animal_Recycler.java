package com.example.test;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class Admin_Animal_Recycler extends RecyclerView.Adapter<Admin_Animal_Recycler.ViewHolder> {

    Context context;
    ArrayList<animal> animalsItemArrayList;
    DatabaseReference databaseReference;

    public Admin_Animal_Recycler(Context context, ArrayList<animal> animalsItemArrayList) {
        this.context = context;
        this.animalsItemArrayList = animalsItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_itema_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        animal animal = animalsItemArrayList.get(position);

        holder.Image.findViewById( R.id.tvimage );
        holder.Name.setText( animal.getAge() );
        holder.Age.setText( animal.getAge() );

        holder.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context,  animal.getImage(), animal.getName(), animal.getAge());
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, animal.getName() , animal.getAge(),animal.getImage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return animalsItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Image ;
        TextView Name;
        TextView Age;

        Button btDelete;
        Button btUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.tvfirstName);
            Age = itemView.findViewById(R.id.tvage);
            Image= itemView.findViewById(R.id.tvimage);

            btDelete = itemView.findViewById(R.id.btDelete);
            btUpdate = itemView.findViewById(R.id.btUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String name, String image, String age) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.activity_itema_admin);

            EditText Name = dialog.findViewById(R.id.tvfirstName);
            EditText Image = dialog.findViewById(R.id.tvimage);
            EditText Age = dialog.findViewById(R.id.tvage);

            Name.setText(name);
            Image.setText( image );
            Age.setText( age );


            Button buttonUpdate = dialog.findViewById(R.id.btAdd);
            Button buttonCancel = dialog.findViewById(R.id.btDelete);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nouveau_Name = Name.getText().toString();
                    String nouveau_Age = Age.getText().toString();
                    String nouveau_Image = Image.getText().toString();

                    if (nouveau_Name.isEmpty() || nouveau_Age.isEmpty()|| nouveau_Image.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    }

                      else {
                            databaseReference.child("animal").child(name).setValue(animalsItemArrayList);
                        Toast.makeText(context, "animal est modifier", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }



                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String name, String age, String image) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.supp_conf);

            Button buttonDelete = dialog.findViewById(R.id.btDelete);
            Button buttonCancel = dialog.findViewById(R.id.btCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

           buttonDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                  databaseReference.child("animal").child( name );
                  Toast.makeText(context, "animal  est supprimer!", Toast.LENGTH_SHORT).show();
                  dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }
}