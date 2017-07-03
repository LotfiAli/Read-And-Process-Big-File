package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask;

import com.lotfi.batchFile.executeTask.core.ConfigurationSystem;
import com.lotfi.batchFile.executeTask.core.jobs.InputExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.OutPutExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.readerFile.ControllerReader;

import java.util.List;

/**
 * Created by ali on 6/28/17.
 */
public interface ExecuteTask {
    //    void prepareTasks() throws Exception;
    int startTask() throws Exception;

//    <T> void setInputExecuteJob(InputExecuteJob inputExecuteJob);

    <T> void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob);

    void setMapJob(MapJob mapJob);

    void setReduceJob(ReduceJob reduceJob);

    void setEventJob(EventJob eventJob);

    void setControllerReader(ControllerReader controllerReader);



    InputExecuteJob getInputExecuteJob();

    OutPutExecuteJob getOutputExecuteJob();

//    ConfigurationSystem getConfig();

    MapJob getMapJob();

    ReduceJob getReduceJob();

//    ExecuteTask getExecuteTask();

    EventJob getEventJob();

    ControllerReader getControllerReader();
}
