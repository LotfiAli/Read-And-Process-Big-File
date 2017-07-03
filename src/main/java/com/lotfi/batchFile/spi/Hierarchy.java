package com.lotfi.batchFile.spi;

import com.lotfi.batchFile.executeTask.core.jobs.Jobs;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by ali on 7/2/17.
 */
public class Hierarchy implements JobRepository {

    private Hashtable repository;

//    private String name;


    public Hierarchy() {
        this.repository = new Hashtable();
    }

    public Jobs getJobByName(String name) {
        return (Jobs) repository.get(name);
    }

    public Jobs getJobLogger() {
        return null;
    }

    public Jobs exists(String name) {
        return null;
    }

    public void shutdown() {

    }

    public Enumeration getCurrentLoggers() {
        return null;
    }

    public void addRepository(String repName, Jobs job) {

        this.repository.put(repName, job);
    }


}
