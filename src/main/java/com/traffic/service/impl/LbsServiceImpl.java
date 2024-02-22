package com.traffic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.traffic.entity.Lbs;
import com.traffic.mapper.LbsMapper;
import com.traffic.service.LbsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LbsServiceImpl extends ServiceImpl<LbsMapper, Lbs> implements LbsService {
    @Override
    public List<String> allTables() {
        return this.baseMapper.allTables();
    }

    @Override
    public List<String> descTable(String table) {
        return this.baseMapper.descTable(table);
    }
}
