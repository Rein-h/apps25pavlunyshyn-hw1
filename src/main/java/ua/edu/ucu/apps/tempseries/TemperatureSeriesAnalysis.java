package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double MIN_TEMPERATURE = -273.0;
    private double[] temperatures;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[0];
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        validateTemperatures(temperatureSeries);
        
        this.temperatures = Arrays.copyOf(temperatureSeries,
        temperatureSeries.length);
        this.size = temperatureSeries.length;
    }

    private void validateTemperatures(double[] temps) {
        for (double temp : temps) {
            if (temp < MIN_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    }

    public double average() {
        checkEmpty();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += temperatures[i];
        }
        return sum / size;
    }

    public double deviation() {
        checkEmpty();
        double avg = this.average();
        double squaredSum = 0;
        for (int i = 0; i < size; i++) {
            squaredSum += (temperatures[i] - avg) * (temperatures[i] - avg);
        }
        return Math.sqrt(squaredSum / size);
    }

    public double min() {
        checkEmpty();
        double min = temperatures[0];
        for (int i = 1; i < size; i++) {
            if (temperatures[i] < min) {
                min = temperatures[i];
            }
        }
        return min;
    }

    public double max() {
        checkEmpty();
        double max = temperatures[0];
        for (int i = 1; i < size; i++) {
            if (temperatures[i] > max) {
                max = temperatures[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return this.findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkEmpty();
        double closest = temperatures[0];
        double difference = Math.abs(temperatures[0] - tempValue);

        for (int i = 1; i < size; i++) {
            double current = Math.abs(temperatures[i] - tempValue);

            if (current < difference) {
                closest = temperatures[i];
                difference = current;
            }
            else if (current == difference) {
                closest = Math.max(closest, temperatures[i]);
            }
        }
        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] < tempValue) {
                count++;
            }
        }

        double[] result = new double[count];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] < tempValue) {
                result[index++] = temperatures[i];
            }
        }
        return result;
    }

    public double[] findTempsGreaterThan(double tempValue) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] >= tempValue) {
                count++;
            }
        }

        double[] result = new double[count];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] >= tempValue) {
                result[index++] = temperatures[i];
            }
        }
        return result;
    }

    public double[] findTempsInRange(double lowerB, double upperB) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] >= lowerB && temperatures[i] <= upperB) {
                count++;
            }
        }

        double[] result = new double[count];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (temperatures[i] >= lowerB && temperatures[i] <= upperB) {
                result[index++] = temperatures[i];
            }
        }
        return result;
    }

    public void reset() {
        this.temperatures = new double[0];
        this.size = 0;
    }

    public double[] sortTemps() {
        double[] sorted = Arrays.copyOf(temperatures, size);
        Arrays.sort(sorted);
        return sorted;
    }

    public TempSummaryStatistics summaryStatistics() {
        checkEmpty();
        return new TempSummaryStatistics(
            this.average(),
            this.deviation(),
            this.min(),
            this.max()
        );
    }

    public int addTemps(double... temps) {
        if (temps == null || temps.length == 0) {
            return size;
        }

        validateTemperatures(temps);

        while (size + temps.length > temperatures.length) {
            int newCapacity = 0;

            if (temperatures.length == 0) {
                newCapacity = 1;
            }
            else {
                newCapacity = temperatures.length * 2;
            }
            temperatures = Arrays.copyOf(temperatures, newCapacity);
        }

        for (double temp : temps) {
            temperatures[size++] = temp;
        }

        return size;
    }
}