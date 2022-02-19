package dev.perfectbogus.tradingSystem;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class PriceContainer {

    private Lock lockObject = new ReentrantLock();

    private double bitcoinPrice;
    private double etherPrice;
    private double litecoinPrice;
    private double bitcoinCashPrice;
    private double ripplePrice;
}
