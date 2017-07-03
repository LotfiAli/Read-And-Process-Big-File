package com.lotfi.batchFile.executeTask.core.jobs;


import com.lotfi.batchFile.executeTask.core.ConfigurationSystem;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.ExecuteTask;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.readerFile.ControllerReader;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by alotfi on 2/28/2017.
 */
public interface Jobs extends BaseJob {

//    <T> void setInputExecuteJob(InputExecuteJob inputExecuteJob);
//
//    <T> void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob);
//
//    void setMapJob(MapJob mapJob);
//
//    void setReduceJob(ReduceJob reduceJob);
//
//    void setEventJob(EventJob eventJob);
//
//
//    InputExecuteJob getInputExecuteJob();
//
//    OutPutExecuteJob getOutputExecuteJob();
//
//    ConfigurationSystem getConfig();
//
//    MapJob getMapJob();
//
//    ReduceJob getReduceJob();
//
//    ExecuteTask getExecuteTask();
//
//    EventJob getEventJob();
//
//    ControllerReader getControllerReader();


//    <T> void submit(List<T> request);

//    void setConfiguration(ConfigurationSystem config);

    void setName(String name);

    String getName();

    int waitForCompletion() throws IllegalFormatException, Exception;

    ExecuteTask getExecuteTask();

    void setExecuteTask(ExecuteTask executeTask);


}
