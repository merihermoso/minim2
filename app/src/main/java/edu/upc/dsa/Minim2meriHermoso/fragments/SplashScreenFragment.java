package edu.upc.dsa.Minim2meriHermoso.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import edu.upc.dsa.Minim2meriHermoso.MainActivity;
import edu.upc.dsa.Minim2meriHermoso.R;
import edu.upc.dsa.Minim2meriHermoso.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenFragment extends Fragment {

    private View view;
    private MainActivity mainActivity;

    public SplashScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setBackActivated(false);
    //    TimerTask mTimerTask = new TimerTask() {
     //       @Override
     //       public void run() {

                if (!mainActivity.isNetworkConnected()) {
                    Navigation.findNavController(view).navigate(R.id.noInternetConnectionFragment);

                }
                else{
                    Navigation.findNavController(view).navigate(R.id.mainMenuFragment);
                }

            }
        };
    //    Timer mTimer = new Timer();
    //    mTimer.schedule(mTimerTask, 3000);
 //   }

//}

