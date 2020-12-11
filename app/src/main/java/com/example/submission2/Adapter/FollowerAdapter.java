package com.example.submission2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.Model.FollowerModel;
import com.example.submission2.R;

import java.util.ArrayList;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<FollowerModel> listItem;

    public FollowerAdapter(Context context, ArrayList<FollowerModel> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public FollowerAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_follower, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerAdapter.ListViewHolder holder, int position) {
        Glide.with(context)
                .load(listItem.get(position).getAvatarUrl())
                .into(holder.imgUserFollower);
        holder.tvUserFollower.setText(listItem.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUserFollower;
        TextView tvUserFollower;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserFollower = itemView.findViewById(R.id.img_users_follower);
            tvUserFollower = itemView.findViewById(R.id.tv_login_follower);

        }
    }
}
