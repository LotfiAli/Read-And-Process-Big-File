package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource;


import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.ExecuteTask;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl.TaskResourceImpl;
import com.lotfi.batchFile.executeTask.core.jobs.InputExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.Jobs;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ContextImpl;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.executeTask.utility.PcManager;
import com.lotfi.batchFile.readerFile.ControllerReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by alotfi on 3/3/2017.
 */
public class ManageTasks {

    private Jobs job;

    public ManageTasks(Jobs job) throws Exception {
        this.job = job;
    }

    public int startTask() throws Exception {
        return job.getExecuteTask().startTask();
    }


}
