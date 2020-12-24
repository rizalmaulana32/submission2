package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.submission2.Adapter.FavAdapter;
import com.example.submission2.DataBase.UserHelper;
import com.example.submission2.Model.UserModel;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    UserHelper userHelper;
    ArrayList<UserModel> userModel = new ArrayList<>();
    FavAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        userHelper = new UserHelper(getApplicationContext());
        userHelper.open();
        userModel = userHelper.getDataUser();
        setRecyclerView();

    }

    private void setRecyclerView() {
        RecyclerView rvUser = findViewById(R.id.list_item_favorite);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setHasFixedSize(true);
        favAdapter = new FavAdapter(getApplicationContext());
        rvUser.setAdapter(favAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userModel = userHelper.getDataUser();
        favAdapter.setListItem(userModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userHelper.close();
    }
}