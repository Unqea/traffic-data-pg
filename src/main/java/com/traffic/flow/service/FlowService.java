package com.traffic.flow.service;

import com.traffic.flow.param.FlowParam;

public interface FlowService {

    /**
     * 新增流程和节点
     * @param flowParam 流程参数
     * @return 新增流程是否成功
     */
    Boolean insertFlow(FlowParam flowParam);
}
