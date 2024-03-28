package com.traffic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.traffic.entity.TfcdlineResult;
import com.traffic.mapper.TfcdlineResultMapper;
import com.traffic.service.TfcdlineResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TfcdlineResultServiceImpl extends ServiceImpl<TfcdlineResultMapper, TfcdlineResult> implements TfcdlineResultService {
    @Override
    public List<TfcdlineResult> selectAllLine(Integer period_type) {
        return this.baseMapper.selectAllLine(period_type);
    }

    @Override
    public String selectOneLine(int period_type, String tfcdlineId) {
        return this.baseMapper.selectOneLine(period_type,tfcdlineId);
    }
}
