package com.project.uconverter.units;

import java.util.Arrays;
import java.util.List;

public final class Length implements Converter {
    //Empty strings are added between the elements where the step is 3
    // instead of 1 so we don't have to deal with that case.
    private static final List<String> units = Arrays.asList("Mm", "", "", "km", "hm", "dam", "m",
                    "dm", "cm", "mm", "", "", "μm", "", "", "nm");;

    private static Length instance = null;

    //Prevent instantiation
    private Length() {}

    public static Length getInstance() {

        if (instance == null) {
            instance = new Length();
        }

        return instance;
    }

    /*
     * a static method to convert from one unit to another
     */
    public double convert(double value, String fromUnit, String toUnit) throws Exception {

        int fromIndex = units.indexOf(fromUnit);
        int toIndex = units.indexOf(toUnit);

        if (fromIndex == -1 || toIndex == -1) {
            throw new Exception("Unit was not found!");
        }

        int expo = toIndex - fromIndex; // calculates the exponent where val fromunit = val * 10 ^ +/- expo toUnit

        return value * Math.pow(10, expo);
    }
}
