package com.project.uconverter.units;


import java.util.Arrays;
import java.util.List;

public final class Mass implements Converter {

    private static final List<String> units = Arrays.asList("tonne", "", "", "kg", "hg", "dag", "g", "dg", "cg", "mg", "", "", "μg");

    private static Mass instance = null;

    //Prevent instantiation
    private Mass() { }

    public static Mass getInstance() {

        if (instance == null) {
            instance = new Mass();
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

