package com.traffic.service;

import java.io.IOException;
import java.util.List;

public interface AsyncService {
    /**
     * 执行(按照执行序列)
     * @param tfcunitId
     * @return
     */
    void executionByExecuteSequence(String tfcunitId) throws InterruptedException, IllegalAccessException, IOException;

}
