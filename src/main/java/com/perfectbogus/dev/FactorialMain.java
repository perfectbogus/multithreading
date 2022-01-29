package com.perfectbogus.dev;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialMain {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 12L, 32L,1L, 10L);
        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The Calculation for " + inputNumbers.get(i) + " is Still in progress ");
            }
        }
    }

    private static class FactorialThread extends Thread {

        private final Long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private Boolean isFinished = false;

        public FactorialThread(Long inputNumber) {
            this.inputNumber = inputNumber;
        }

        public Boolean isFinished(){
            return this.isFinished;
        }

        public BigInteger getResult(){
            return this.result;
        }

        public BigInteger factorial(long n){
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }

            return tempResult;
        }

        @Override
        public void run() {
            this.result = this.factorial(inputNumber);
            this.isFinished = true;
        }

    }



}
