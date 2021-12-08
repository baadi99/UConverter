package com.project.uconverter.units;

import java.util.Arrays;
import java.util.List;

public final class Temperature implements Converter {

    private static final List<String> units = Arrays.asList("Celsius", "Fahrenheit", "Kelvin");

    private static Temperature instance = null;

    // Prevent instantiation
    private Temperature() {
    }

    public static Temperature getInstance() {
        if (instance == null) {
            instance = new Temperature();
        }
        return instance;
    }

    public double convert(double value, String fromUnit, String toUnit) throws Exception {

        // we get the unit in the form Celsius
        int fromUnitIndex = units.indexOf(fromUnit);
        int toUnitIndex = units.indexOf(toUnit);

        if (fromUnitIndex == -1 || toUnitIndex == -1) {
            throw new Exception("Unit was not found");
        }

        final double LOSCHMIDT_CONSTANT = 273.15;
        double result = value; // To avoid the else case when fromUnit == toUnit

        // 0 => Celsius, 1 =>Fahrenheit, 2 => Kelvin
        if (fromUnitIndex == 0) {
            if (toUnitIndex == 1) {
                result = (value * 9.0 / 5.0) + 32;
            } else if (toUnitIndex == 2) {
                result = value + LOSCHMIDT_CONSTANT;
            }
        } else if (fromUnitIndex == 1) {
            if (toUnitIndex == 0) {
                result = (value - 32) * 5.0 / 9.0;
            } else if (toUnitIndex == 2) {
                result = ((value - 32) * 5.0 / 9.0) + LOSCHMIDT_CONSTANT;
            }
        } else if (fromUnitIndex == 2) {
            if (toUnitIndex == 0) {
                result = (32 * value) - LOSCHMIDT_CONSTANT;
            } else if (toUnitIndex == 1) {
                result = ((value - LOSCHMIDT_CONSTANT) * 9.0 / 5.0) + 32;
            }
        }

        return result;
    }
}
