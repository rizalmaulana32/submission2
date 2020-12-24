package com.example.submission2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.DetailUserActivity;
import com.example.submission2.Model.UserModel;
import com.example.submission2.R;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<UserModel> listItem = new ArrayList<>();

    public FavAdapter(Context context) {
        this.context = context;
    }

    public void setListItem(ArrayList<UserModel> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public FavAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.NoteViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listItem.get(position).getAvatarUrl())
                .into(holder.imgFavUser);
        holder.tvName.setText(listItem.get(position).getName());
        holder.tvUserName.setText(listItem.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvUserName;
        ImageView imgFavUser;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_fav_name);
            tvUserName = itemView.findViewById(R.id.tv_fav_user_name);
            imgFavUser = itemView.findViewById(R.id.img_user_favorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            UserModel userModel = listItem.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailUserActivity.class);
            intent.putExtra("datauser", userModel);
            view.getContext().startActivity(intent);
        }
    }
}
