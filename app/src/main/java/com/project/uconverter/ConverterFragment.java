package com.project.uconverter;

import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment {

    //Get UI items
    private TextView resultView;
    private EditText valueView;
    private AutoCompleteTextView fromUnitView;
    private AutoCompleteTextView toUnitView;
    private ImageButton switchBtn;

    ArrayAdapter<CharSequence> adapter;

    private final int FONT_SIZE = 60;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConverterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConverterFragment newInstance(String param1, String param2) {
        ConverterFragment fragment = new ConverterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = ArrayAdapter.createFromResource(container.getContext(), R.array.units, android.R.layout.simple_spinner_dropdown_item);
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up from unit spinner
        fromUnitView = view.findViewById(R.id.from_unit_view);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitView.setAdapter(adapter);
        //Set up to unit spinner
        toUnitView = view.findViewById(R.id.to_unit_view);
        toUnitView.setAdapter(adapter);
        //initialize result
        resultView = view.findViewById(R.id.result);
        valueView = view.findViewById(R.id.value);

        //Set an event on the value field
        valueView.setOnKeyListener((View v, int keyCode, KeyEvent event) -> {

            //Check key pressed is in the interval 0 - 9 or it's backspace (<- key) or a dot "."
            //For decimal values.
            if((keyCode >= 7 && keyCode <= 16) || (keyCode == 67) || (keyCode == 158)) {
                String val = valueView.getText().toString();
                if(val.length() > 0) {
                    resultView.setText(val);
                } else {
                    //Reset text view
                    resultView.setText("0");
                }
                return false;
                //when the back key is pressed remove focus on value field
                //this unblocks going back
            } else if (keyCode == 4) {
                resultView.setFocusable(false);
                return false;
            }
            return true;
        });

        //handle the switch btn event
        switchBtn = view.findViewById(R.id.switch_units_btn);
        switchBtn.setOnClickListener((View v) -> {
            String fromUnit = fromUnitView.getText().toString();
            fromUnitView.setText(toUnitView.getText().toString());
            toUnitView.setText(fromUnit);
        });


    }
}