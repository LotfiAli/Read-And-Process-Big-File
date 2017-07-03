package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource;

import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.Context;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by alotfi on 2/28/2017.
 */
public interface TaskRunner extends Callable {

    void setMapJob(MapJob mapJob);

    Context getContext();

    void setContext(Context context);

//    int getCountItem();

//    void setCountItem(int countItem);

//    void addToItem(Object... item);
//
//    void setItems(List item);

}
