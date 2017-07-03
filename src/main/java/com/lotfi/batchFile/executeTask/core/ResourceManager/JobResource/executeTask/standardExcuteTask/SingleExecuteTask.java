package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.standardExcuteTask;

import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.TaskRunner;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.BaseExecuteTask;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl.TaskResourceImpl;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ContextImpl;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.executeTask.utility.PcManager;
import com.lotfi.batchFile.readerFile.ControllerReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ali on 6/28/17.
 */
public class SingleExecuteTask extends BaseExecuteTask {
    protected int getCountCpu() {
        return 1;
    }
}
