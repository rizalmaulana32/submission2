package com.example.consumerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.example.consumerapp.Adapter.FavAdapter;

import static com.example.consumerapp.DataBase.DatabaseContract.UserColumn.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_item_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null){
            new FavoriteUser().execute();
        }

    }

    public class FavoriteUser extends AsyncTask<Void, Void, Cursor>{
        @RequiresApi(api = Build.VERSION_CODES.O)

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getApplicationContext().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            FavAdapter favAdapter = new FavAdapter(getApplicationContext());
            favAdapter.setCursor(cursor);
            favAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(favAdapter);
        }
    }

}