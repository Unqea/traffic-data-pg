package com.traffic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.traffic.entity.Lbs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DS("odps")
public interface LbsService extends IService<Lbs> {
    List<String> allTables();

    List<String> descTable( String table);
}
