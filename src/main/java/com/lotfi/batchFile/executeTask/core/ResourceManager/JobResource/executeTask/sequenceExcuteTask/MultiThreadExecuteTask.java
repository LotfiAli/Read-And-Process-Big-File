package com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.sequenceExcuteTask;

import com.lotfi.batchFile.executeTask.core.ResourceManager.JobResource.executeTask.BaseExecuteTask;
import com.lotfi.batchFile.executeTask.utility.PcManager;

/**
 * Created by ali on 6/28/17.
 */
public class MultiThreadExecuteTask extends BaseExecuteTask {
    protected int getCountCpu() {
        return PcManager.getCountCpu();
    }
}
