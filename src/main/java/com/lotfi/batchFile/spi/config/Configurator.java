package com.lotfi.batchFile.spi.config;

import com.lotfi.batchFile.spi.JobRepository;

import java.net.URL;

/**
 * Created by ali on 7/2/17.
 */
public interface Configurator {

    void doConfigure(URL url, JobRepository repository);
}
