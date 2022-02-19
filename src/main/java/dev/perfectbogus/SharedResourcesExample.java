package dev.perfectbogus;

public class SharedResourcesExample {

    public static void main(String[] args) throws InterruptedException {

        // No Sync Section
        InventoryCounter inventoryCounter = new InventoryCounterImpl();
        IncrementThread incrementThread = new IncrementThread(inventoryCounter);
        DecrementThread decrementThread = new DecrementThread(inventoryCounter);

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        //Notes: We have unexpected behavior because the critical section is
        // under the control of the OS Scheduler and the increment and decrement
        // is made in three steps (is not atomic operation)  that are interrupted between the threads
        System.out.println("No Sync Solution: " + inventoryCounter.getItem());

        // Sync Method Level Section

        InventoryCounter syncInventory = new InventoryCounterSynchronizedImpl();
        IncrementThread incrementThread1 = new IncrementThread(syncInventory);
        DecrementThread decrementThread1 = new DecrementThread(syncInventory);

        incrementThread1.start();
        decrementThread1.start();

        incrementThread1.join();
        decrementThread1.join();

        System.out.println("Sync Method Level: " + syncInventory.getItem());

        // Block Sync Section
        InventoryCounter blockSyncInventory = new InventoryCounterBlockSyncImpl();
        IncrementThread incrementThread2 = new IncrementThread(blockSyncInventory);
        DecrementThread decrementThread2 = new DecrementThread(blockSyncInventory);
        incrementThread2.start();
        decrementThread2.start();
        incrementThread2.join();
        decrementThread2.join();
        System.out.println("Block Sync Level: " + blockSyncInventory.getItem());

    }

    public static class DecrementThread extends Thread {
        private InventoryCounter store;

        public DecrementThread(InventoryCounter store){
            this.store = store;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                store.decrement();
            }
        }
    }

    public static class IncrementThread extends Thread {
        private InventoryCounter store;

        public IncrementThread(InventoryCounter store){
            this.store = store;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                store.increment();
            }
        }
    }

    public static class InventoryCounterBlockSyncImpl implements InventoryCounter {
        private int item = 0;
        final Object lock = new Object();

        @Override
        public int getItem() {
            return item;
        }

        @Override
        public void increment() {
            synchronized (this.lock){
                item++;
            }
        }

        @Override
        public void decrement() {
            synchronized (this.lock) {
                item--;
            }
        }
    }

    public static class InventoryCounterSynchronizedImpl implements InventoryCounter {
        private int item = 0;

        public synchronized int getItem(){
            return item;
        }
        public synchronized void increment(){
            item++;
        }
        public synchronized void decrement(){
            item--;
        }
    }

    public static class InventoryCounterImpl implements InventoryCounter {

        private int item = 0;

        @Override
        public int getItem(){
            return item;
        }

        @Override
        public void increment(){
            item++;
        }

        @Override
        public void decrement(){
            item--;
        }
    }

    public interface InventoryCounter {
        int getItem();
        void increment();
        void decrement();
    }

}
