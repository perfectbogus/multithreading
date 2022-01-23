package com.perfectbogus.dev;

public class Main {

    public static void main(String[] args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread " + Thread.currentThread().getName());
            }
        });

        System.out.println("We are in thread: " + Thread.currentThread().getName());
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName());

    }
}
