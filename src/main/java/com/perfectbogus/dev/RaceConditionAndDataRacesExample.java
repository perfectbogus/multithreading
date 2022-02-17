package com.perfectbogus.dev;

public class RaceConditionAndDataRacesExample {

    public static void main(String[] args) {
//        SharedClassImpl sharedClass = new SharedClassImpl();
//
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                sharedClass.increment();
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                sharedClass.checkForDataRace();
//            }
//        });
//
//        thread1.start();
//        thread2.start();

        SharedClass sharedClassVolatile = new SharedClassVolatileImpl();
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClassVolatile.increment();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClassVolatile.checkForDataRace();
            }
        });

        thread3.start();
        thread4.start();

    }

    public static class SharedClassVolatileImpl implements SharedClass {

        public volatile int x = 0;
        public volatile int y = 0;

        @Override
        public void increment() {
            x++;
            y++;
        }

        @Override
        public void checkForDataRace() {
            if (y > x) {
                System.out.println("Data Race is Detected...");
            }
        }
    }

    interface SharedClass {
        void increment();
        void checkForDataRace();
    }

    public static class SharedClassImpl implements SharedClass {
        private int x = 0;
        private int y = 0;

        public void increment(){
            x++;
            y++;
        }

        public void checkForDataRace(){
            if (y > x) {
                System.out.println("Data Race is Detected...");
            }
        }
    }
}
