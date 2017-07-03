package com.lotfi.batchFile.spi;

import com.lotfi.batchFile.executeTask.core.jobs.Jobs;

import java.util.Enumeration;

/**
 * Created by ali on 7/2/17.
 */
public interface JobRepository {
    Jobs getJobByName(String name);

    public Jobs getJobLogger();

    public Jobs exists(String name);

    public void shutdown();

    public Enumeration getCurrentLoggers();
}
