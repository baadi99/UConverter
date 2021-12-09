package com.project.uconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;


public class HomePageFragment extends Fragment {

    // Required empty public constructor
    public HomePageFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<View> cards = new ArrayList<>();

        //Get all the cards and store them in cards list
        view.findViewsWithText(cards, "card", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);

        //Iterate through the cards and attach an OnClick event on each card
        NavController navController = NavHostFragment.findNavController(HomePageFragment.this);
        for(View card : cards) {
            card.setOnClickListener((View v) -> {
                //Passing the label to converter fragment so the corresponding units will be used in spinner
                // and the corresponding algorithm will be used
                String label = card.getContentDescription().toString().split(" ")[1];
                HomePageFragmentDirections.ActionHomePageFragmentToConverterFragment action = HomePageFragmentDirections.actionHomePageFragmentToConverterFragment(label);
                navController.navigate(action);
            });
        }

    }
}