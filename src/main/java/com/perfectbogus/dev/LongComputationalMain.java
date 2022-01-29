package com.perfectbogus.dev;

import java.math.BigInteger;

public class LongComputationalMain {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationalTask(new BigInteger("1000"), new BigInteger("1000")));

        thread.start();

        thread.interrupt();

    }

    private static class LongComputationalTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationalTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power =  power;
        }

        @Override
        public void run() {
            System.out.println("Base " + base + " Power: " + power + " = " + pow(base,power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ONE; i.compareTo(power) != 0; i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread was interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }

}
