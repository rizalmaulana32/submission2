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

import com.example.submission2.Adapter.FollowingAdapter;
import com.example.submission2.Adapter.ListUserAdapter;
import com.example.submission2.Retrofit.ApiClient;
import com.example.submission2.DetailUserActivity;
import com.example.submission2.Model.FollowingModel;
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
public class FollowingFragment extends Fragment {

    RecyclerView rvFollowing;
    UserModel userModel;

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailUserActivity detailUserActivity = (DetailUserActivity) getActivity();
        Bundle bundle = detailUserActivity.getIntent().getBundleExtra(ListUserAdapter.DATA_EXTRA);
        userModel = Parcels.unwrap(bundle.getParcelable(ListUserAdapter.DATA_USER));

        rvFollowing = view.findViewById(R.id.rv_Following);
        rvFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowingModel>> request = ApiClient.getApiService().getFollowing(userModel.getLogin());
        request.enqueue(new Callback<List<FollowingModel>>() {
            @Override
            public void onResponse(Call<List<FollowingModel>> call, Response<List<FollowingModel>> response) {
                ArrayList<FollowingModel> listFollowing = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response != null){
                        listFollowing.addAll(response.body());
                        rvFollowing.setAdapter(new FollowingAdapter(getContext(), listFollowing));
                    }
                } else {
                    Toast.makeText(getContext(), "Error request data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowingModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Request failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}