package com.perfectbogus.dev;

import java.util.Random;

public class VolatileExample {

    public static void main(String[] args) throws InterruptedException {
        Metrics metrics = new Metrics();

        BusinessLogic thread1 = new BusinessLogic(metrics);
        BusinessLogic thread2 = new BusinessLogic(metrics);

        MetricPrinter metricPrinter = new MetricPrinter(metrics);


        thread1.start();
        thread2.start();
        metricPrinter.start();

    }

    public static class MetricPrinter extends Thread {
        private Metrics metrics;

        public MetricPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double currentAverage = metrics.getAverage();
                System.out.println("Current Average is " + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }

        }
    }

    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
