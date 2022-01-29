package com.perfectbogus.dev;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll(){
        List<Thread> threadList = new ArrayList<>();

        for(Runnable task: tasks){
            Thread thread = new Thread(task);
            threadList.add(thread);
        }
        for(Thread thread: threadList){
            thread.start();
        }
    }
}
