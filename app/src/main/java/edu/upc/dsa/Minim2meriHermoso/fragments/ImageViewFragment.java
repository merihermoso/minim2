package edu.upc.dsa.Minim2meriHermoso.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.upc.dsa.Minim2meriHermoso.MainActivity;
import edu.upc.dsa.Minim2meriHermoso.R;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageViewFragment extends Fragment {

    private View view;
    private MainActivity mainActivity;

    private ImageView ivFull;

    public ImageViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_image_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        mainActivity.setBackActivated(true);

        ivFull = view.findViewById(R.id.ivFull);

        ivFull.setImageResource(getArguments().getInt("image"));

        ivFull.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

    }

}