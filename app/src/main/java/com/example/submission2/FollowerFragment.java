package com.example.submission2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        Call<FollowerModel> request = ApiClient.getApiService().getFollower(userModel.getLogin());
        request.enqueue(new Callback<FollowerModel>() {
            @Override
            public void onResponse(Call<FollowerModel> call, Response<FollowerModel> response) {
                ArrayList<FollowerModel> listFollower = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        //listFollower.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<FollowerModel> call, Throwable t) {

            }
        });
    }
}