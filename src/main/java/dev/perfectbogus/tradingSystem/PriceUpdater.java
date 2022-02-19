package dev.perfectbogus.tradingSystem;

import java.util.Random;

public class PriceUpdater extends Thread{
    private PriceContainer priceContainer;
    private Random random = new Random();

    public PriceUpdater(PriceContainer priceContainer) {
        this.priceContainer = priceContainer;
    }

    @Override
    public void run() {
        while (true) {
            priceContainer.getLockObject().lock();
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                priceContainer.setBitcoinPrice(2000);
                priceContainer.setEtherPrice(1234);
                priceContainer.setLitecoinPrice(4321);
                priceContainer.setBitcoinCashPrice(987);
                priceContainer.setRipplePrice(2345);
            } finally {
                priceContainer.getLockObject().unlock();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
