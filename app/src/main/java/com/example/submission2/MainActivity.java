package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserModel> listItem = new ArrayList<>();
    RecyclerView rvUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.github_users));
        }

        progressBar = findViewById(R.id.prograssBar);
        rvUser = findViewById(R.id.rv_users);
        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            SearchView searchView = (SearchView) findViewById(R.id.searchView);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.username));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    showProgress(true);
                    if (s != null){
                        getDataOnline(s);
                    } else {
                        Toast.makeText(MainActivity.this, "Input a username !", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            });
        }
    }

    private void showProgress(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void getDataOnline(final String username){
        Call<ResponseUserModel> responseUserModelCall = ApiClient.getApiService().getSearchUser(username);
        responseUserModelCall.enqueue(new Callback<ResponseUserModel>() {
            @Override
            public void onResponse(Call<ResponseUserModel> call, Response<ResponseUserModel> response) {
                if (response.isSuccessful()){
                    listItem = response.body().getItems();
                    rvUser.setAdapter(new ListUserAdapter(MainActivity.this, listItem));
                    showProgress(false);
                } else {
                    Toast.makeText(MainActivity.this, "Error fetch data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error request data ! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}