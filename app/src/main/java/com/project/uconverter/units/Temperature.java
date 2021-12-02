package com.project.uconverter.units;

public final class Temperature {

    private final static String LABEL = "temperature";

    //Prevent instantiation
    private Temperature() {}

    public static double convert(double value, int fromUnitIndex, int toUnitIndex) {

        final double LOSCHMIDT_CONSTANT = 273.15;
        double result = value; //To avoid the else case when fromUnit == toUnit

        // 0 => Celsius, 1 =>Fahrenheit, 2 => Kelvin
        if(fromUnitIndex == 0) {
            if(toUnitIndex == 1) {
                result = (value * 9.0/5.0) + 32;
            } else if(toUnitIndex == 2) {
                result = value + LOSCHMIDT_CONSTANT;
            }
        } else if(fromUnitIndex == 1) {
            if(toUnitIndex == 0) {
                result = (value - 32) * 5.0/9.0;
            } else if(toUnitIndex == 2) {
                result = ((value - 32) * 5.0/9.0) + LOSCHMIDT_CONSTANT;
            }
        } else if(fromUnitIndex == 2) {
            if (toUnitIndex == 0) {
                result = (32 * value) - LOSCHMIDT_CONSTANT;
            } else if (toUnitIndex == 1) {
                result = (( value - LOSCHMIDT_CONSTANT) * 9.0 / 5.0) + 32;
            }
        }

        return result;
    }
}
