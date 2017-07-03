package com.lotfi.batchFile.executeTask.core.jobs.impl;

import com.lotfi.batchFile.executeTask.core.ConfigurationSystem;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.ExecuteTask;
import com.lotfi.batchFile.executeTask.core.ResourceManager.ResourceManagers;
import com.lotfi.batchFile.executeTask.core.ResourceManager.queue.impl.ResourceManagersImpl;
import com.lotfi.batchFile.executeTask.core.jobs.InputExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.Jobs;
import com.lotfi.batchFile.executeTask.core.jobs.OutPutExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by alotfi on 2/28/2017.
 */
public class JobsImpl implements Jobs {


    private String name;

    private ExecuteTask executeTask;

    public JobsImpl() {
        executeTask = new ExecuteTaskImpl();
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getName() {
        return null;
    }

    public int waitForCompletion() throws Exception {
        ResourceManagers resourceAllocated = ResourceManagersImpl.getResourceManager();
        String idJob = resourceAllocated.sendJobs(this);
        if ("".equals(idJob)) {
            throw new Exception("error error");
        }
        return resourceAllocated.submit(idJob);
    }

    public ExecuteTask getExecuteTask() {
        return executeTask;
    }

    public void setExecuteTask(ExecuteTask executeTask) {
        this.executeTask = executeTask;
    }
}
