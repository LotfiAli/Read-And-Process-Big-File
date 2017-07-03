package com.lotfi.batchFile.executeTask.core.jobs.impl;

import com.lotfi.batchFile.executeTask.core.ConfigurationSystem;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.ExecuteTask;
import com.lotfi.batchFile.executeTask.core.jobs.InputExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.OutPutExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.readerFile.ControllerReader;

/**
 * Created by ali on 7/3/17.
 */
public class ExecuteTaskImpl implements ExecuteTask {

    private InputExecuteJob inputExecuteJob;
    private OutPutExecuteJob outputExecuteJob;
    private ConfigurationSystem config;
    private MapJob mapJob;
    private ReduceJob reduceJob;
    private EventJob eventJob;
    private ControllerReader controllerReader;

    public int startTask() throws Exception {
        return 0;
    }

//    public <T> void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob) {
//
//    }

    public InputExecuteJob getInputExecuteJob() {
        return inputExecuteJob;
    }

    public void setInputExecuteJob(InputExecuteJob inputExecuteJob) {
        this.inputExecuteJob = inputExecuteJob;
    }

    public OutPutExecuteJob getOutputExecuteJob() {
        return outputExecuteJob;
    }

//    public void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob) {
//        this.outputExecuteJob = outputExecuteJob;
//    }

    public ConfigurationSystem getConfig() {
        return config;
    }

    public void setConfig(ConfigurationSystem config) {
        this.config = config;
    }

    public MapJob getMapJob() {
        return mapJob;
    }

    public void setMapJob(MapJob mapJob) {
        this.mapJob = mapJob;
    }

    public ReduceJob getReduceJob() {
        return reduceJob;
    }

    public void setReduceJob(ReduceJob reduceJob) {
        this.reduceJob = reduceJob;
    }

    public void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob) {
        this.outputExecuteJob = outputExecuteJob;
    }

    public EventJob getEventJob() {
        return eventJob;
    }

    public void setEventJob(EventJob eventJob) {
        this.eventJob = eventJob;
    }

    public ControllerReader getControllerReader() {
        return controllerReader;
    }

    public void setControllerReader(ControllerReader controllerReader) {
        this.controllerReader = controllerReader;
    }
}
