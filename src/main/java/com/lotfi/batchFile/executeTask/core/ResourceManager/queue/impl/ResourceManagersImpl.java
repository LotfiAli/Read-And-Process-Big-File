package com.lotfi.batchFile.executeTask.core.ResourceManager.queue.impl;

import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.impl.ApplicationManagersImpl;
import com.lotfi.batchFile.executeTask.core.ResourceManager.ResourceManagers;
import com.lotfi.batchFile.executeTask.core.jobs.Jobs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alotfi on 2/28/2017.
 */
public class ResourceManagersImpl implements ResourceManagers {

    private Map<String, ApplicationManagersImpl> jobs = new HashMap<String, ApplicationManagersImpl>();
//    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    private static ResourceManagersImpl resourceManagers;

    private ResourceManagersImpl() {
    }

    public static ResourceManagersImpl getResourceManager() {
        if (resourceManagers == null)
            resourceManagers = new ResourceManagersImpl();
        return resourceManagers;

    }

    public String sendJobs(Jobs job) throws Exception {

        //Check Thread Pool For empty Job
        String code = java.util.UUID.randomUUID().toString();
        jobs.put(code, new ApplicationManagersImpl(job));
        return code;
    }

    public int submit(String id) throws Exception {
        if (jobs.containsKey(id)) {
            ApplicationManagersImpl job = jobs.get(id);
            job.call();
            return 1;
        }
        throw new Exception("No Job Find");
    }

    public void killAllJob() {
    }
}
