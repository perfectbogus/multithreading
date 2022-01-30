package com.perfectbogus.dev;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ComplexCalculationExample {

    public static void main(String[] args) throws InterruptedException {
        BigInteger base1 = new BigInteger("2");
        BigInteger base2 = new BigInteger("2");
        BigInteger power1 = new BigInteger("5");
        BigInteger power2 = new BigInteger("5");
        System.out.println(calculateResult(base1, power1, base2, power2));
    }

    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result = BigInteger.ZERO;

        List<PowerCalculatingThread> threads = new ArrayList<>();

        threads.add(new PowerCalculatingThread(base1, power1));
        threads.add(new PowerCalculatingThread(base2, power2));

        for (PowerCalculatingThread thread : threads) {
            thread.start();
        }

        for (PowerCalculatingThread thread : threads) {
            thread.join();
        }

        for (PowerCalculatingThread thread : threads) {
            System.out.println("Get Result From Thread: " + thread.getResult());
            result = result.add(thread.getResult());
        }

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ZERO;
        private final BigInteger base;
        private final BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            BigInteger tmpResult = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                System.out.println("For Result:" + tmpResult + " From Thread: " + currentThread().getName());
                tmpResult = tmpResult.multiply(base);
            }

            this.result = tmpResult;
        }

        public BigInteger getResult() {
            return this.result;
        }
    }

}
