package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.CameraName;
import com.traffic.entity.DwdTfcBasRdnetDsecroadWithCameraInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwdTfcBasRdnetDsecroadWithCameraInfoMapper extends BaseMapper<DwdTfcBasRdnetDsecroadWithCameraInfo> {
    @Select("SELECT DISTINCT index_code as indexcode,name FROM dws_hzjjzd_monitory_point ")
    List<CameraName> selectCameraName();
}
