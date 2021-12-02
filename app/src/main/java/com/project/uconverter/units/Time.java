package com.project.uconverter.units;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            throw new Exception("Overflow!");
        }

        return result;
    }
}
