package com.example.android_volley_library_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class Adapter extends  RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private  Context context;
    private  User[] users;
    public Adapter(Context context,User[] users) {
        this.context=context;
        this.users=users;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.user_view,parent,false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      User user=users[position];
      holder.txtUser.setText(user.getLogin());
        Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
     // in this on bind we add the listener to one view so that on click we go  to detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getLogin()+"is clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView imgUser;
        TextView txtUser;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.imgUser);
            txtUser=itemView.findViewById(R.id.txtUser);

        }
    }
}
