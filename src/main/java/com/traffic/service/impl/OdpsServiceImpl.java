package com.traffic.service.impl;

import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.traffic.entity.IndexTableNames;
import com.traffic.mapper.OdpsMapper;
import com.traffic.service.OdpsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OdpsServiceImpl implements OdpsService {
    @Resource
    private OdpsMapper odpsMapper;

    /**
     * 查询所有的表
     * @return
     */
    @Override
    public List<IndexTableNames> selectTables(String tableName) {
        return null;
    }

}
