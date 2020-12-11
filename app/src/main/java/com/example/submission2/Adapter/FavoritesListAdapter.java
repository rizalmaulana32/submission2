package com.example.submission2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission2.R;
import com.example.submission2.Model.UserModel;

import java.util.ArrayList;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.FavoritesListViewHolder> {

    ArrayList<UserModel> listItem;
    Context context;

    public FavoritesListAdapter(ArrayList<UserModel> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoritesListAdapter.FavoritesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite, parent, false);
        return new FavoritesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesListAdapter.FavoritesListViewHolder holder, int position) {

        holder.tvName.setText(listItem.get(position).getLogin());
        holder.tvUsername.setText(listItem.get(position).getLogin());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class FavoritesListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvUsername;

        public FavoritesListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_fav_name);
            tvUsername = itemView.findViewById(R.id.tv_fav_user_name);
        }
    }
}
