package com.project.uconverter.units;

import java.util.Arrays;
import java.util.List;

public class BaseUnit {

    //Empty strings are added between the elements where the step is 3 instead of 1 so we don't have to deal with that case.
    protected final static List<String> UNITS = Arrays.asList("Ym", "", "",
            "Zm", "", "", "Em", "", "", "Pm", "", "", "Tm", "", "", "Gm", "", "", "Mm", "", "", "km", "hm", "dam", "m",
            "dm", "cm", "mm", "", "", "μm", "", "", "nm", "", "", "pm", "", "", "fm", "", "", "am", "", "", "zm", "",
            "", "ym");

    protected BaseUnit() {}

    /*
     * a static method to convert from one unit to another
     */

    public static double convert(double value, String fromUnit, String toUnit) throws Exception {

        int fromIndex = UNITS.indexOf(fromUnit);
        int toIndex = UNITS.indexOf(toUnit);

        if (fromIndex == -1 || toIndex == -1) {
            throw new Exception("Unit was not found!");
        }

        int expo = toIndex - fromIndex; // calculates the exponent where val fromunit = val * 10 ^ +/- expo toUnit

        return value * Math.pow(10, expo);
    }

    /**
     * A static method to format the result value in the form value * 10 ^ +/- expo
     * @param value
     * @return formated value
     */

    public static String format(double value) {

        String val = value + "";  //Convert value to a string for easier manipulation
        String[] parts = val.split("E");

        return parts.length == 2 ? parts[0] + "x10" + superScriptedExpo(parts[1]) : val;
    }

    /**
     * a helper method to format the exponent to superscript format
     * @param expo
     * @return superscripted exponent
     */

    private static String superScriptedExpo(String expo) {

        expo = expo.replaceAll("-", "\u207B");
        expo = expo.replaceAll("0", "\u2070");
        expo = expo.replaceAll("1", "\u00B9");
        expo = expo.replaceAll("2", "\u00B2");
        expo = expo.replaceAll("3", "\u00B3");
        expo = expo.replaceAll("4", "\u2074");
        expo = expo.replaceAll("5", "\u2075");
        expo = expo.replaceAll("6", "\u2076");
        expo = expo.replaceAll("7", "\u2077");
        expo = expo.replaceAll("8", "\u2078");
        expo = expo.replaceAll("9", "\u2079");

        return expo;
    }

}
