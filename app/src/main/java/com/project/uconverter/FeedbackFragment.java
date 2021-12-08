package com.project.uconverter;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.uconverter.units.Area;
import com.project.uconverter.units.Converter;
import com.project.uconverter.units.Length;
import com.project.uconverter.units.Mass;
import com.project.uconverter.units.Temperature;
import com.project.uconverter.units.Time;
import com.project.uconverter.units.Utils;
import com.project.uconverter.units.Volume;

public class ConverterFragment extends Fragment {

    // UI items
    private TextView resultView;
    private EditText valueView;
    private Spinner fromUnitDropDown;
    private Spinner toUnitDropDown;

    // Spinner Adapter
    private ArrayAdapter<CharSequence> dropDownAdapter;

    // A variable to store the converter instance of one of the implementations
    // of the Converter interface and use polymorphism to call the appropriate
    // implementation
    private Converter converter;

    // Required empty public constructor
    public ConverterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // label is passed from the home page fragments and indicates the label of
        // the conversion that was chosen (i.e. Length, Area, ...) and is used to
        // initialize spinners with the appropriate data and to use the appropriate
        // conversion algorithm
        String label = ConverterFragmentArgs.fromBundle(getArguments()).getLabel();

        // a variable to store the array resource (Defined in res > values > arrays.xml)
        // that will be used to initialize spinner
        @ArrayRes
        int units_resource;

        switch (label) {
            case "length":
                units_resource = R.array.length_units;
                converter = Length.getInstance();
                break;
            case "mass":
                units_resource = R.array.mass_units;
                converter = Mass.getInstance();
                break;
            case "area":
                units_resource = R.array.area_units;
                converter = Area.getInstance();
                break;
            case "volume":
                units_resource = R.array.volume_units;
                converter = Volume.getInstance();
                break;
            case "time":
                units_resource = R.array.time_units;
                converter = Time.getInstance();
                break;
            case "temperature":
                units_resource = R.array.temperature_units;
                converter = Temperature.getInstance();
                break;
            default:
                units_resource = R.array.length_units;
        }

        dropDownAdapter = ArrayAdapter.createFromResource(container.getContext(), units_resource,
                android.R.layout.simple_spinner_dropdown_item);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up from unit spinner
        fromUnitDropDown = view.findViewById(R.id.from_unit_view);
        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitDropDown.setAdapter(dropDownAdapter);
        int nmUnits = fromUnitDropDown.getCount();
        fromUnitDropDown.setSelection(nmUnits / 2); // Set default from unit to be meter

        // Set up to unit spinner
        toUnitDropDown = view.findViewById(R.id.to_unit_view);
        toUnitDropDown.setAdapter(dropDownAdapter);

        // initialize result
        resultView = view.findViewById(R.id.result);
        valueView = view.findViewById(R.id.value);

        // Set an event on the value field
        valueView.setOnKeyListener((View v, int keyCode, KeyEvent event) -> {

            // Check key pressed is in the interval 0 - 9 or it's a backspace (<- key) or a
            // dot "."
            // For decimal values.
            if ((keyCode >= 7 && keyCode <= 16) || (keyCode == 67) || (keyCode == 158)) {
                String input = valueView.getText().toString();

                if (input.length() == 0) {
                    // Reset text view when input is cleared
                    resultView.setText("0");
                } else {
                    calculateResult();
                }
                return false;
                // when the back key is pressed remove focus on value field
                // this unblocks going back action
            } else if (keyCode == 4) {
                resultView.setFocusable(false);
                return false;
            }
            return true;
        });

        // handle the switch button event
        view.findViewById(R.id.switch_units_btn).setOnClickListener((View v) -> {
            int fromUnit = fromUnitDropDown.getSelectedItemPosition();
            fromUnitDropDown.setSelection(toUnitDropDown.getSelectedItemPosition());
            toUnitDropDown.setSelection(fromUnit);
            // Update value
            calculateResult();
        });

        // Update result when a new from unit is selected
        fromUnitDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Update result when a new to unit is selected
        toUnitDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /*
     * A helper method that is responsible for calling the appropriate conversion
     * algorithm and formatting the resulting value
     */
    private void calculateResult() {
        String input = valueView.getText().toString();
        if (input.length() > 0) {
            // Get the selected items (from unit and to unit)
            String fromUnit = fromUnitDropDown.getSelectedItem().toString();
            String toUnit = toUnitDropDown.getSelectedItem().toString();

            double val = Double.parseDouble(input);

            try {

                double result = converter.convert(val, fromUnit, toUnit);
                resultView.setText(Utils.format(result));

            } catch (Exception e) {
                resultView.setText(e.getMessage());
            }
        }
    }
}