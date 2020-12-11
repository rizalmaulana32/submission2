package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission2.Adapter.ListUserAdapter;
import com.example.submission2.Adapter.PageAdapter;
import com.example.submission2.Model.DetailUserModel;
import com.example.submission2.Model.UserModel;
import com.example.submission2.Retrofit.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    UserModel userModel;
    TextView login, company, location, repo;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.detail_activity));
        }

        Bundle bundle = getIntent().getBundleExtra(ListUserAdapter.DATA_EXTRA);
        userModel = Parcels.unwrap(bundle.getParcelable(ListUserAdapter.DATA_USER));

        login = findViewById(R.id.tv_login_detail);
        company = findViewById(R.id.tv_company_detail);
        location = findViewById(R.id.tv_location_detail);
        repo = findViewById(R.id.tv_repo_detail);
        imgPhoto = findViewById(R.id.img_users_detail);

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

            }
        });

        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);

        FloatingActionButton btnFavorite = findViewById(R.id.btn_favorites);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailUserActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

    }
}