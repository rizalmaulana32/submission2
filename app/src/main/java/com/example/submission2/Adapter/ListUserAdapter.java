package com.example.submission2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.DetailUserActivity;
import com.example.submission2.R;
import com.example.submission2.Model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListViewHolder> {

    public static final String DATA_USER = "datauser";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private ArrayList<UserModel> listItem;

    public ListUserAdapter(Context context, ArrayList<UserModel> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ListUserAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_users, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ListViewHolder holder, int position) {
        Glide.with(context)
                .load(listItem.get(position).getAvatarUrl())
                .into(holder.imgUser);
        holder.tvLogin.setText(listItem.get(position).getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDetailActivity = new Intent(context, DetailUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(listItem.get(position)));
                moveDetailActivity.putExtra(DATA_EXTRA, bundle);
                context.startActivity(moveDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView tvLogin;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.img_users_row);
            tvLogin = itemView.findViewById(R.id.tv_login_row);
        }
    }
}
