package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask;

import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.TaskRunner;
import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl.TaskResourceImpl;
import com.lotfi.batchFile.executeTask.core.jobs.InputExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.OutPutExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ContextImpl;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
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
public abstract class BaseExecuteTask implements ExecuteTask {

    private InputExecuteJob inputExecuteJob;
    private OutPutExecuteJob outputExecuteJob;
    private MapJob mapJob;
    private ReduceJob reduceJob;
    private EventJob eventJob;
    private ControllerReader controllerReader;
    private List<TaskRunner> tasks = new ArrayList<TaskRunner>();
    private ThreadPoolExecutor executor;
    private boolean jobIsComplete = false;
    private ContextImpl contextReduce;


    public List<Object> getResult() throws Exception {
        if (this.jobIsComplete)
            return (List<Object>) contextReduce;
        throw new IllegalAccessException("Task Is Not Complete");
    }

    private List<Object> joinResult() throws Exception {
        List<Object> collectResult = new ArrayList<Object>();
        for (int i = 0; i <= tasks.size() - 1; i++) {
            collectResult.add(tasks.get(i).getContext());
        }
        return collectResult;
    }

    private Collection getAllTaskExcutor() throws Exception {
        Collection collection = new ArrayList();
        for (TaskRunner item : tasks)
            collection.add(item);
        return collection;
    }

    public int startTask() throws Exception {
        prepareTasks();

        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(getCountCpu());
        List<Future<Object>> result = executor.invokeAll(getAllTaskExcutor());
        this.executor.shutdown();
        this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        List<Object> resultTask = joinResult();
        ReduceJob reduceJob = getReduceJob();
        contextReduce = new ContextImpl();
        reduceJob.reduce(resultTask, contextReduce);
        getOutputExecuteJob().submit(contextReduce.getRepository());
        this.jobIsComplete = true;
        return 1;

    }

    protected abstract int getCountCpu();

    protected void prepareTasks() throws Exception {
        int countCore = getCountCpu();
        for (int i = 1; i <= countCore; i++) {
            TaskRunner task = new TaskResourceImpl(getControllerReader(), getEventJob());
            task.setContext(new ContextImpl());
            task.setMapJob(getMapJob());
            tasks.add(task);
        }
    }

    public void setTaskRunner(TaskRunner taskRunner) {
        tasks.add(taskRunner);
    }

    public List<TaskRunner> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRunner> tasks) {
        this.tasks = tasks;
    }

    public ControllerReader getControllerReader() {
        return controllerReader;
    }

    public void setControllerReader(ControllerReader controllerReader) {
        this.controllerReader = controllerReader;
    }

    public InputExecuteJob getInputExecuteJob() {
        return inputExecuteJob;
    }

    public void setInputExecuteJob(InputExecuteJob inputExecuteJob) {
        this.inputExecuteJob = inputExecuteJob;
    }

    public OutPutExecuteJob getOutputExecuteJob() {
        return outputExecuteJob;
    }

    public void setOutputExecuteJob(OutPutExecuteJob outputExecuteJob) {
        this.outputExecuteJob = outputExecuteJob;
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

    public EventJob getEventJob() {
        return eventJob;
    }

    public void setEventJob(EventJob eventJob) {
        this.eventJob = eventJob;
    }


}
