package com.traffic.service;

import com.traffic.entity.DwsCustomTfcunitScnStatus;

import java.io.IOException;
import java.util.List;

public interface MyDataQService {


    /**
     * 执行(按照执行序列)
     * @return
     */
    List<DwsCustomTfcunitScnStatus> executionByExecuteSequence(String tfcunitId) throws IOException, InterruptedException, IllegalAccessException;

}
