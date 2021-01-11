package edu.upc.dsa.Minim2meriHermoso.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.Minim2meriHermoso.MainActivity;
import edu.upc.dsa.Minim2meriHermoso.MyAdapter;
import edu.upc.dsa.Minim2meriHermoso.R;
import edu.upc.dsa.Minim2meriHermoso.models.ReposResponse;
import edu.upc.dsa.Minim2meriHermoso.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    TextView tvProfileFollowing;
    TextView tvProfileFollowers;
    TextView tvProfileRepositories;

    private View view;
    private MainActivity mainActivity;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setBackActivated(true);

        if (!mainActivity.isNetworkConnected()) {
            Navigation.findNavController(view).navigate(R.id.noInternetConnectionFragment);
            return;
        }
        tvProfileFollowers=view.findViewById(R.id.tvProfileFollowers);
        tvProfileFollowing=view.findViewById(R.id.tvProfileFollowing);
        tvProfileRepositories=view.findViewById(R.id.tvProfileRepositories);
        UserRequest();

    }


public void UserRequest() {
        mainActivity.setLoadingData(true);

        Call<UserResponse> resp = mainActivity.getUserService().getUser(getArguments().getString("login"));
        resp.enqueue(new Callback<UserResponse>() {

            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mainActivity.setLoadingData(false);
                switch (response.code()) {

                    case 200:
                        UserResponse userResponse = response.body();
                        tvProfileFollowers.setText("Followers: "+String.valueOf(userResponse.getFollowers()));
                        tvProfileFollowing.setText("Following: "+String.valueOf(userResponse.getFollowing()));
                        tvProfileRepositories.setText(String.valueOf(userResponse.getLogin()));

                        getFollowers();
                        break;

                    case 404:
                        Toast.makeText(getContext(), "Usuario no encontrado :(", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Navigation.findNavController(view).navigate(R.id.connectionErrorFragment);
                        break;

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                mainActivity.setLoadingData(false);
                Navigation.findNavController(view).navigate(R.id.connectionErrorFragment);
            }
        });

    }
    public void getFollowers() {
        String username = getArguments().getString("login");
        mainActivity.setLoadingData(true);
        Call<List<ReposResponse>> resp = mainActivity.getUserService().getUserRepo(username);
        resp.enqueue(new Callback<List<ReposResponse>>() {

            @Override
            public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                mainActivity.setLoadingData(false);
                switch (response.code()) {
                    case 200:
                        List<ReposResponse> reposResponses = response.body();
                        RecyclerView recyclerView;

                        recyclerView = view.findViewById(R.id.recyclerView);
                        recyclerView.setHasFixedSize(true);

                        RecyclerView.LayoutManager layoutManager;
                        layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);

                        // define an adapter
                        RecyclerView.Adapter mAdapter;
                        mAdapter = new MyAdapter(reposResponses);
                        recyclerView.setAdapter(mAdapter);
                        break;

                    default:
                        Navigation.findNavController(view).navigate(R.id.connectionErrorFragment);
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<ReposResponse>> call, Throwable t) {
                mainActivity.setLoadingData(false);
                Navigation.findNavController(view).navigate(R.id.connectionErrorFragment);

            }

        });


/*
    public void btnBackProfileClick(android.view.View u) {
        mainActivity.goBack();

    }
*/
    }
}