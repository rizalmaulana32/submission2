package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission2.DataBase.DatabaseContract;
import com.example.submission2.DataBase.DatabaseHelper;
import com.example.submission2.DataBase.UserHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.example.submission2.Adapter.PageAdapter;
import com.example.submission2.Model.DetailUserModel;
import com.example.submission2.Model.UserModel;
import com.example.submission2.Retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.submission2.DataBase.DatabaseContract.UserColumn.TABLE_USER_NAME;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    UserModel userModel;
    TextView login, company, location, repo;
    ImageView imgPhoto;
    UserHelper userHelper;
    ArrayList<UserModel> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.detail_activity));
        }

        login = findViewById(R.id.tv_login_detail);
        company = findViewById(R.id.tv_company_detail);
        location = findViewById(R.id.tv_location_detail);
        repo = findViewById(R.id.tv_repo_detail);
        imgPhoto = findViewById(R.id.img_users_detail);
        userModel = getIntent().getParcelableExtra("datauser");
        userHelper = new UserHelper(this);
        userHelper.open();

        getData();
        onViewPager();
        setOnClickFavButton();
    }

    private void getData() {
        if (userModel != null) {
            final ProgressDialog progressDialog = new ProgressDialog(DetailUserActivity.this);
            progressDialog.setMessage(getString(R.string.progress));
            progressDialog.show();

            Glide.with(DetailUserActivity.this)
                    .load(userModel.getAvatarUrl())
                    .into(imgPhoto);
            login.setText(userModel.getLogin());

            Call<DetailUserModel> request = ApiClient.getApiService().getDetailUser(userModel.getLogin());
            request.enqueue(new Callback<DetailUserModel>() {
                @Override
                public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                    detailUserModel = response.body();

                    location.setText(detailUserModel.getLocation());
                    company.setText(detailUserModel.getCompany());
                    repo.setText(detailUserModel.getRepository());
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<DetailUserModel> call, Throwable t) {
                    Toast.makeText(DetailUserActivity.this, "Failed fetch data !", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No data !", Toast.LENGTH_SHORT).show();
        }

    }

    private void onViewPager() {
        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);
    }

    private void setOnClickFavButton() {
        MaterialFavoriteButton btnFav = findViewById(R.id.btn_favorites);

        btnFav.setFavorite(userHelper.isUserFavorited(userModel.getLogin()));
        btnFav.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if (favorite){
                    userHelper.userInsert(userModel);
                    Toast.makeText(DetailUserActivity.this, "Success added favorite !", Toast.LENGTH_SHORT).show();
                } else {
                    userHelper.userDelete(String.valueOf(userModel.getId()));
                    Toast.makeText(DetailUserActivity.this, "Success delete favorite 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}