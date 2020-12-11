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
import com.example.submission2.Model.FollowingModel;
import com.example.submission2.R;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<FollowingModel> listItem;

    public FollowingAdapter(Context context, ArrayList<FollowingModel> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public FollowingAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_following, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAdapter.ListViewHolder holder, int position) {
        Glide.with(context)
                .load(listItem.get(position).getAvatarUrl())
                .into(holder.imgUserFollowing);
        holder.tvUserFollowing.setText(listItem.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUserFollowing;
        TextView tvUserFollowing;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserFollowing = itemView.findViewById(R.id.img_users_following);
            tvUserFollowing = itemView.findViewById(R.id.tv_login_following);

        }
    }
}
