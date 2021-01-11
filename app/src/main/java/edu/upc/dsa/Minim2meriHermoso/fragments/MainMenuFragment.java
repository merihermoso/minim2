package edu.upc.dsa.Minim2meriHermoso.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.upc.dsa.Minim2meriHermoso.MainActivity;
import edu.upc.dsa.Minim2meriHermoso.MyAdapter;
import edu.upc.dsa.Minim2meriHermoso.R;
import edu.upc.dsa.Minim2meriHermoso.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainMenuFragment extends Fragment {

    EditText userName;
    private View view;
    private MainActivity mainActivity;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return view;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.btnGotoSearchMainMenu).setOnClickListener(this::btnGotoProfileClick);
        userName = view.findViewById(R.id.etUserName);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setBackActivated(false);

        if (!mainActivity.isNetworkConnected()) {
            Navigation.findNavController(view).navigate(R.id.noInternetConnectionFragment);
            return;
        }

        TextView tvHello = view.findViewById(R.id.tvHello);
        tvHello.setText(getString(R.string.hello_string) + " " + mainActivity.getSavedUsername() + "!");
    }





    public void btnGotoProfileClick(android.view.View u) {
        Bundle bundle = new Bundle();
        bundle.putString("login", userName.getText().toString());
        Navigation.findNavController(view).navigate(R.id.action_mainMenuFragment_to_profileFragment, bundle);
    }



}