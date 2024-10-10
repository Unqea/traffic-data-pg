package com.traffic.service.impl;

import com.traffic.service.AsyncService;
import com.traffic.service.MyDataQService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Resource
    private MyDataQService myDataQService;

    @Async("myExecutor")
    @Override
    public void executionByExecuteSequence(String tfcunitId) throws InterruptedException, IllegalAccessException, IOException {
        myDataQService.executionByExecuteSequence(tfcunitId);
    }

}
