package com.project.uconverter.units;

public interface Converter {

    double convert(double value, String fromUnit, String toUnit) throws Exception;

}
