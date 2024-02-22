package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.Lbs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LbsMapper extends BaseMapper<Lbs> {
    @Select("show tables")
    List<String> allTables();

    @Select("SHOW PARTITIONS ${table}")
    List<String> descTable(@Param("table") String table);
}
