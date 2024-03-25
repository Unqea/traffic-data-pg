package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.OdsNewCheckpointDeviceInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdsNewCheckpointDeviceInfoMapper extends BaseMapper<OdsNewCheckpointDeviceInfo> {
    @Select("select * from ods_new_checkpoint_device_info where lng like '30.%'")
    List<OdsNewCheckpointDeviceInfo> getAll();
}
