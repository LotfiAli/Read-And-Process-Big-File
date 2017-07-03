package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ali on 6/28/17.
 */
public class ThreadPoolExecutorBatchFileImpl extends AbstractExecutorService {

    private List<Runnable> tasks = new ArrayList<Runnable>();

    public void shutdown() {

    }

    public List<Runnable> shutdownNow() {
        return null;
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void execute(Runnable command) {

    }
}
