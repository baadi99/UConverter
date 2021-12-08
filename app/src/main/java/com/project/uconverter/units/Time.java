package com.project.uconverter.units;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

<<<<<<< HEAD

public final class Time implements Converter{


    private static final Map<String, TimeUnit> units = new HashMap<>();
    private static Time instance = null;

    static {
        units.put("Day", TimeUnit.DAYS);
        units.put("Hour", TimeUnit.HOURS);
        units.put("Minute", TimeUnit.MINUTES);
        units.put("Second", TimeUnit.SECONDS);
        units.put("ms", TimeUnit.MILLISECONDS);
        units.put("μs", TimeUnit.MICROSECONDS);
        units.put("ns", TimeUnit.NANOSECONDS);
    }

    //Prevent instantiation
    private Time() { }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }


    public double convert(double value, String fromUnit, String toUnit) throws Exception {

        TimeUnit from = units.get(fromUnit);
        TimeUnit to = units.get(toUnit);

        if(from == null || to == null) {
            throw new Exception("Unit was not found!");
        }

        double result = to.convert((long)value, from);

        //Test for overflow
        if(result == Long.MAX_VALUE || result == Long.MIN_VALUE) {
=======
import java.lang.Math;

public final class Time {

    private final static String LABEL = "time";

    private final static Map<String, TimeUnit> UNITS = new HashMap<>();

    static {
        UNITS.put("Day", TimeUnit.DAYS);
        UNITS.put("Hour", TimeUnit.HOURS);
        UNITS.put("Minute", TimeUnit.MINUTES);
        UNITS.put("Second", TimeUnit.SECONDS);
        UNITS.put("ms", TimeUnit.MILLISECONDS);
        UNITS.put("μs", TimeUnit.MICROSECONDS);
        UNITS.put("ns", TimeUnit.NANOSECONDS);
    }


    //Prevent instantiation
    private Time() {}


    public static long convert(long value, String fromUnit, String toUnit) throws Exception {

        TimeUnit from = UNITS.get(fromUnit);
        TimeUnit to = UNITS.get(toUnit);

        long result = to.convert(value, from);

        //Test for overflow
        if(result == Long.MAX_VALUE || result == Long.MIN_VALUE) {
            //just for testing should be chaged later
>>>>>>> 188f275d1b8938ce0cada910e3d2a3303cc28d60
            throw new Exception("Overflow!");
        }

        return result;
    }
}
