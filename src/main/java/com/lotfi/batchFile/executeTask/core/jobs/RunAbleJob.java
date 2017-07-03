package com.lotfi.batchFile.executeTask.core.jobs;

import java.util.List;

/**
 * Created by alotfi on 3/1/2017.
 */
public interface RunAbleJob {

    void run(List<Object> args) throws Exception;

    Jobs getJobs();
}
