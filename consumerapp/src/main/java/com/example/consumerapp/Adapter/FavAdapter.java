package com.example.consumerapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.consumerapp.Model.UserModel;
import com.example.consumerapp.R;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.NoteViewHolder> {

    private Context context;
    private Cursor cursor;

    public FavAdapter(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    private UserModel getItemData(int position){
        if (!cursor.moveToPosition(position)){
            throw new IllegalStateException("INVALID");
        }
        return new UserModel(cursor);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        UserModel listItem = getItemData(position);
        Glide.with(holder.itemView.getContext())
                .load(listItem.getAvatarUrl())
                .into(holder.imgFavUser);
        holder.tvUserName.setText(listItem.getLogin());
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        ImageView imgFavUser;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_fav_user_name);
            imgFavUser = itemView.findViewById(R.id.img_user_favorite);
        }
    }
}
