package com.lotfi.batchFile.spi.config;

import com.lotfi.batchFile.helper.OptionConverter;
import com.lotfi.batchFile.spi.JobRepository;

import java.util.Properties;

import static com.lotfi.batchFile.spi.config.PropertyConfigurator.CONTROLLER_READER;
import static com.lotfi.batchFile.spi.config.PropertyConfigurator.PLUGING_READER;

/**
 * Created by ali on 7/3/17.
 */
public enum ElementConfiguration {

    OUTPUT_EXECUTE_JOB("jobs.output.execute.job", StringConvert.stringConvert),
    NAME("jobs.name", StringConvert.stringConvert),
    MAP_JOB("jobs.map.job", StringConvert.stringConvert),
    REDUCE_JOB("jobs.reduce.job", StringConvert.stringConvert),
    EVENT_JOB("jobs.event.job", StringConvert.stringConvert),
    CONTROLLER_READER("jobs.controller.reader", StringConvert.stringConvert),
    PLUGING_READER("jobs.pluging.reader", StringConvert.stringConvert);

    ElementConfiguration(String name, ConvertElement convertElement) {
        this.convertElement = convertElement;
        this.name = name;
    }

    private String name;
    private ConvertElement convertElement;

    public String getName() {
        return name;
    }

    public ConvertElement getConvertElement() {
        return convertElement;
    }

    public String readProperty(Properties properties) {

        return this.convertElement.readProperty(properties, this);
    }

    public Properties writeProperties(String propertiesValue) {

        return this.convertElement.writeProperties(propertiesValue);
    }

    interface ConvertElement {
        String readProperty(Properties properties, ElementConfiguration elementConfiguration);

        Properties writeProperties(String propertiesValue);
    }

    public static class StringConvert implements ConvertElement {

        public static StringConvert stringConvert = new StringConvert();

        public String readProperty(Properties properties, ElementConfiguration elementConfiguration) {
            return OptionConverter.substVars(elementConfiguration.getName(), properties);
        }

        public Properties writeProperties(String propertiesValue) {
            return null;
        }
    }
}
