package com.lotfi.batchFile.spi.config;

import com.lotfi.batchFile.executeTask.core.jobs.Jobs;
import com.lotfi.batchFile.executeTask.core.jobs.OutPutExecuteJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.EventJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.MapJob;
import com.lotfi.batchFile.executeTask.core.jobs.mapReduceJob.ReduceJob;
import com.lotfi.batchFile.helper.OptionConverter;
import com.lotfi.batchFile.readerFile.ControllerReader;
import com.lotfi.batchFile.spi.JobRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by ali on 7/2/17.
 */
public class PropertyConfigurator extends ConfiguratorBase {

    static final String OUTPUT_EXECUTE_JOB = "jobs.output.execute.job";
    static final String NAME = "jobs.name";
    static final String MAP_JOB = "jobs.map.job";
    static final String REDUCE_JOB = "jobs.reduce.job";
    static final String EVENT_JOB = "jobs.event.job";
    static final String CONTROLLER_READER = "jobs.controller.reader";
    static final String PLUGING_READER = "jobs.pluging.reader";


    public void doConfigure(URL url, JobRepository repo) {
        Properties props = new Properties();
//        getLogger(repository).debug(
//                "Reading configuration from URL {}", configURL);

        InputStream in = null;
        try {
            in = url.openStream();
            props.load(in);
        } catch (Exception e) {
//            String errMsg =
//                    "Could not read configuration file from URL [" + configURL + "].";
            return;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }

        doConfigure(props, repo);
    }

    public void doConfigure(Properties properties, JobRepository repo) {

        try {
            prepareName(properties, repo);
            prepareOutputExecuteJobs(properties, repo);
            prepareMapJob(properties, repo);
            prepareReduceJob(properties, repo);
            prepareEventJob(properties, repo);
            prepareControllerReader(properties, repo);
//            prepareAllOFPropties(properties, repo);
        } finally {
        }
    }

    private void prepareMapJob(Properties properties, JobRepository repo) {
        String classPath= ElementConfiguration.MAP_JOB.readProperty(properties);
         MapJob mapJob= (MapJob) OptionConverter.instantiateByClassName(classPath);
        Jobs job=repo.getJobLogger();
        job.getExecuteTask().setMapJob(mapJob);
    }

//    private void prepareAllOFPropties(Properties properties, JobRepository repo) {
//
//        for (ElementConfiguration element:ElementConfiguration.values()) {
//            element.readProperty(properties);
//        }
//    }

    private void prepareControllerReader(Properties properties, JobRepository repo) {
        String classPath= ElementConfiguration.CONTROLLER_READER.readProperty(properties);
        ControllerReader controllerReader= (ControllerReader) OptionConverter.instantiateByClassName(classPath);
        Jobs job=repo.getJobLogger();
        job.getExecuteTask().setControllerReader(controllerReader);
    }

    private void prepareEventJob(Properties properties, JobRepository repo) {
        String classPath= ElementConfiguration.EVENT_JOB.readProperty(properties);
        EventJob eventJob= (EventJob) OptionConverter.instantiateByClassName(classPath);
        Jobs job=repo.getJobLogger();
        job.getExecuteTask().setEventJob(eventJob);
    }

    private void prepareReduceJob(Properties properties, JobRepository repo) {
        String classPath= ElementConfiguration.REDUCE_JOB.readProperty(properties);
        ReduceJob reduceJob= (ReduceJob) OptionConverter.instantiateByClassName(classPath);
        Jobs job=repo.getJobLogger();
        job.getExecuteTask().setReduceJob(reduceJob);
    }

//    private void prepareJob(Properties properties, JobRepository repo) {
//
//    }

    private void prepareOutputExecuteJobs(Properties properties, JobRepository repo) {
        String classPath= ElementConfiguration.OUTPUT_EXECUTE_JOB.readProperty(properties);
        OutPutExecuteJob outPutExecuteJob= (OutPutExecuteJob) OptionConverter.instantiateByClassName(classPath);
        Jobs job=repo.getJobLogger();
        job.getExecuteTask().setOutputExecuteJob(outPutExecuteJob);

    }

    private void prepareName(Properties properties, JobRepository repo) {
       String nameJobs= ElementConfiguration.NAME.readProperty(properties);
//        OptionConverter.instantiateByClassName(classPathName);
        Jobs job=repo.getJobLogger();
        job.setName(nameJobs);
    }


}
