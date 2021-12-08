package com.project.uconverter.units;

public final class Utils {

    // Prevent instantiation
    private Utils() {}

    /**
     * A static method to format the result value in the form value * 10 ^ +/- expo
     * @param value maybe in the format xEn (e.g. 6.00E4)
     * @return formatted value
     */
    public static String format(double value) {

        String val = value + "";  //Convert value to a string for easier manipulation
        String[] parts = val.split("E");

        return parts.length == 2 ? parts[0] + "x10" + superScriptedExpo(parts[1]) : val;
    }

    /**
     * a helper method to format the exponent to superscript format (e.g. 10³)
     * @param expo
     * @return superscripted exponent
     */
    private static String superScriptedExpo(String expo) {

        return (
                expo.replaceAll("-", "\u207B")
                .replaceAll("0", "\u2070")
                .replaceAll("1", "\u00B9")
                .replaceAll("2", "\u00B2")
                .replaceAll("3", "\u00B3")
                .replaceAll("4", "\u2074")
                .replaceAll("5", "\u2075")
                .replaceAll("6", "\u2076")
                .replaceAll("7", "\u2077")
                .replaceAll("8", "\u2078")
                .replaceAll("9", "\u2079")
        );
    }
}
