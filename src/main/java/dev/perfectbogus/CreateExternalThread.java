package dev.perfectbogus;

public class CreateExternalThread {

    public static void main(String[] args){
        Thread thread = new ExternalClassThread();

        thread.start();
    }

    private static class ExternalClassThread extends Thread{
        @Override
        public void run(){
            System.out.println("Hello From: " + Thread.currentThread().getName());
        }
    }
}
