package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl;


import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.TaskRunner;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.Context;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.readerFile.ControllerReader;

import java.util.List;

/**
 * Created by alotfi on 2/28/2017.
 */
public class TaskResourceImpl implements TaskRunner {
    //    private int countItem;
    private MapJob mapJob;
    private Context context;
    private ControllerReader controllerReader;
    private EventJob eventJob;

    public TaskResourceImpl(ControllerReader controllerReader, EventJob eventJob) {
        this.controllerReader = controllerReader;
        this.eventJob=eventJob;
//        this.countItem = countItem;
//        this.items = items;
//        this.context = context;
    }

    public void setMapJob(MapJob mapJob) {
        this.mapJob = mapJob;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Object call() throws Exception {
//        System.out.print("I get Mapp");
        while (this.controllerReader.size() > 0) {
            for (Object item : this.controllerReader.readData())
                mapJob.map(item, context);
            this.eventJob.toString();
        }
        return true;
    }

//    public int getCountItem() {
//        return countItem;
//    }
//
//    public void setCountItem(int countItem) {
//        this.countItem = countItem;
//    }

//    public void addToItem(Object... item) {
//        items.add(item);
//    }

//    public void setItems(List item) {
//        this.items = item;
//    }


}
