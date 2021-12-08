package com.project.uconverter.units;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class Time implements Converter {

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

    // Prevent instantiation
    private Time() {
    }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    public double convert(double value, String fromUnit, String toUnit) throws Exception {

        TimeUnit from = units.get(fromUnit);
        TimeUnit to = units.get(toUnit);

        if (from == null || to == null) {
            throw new Exception("Unit was not found!");
        }

        double result = to.convert((long) value, from);

        // Test for overflow
        if (result == Long.MAX_VALUE || result == Long.MIN_VALUE) {
            throw new Exception("Overflow!");
        }

        return result;
    }
}
