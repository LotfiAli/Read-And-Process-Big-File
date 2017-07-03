package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl;

import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.ApplicationManager;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.ManageTasks;
import com.lotfi.batchFile.executeTask.core.jobs.Jobs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alotfi on 2/28/2017.
 */
public class ApplicationManagersImpl implements ApplicationManager {

    private Jobs job;
    private ManageTasks manageTasks;

    public ApplicationManagersImpl(Jobs job) throws Exception {
        this.job = job;
        this.manageTasks=new ManageTasks(job);
    }


    public Object call() throws Exception {

        return manageTasks.startTask();
    }


}
