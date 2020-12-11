package com.example.submission2.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission2.Adapter.FollowerAdapter;
import com.example.submission2.Adapter.ListUserAdapter;
import com.example.submission2.Retrofit.ApiClient;
import com.example.submission2.DetailUserActivity;
import com.example.submission2.Model.FollowerModel;
import com.example.submission2.R;
import com.example.submission2.Model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowerFragment extends Fragment {

    RecyclerView rvFollower;
    UserModel userModel;

    public FollowerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailUserActivity detailUserActivity = (DetailUserActivity) getActivity();
        Bundle bundle = detailUserActivity.getIntent().getBundleExtra(ListUserAdapter.DATA_EXTRA);
        userModel = Parcels.unwrap(bundle.getParcelable(ListUserAdapter.DATA_USER));

        rvFollower = view.findViewById(R.id.rv_follower);
        rvFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowerModel>> request = ApiClient.getApiService().getFollower(userModel.getLogin());
        request.enqueue(new Callback<List<FollowerModel>>() {
            @Override
            public void onResponse(Call<List<FollowerModel>> call, Response<List<FollowerModel>> response) {
                ArrayList<FollowerModel> listFollower = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listFollower.addAll(response.body());
                        rvFollower.setAdapter(new FollowerAdapter(getContext(), listFollower));
                    }
                } else {
                    Toast.makeText(getContext(), "Error loading data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowerModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}