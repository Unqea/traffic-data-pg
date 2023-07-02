package com.traffic.mapper;

import com.traffic.entity.IndexTableNames;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdpsMapper {
    /**
     * 查询所有的表
     * @param tablePre
     * @return
     */
    List<IndexTableNames> showTables(@Param("tablePre") String tablePre);


}
