package com.project.uconverter;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.uconverter.units.Length;
import com.project.uconverter.units.Temperature;


public class ConverterFragment extends Fragment {

    @ArrayRes
    private int units_resource;

    //Unit resources (Defined in res > values > arrays.xml
    @ArrayRes
    private final int length_units = R.array.length_units;
    @ArrayRes
    private final int area_units = R.array.area_units;
    @ArrayRes
    private final int mass_units = R.array.mass_units;
    @ArrayRes
    private final int volume_units = R.array.volume_units;
    @ArrayRes
    private final int time_units = R.array.time_units;
    @ArrayRes
    private final int speed_units = R.array.speed_units;
    @ArrayRes
    private final int temperature_units = R.array.temperature_units;
    @ArrayRes
    private final int currency_units = R.array.currency_units;


    //UI items
    private TextView resultView;
    private EditText valueView;
    private Spinner fromUnitDropDown;
    private Spinner toUnitDropDown;
    private ImageButton switchBtn;

    private ArrayAdapter<CharSequence> dropDownAdapter;

    // Required empty public constructor
    public ConverterFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Argument passed
        String label = ConverterFragmentArgs.fromBundle(getArguments()).getLabel();
        switch (label) {
            case "length":
                units_resource = length_units;
                break;
            case "mass":
                units_resource = mass_units;
                break;
            case "area":
                units_resource = area_units;
                break;
            case "volume":
                units_resource = volume_units;
                break;
            case "time":
                units_resource = time_units;
                break;
            case "speed":
                units_resource = speed_units;
                break;
            case "temperature":
                units_resource = temperature_units;
                break;
            case "currency":
                units_resource = currency_units;
                break;
        }

        dropDownAdapter = ArrayAdapter.createFromResource(container.getContext(), units_resource, android.R.layout.simple_spinner_dropdown_item);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up from unit spinner
        fromUnitDropDown = view.findViewById(R.id.from_unit_view);
        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitDropDown.setAdapter(dropDownAdapter);
        int nmUnits = fromUnitDropDown.getCount();
        fromUnitDropDown.setSelection(nmUnits/2); //Set default from unit to be meter

        //Set up to unit spinner
        toUnitDropDown = view.findViewById(R.id.to_unit_view);
        toUnitDropDown.setAdapter(dropDownAdapter);
        //toUnitDropDown.setSelection((nmUnits/2) + 3); //Set default to unit to be milimeter


        //initialize result
        resultView = view.findViewById(R.id.result);
        valueView = view.findViewById(R.id.value);

        //Set an event on the value field
        valueView.setOnKeyListener((View v, int keyCode, KeyEvent event) -> {

            //Check key pressed is in the interval 0 - 9 or it's a backspace (<- key) or a dot "."
            //For decimal values.
            if((keyCode >= 7 && keyCode <= 16) || (keyCode == 67) || (keyCode == 158)) {
                String input = valueView.getText().toString();
                String output;
                String fromUnit = fromUnitDropDown.getSelectedItem().toString();
                String toUnit = toUnitDropDown.getSelectedItem().toString();

                if(input.length() > 0) {
                    double val = Double.parseDouble(input);
                    double res;
                    try {
                        res = Temperature.convert(val, fromUnitDropDown.getSelectedItemPosition(), toUnitDropDown.getSelectedItemPosition());
                        //output = Length.format(res);
                        resultView.setText("" + res);
                    } catch(Exception e) {
                        resultView.setText(e.getMessage());
                    }
                } else {
                    //Reset text view when input is cleared
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
            int fromUnit = fromUnitDropDown.getSelectedItemPosition();
            fromUnitDropDown.setSelection(toUnitDropDown.getSelectedItemPosition());
            toUnitDropDown.setSelection(fromUnit);
            //Update value
        });


    }
}