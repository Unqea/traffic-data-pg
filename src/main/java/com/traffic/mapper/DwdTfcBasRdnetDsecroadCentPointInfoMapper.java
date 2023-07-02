package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.DwdTfcBasRdnetDsecroadCentPointInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwdTfcBasRdnetDsecroadCentPointInfoMapper extends BaseMapper<DwdTfcBasRdnetDsecroadCentPointInfo> {

    /**
     * 获取该坐标的附近两公里的道路
     * @param centPoint
     * @return
     */
    List<DwdTfcBasRdnetDsecroadCentPointInfo> getLinks(@Param("centPoint") String centPoint);
}
