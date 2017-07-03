package com.lotfi.batchFile.readerFile;

import java.util.List;

/**
 * Created by ali on 6/28/17.
 */
public interface ControllerReader {
    <T> List<T> readData();

    int size();

    <T> List<T> readAllList();

    void readAndFullRepo();

    boolean endOfFile();


}
