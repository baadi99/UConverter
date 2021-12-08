package com.project.uconverter.units;


import java.util.Arrays;
import java.util.List;

public final class Volume implements Converter {
    
    //Empty strings are added between the elements where the step is 6
    // instead of 4 so we don't have to deal with that case.
    private static final List<String> units = Arrays.asList("Mm", "", "", "km", "hm", "dam", "m",
            "dm", "cm", "mm", "", "", "μm", "", "", "nm");
    //the exponent is not included in the units (i.e. m^3) to simplify searching
    
    
    private static Volume instance = null;

    //Prevent instantiation
    private Volume() { }

    public static Volume getInstance() {

        if (instance == null) {
            instance = new Volume();
        }

        return instance;
    }

    /*
     * a static method to convert from one unit to another
     */
    public double convert(double value, String fromUnit, String toUnit) throws Exception {

        int fromIndex = units.indexOf(fromUnit.split("[^a-zA-Z]")[0]);
        int toIndex = units.indexOf(toUnit.split("[^a-zA-Z]")[0]);

        if (fromIndex == -1 || toIndex == -1) {
            throw new Exception("Unit was not found!");
        }

        int expo = toIndex - fromIndex; // calculates the exponent where val fromunit = val * 10 ^ +/- expo toUnit

        return value * Math.pow(10, expo * 3);
    }

}


