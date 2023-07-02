package com.traffic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.traffic.entity.IndexTableNames;

import java.util.List;

@DS("odps")
public interface OdpsService {
    /**
     * 查询所有的表
     * @return
     */
    List<IndexTableNames> selectTables(String tableName);
}
