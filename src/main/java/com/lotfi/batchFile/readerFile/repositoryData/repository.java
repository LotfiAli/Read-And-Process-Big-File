package com.lotfi.batchFile.readerFile.repositoryData;

/**
 * Created by ali on 6/24/17.
 */
public interface repository<T> {

    T readData();

    T nextBlock();

    void writeData(T data);
}
