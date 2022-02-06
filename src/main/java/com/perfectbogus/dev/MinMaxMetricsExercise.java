package com.perfectbogus.dev;

public class MinMaxMetricsExercise {

    public static void main(String[] args) {

    }

    public static class Metrics {
        private volatile long min;
        private volatile long max;

        public Metrics() {
            this.min = Long.MAX_VALUE;
            this.max = Long.MIN_VALUE;
        }

        public void addSample(long newSample) {
            synchronized (this) {
                this.min = Math.min(min, newSample);
                this.max = Math.max(max, newSample);
            }
        }

        public long getMin() {
            return min;
        }

        public long getMax() {
            return max;
        }
    }
}
