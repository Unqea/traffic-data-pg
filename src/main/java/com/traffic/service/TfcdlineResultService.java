package com.traffic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.traffic.entity.TfcdlineResult;

import java.util.List;

@DS("zk_rds")
public interface TfcdlineResultService extends IService<TfcdlineResult> {
    List<TfcdlineResult> selectAllLine(Integer period_type);

    String selectOneLine(int period_type, String tfcdlineId);
}
