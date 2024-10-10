package com.traffic.flow.controller;

import com.traffic.common.ApiResult;
import com.traffic.flow.param.FlowParam;
import com.traffic.flow.service.FlowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/flow")
@RestController
public class FlowController {

    @Resource
    private FlowService flowService;


    @PostMapping("/insertFlow")
    @ApiOperation(value = "新增流程和节点", notes = "新增流程和节点", response = String.class)
    public ApiResult<Boolean> insertFlow(@RequestBody FlowParam flowParam)  {
        Boolean flag = flowService.insertFlow(flowParam);
        return ApiResult.result(flag);
    }


}
