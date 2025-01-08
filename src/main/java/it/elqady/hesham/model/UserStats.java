package it.elqady.hesham.model;

public class UserStats {
    private int min;
    private int max;
    private double average;

    public UserStats(int min, int max, double average) {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
