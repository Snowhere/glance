package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * executor
 *
 * @author STH
 * @create 2017-06-19
 **/
public class BackgroundWorker {
    private ExecutorService backgroundThread = Executors.newFixedThreadPool(5);

    public void addJob(Runnable job) {
        backgroundThread.execute(job);
    }

}
