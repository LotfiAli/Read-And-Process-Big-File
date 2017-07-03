package com.lotfi.batchFile.executeTask.core.ResourceManager.queue;

import com.lotfi.batchFile.executeTask.core.jobs.Jobs;

/**
 * Created by alotfi on 2/28/2017.
 */
public interface QueueJobs {

    Jobs getJobs();

    void AddJobs(Jobs job);


}
