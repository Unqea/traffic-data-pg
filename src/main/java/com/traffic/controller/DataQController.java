package com.traffic.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.traffic.common.ApiResult;
import com.traffic.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dataQ")
@Api(tags =  "DataQ调用 API")
public class DataQController {

    @Resource
    private AsyncService asyncService;


    @GetMapping("/executionByExecuteSequence")
    @ApiOperation(value = "执行单月(按照执行序列)", notes = "执行单月(按照执行序列)", response = String.class)
    public ApiResult<String> executionByExecuteSequence(@ApiParam(value = "自定义区域id；", required = true)String tfcunitId) throws IOException, InterruptedException, IllegalAccessException {
        asyncService.executionByExecuteSequence(tfcunitId);
        return ApiResult.ok("同步基础数据中");
    }





}
