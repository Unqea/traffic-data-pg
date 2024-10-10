package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.TempDwsTfcWideTfcdlineTfcunitLevelInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempDwsTfcWideTfcdlineTfcunitLevelInfoMapper extends BaseMapper<TempDwsTfcWideTfcdlineTfcunitLevelInfo> {
    @Select("SELECT * FROM dws_tfc_wide_tfcdline_tfcunit_level_info WHERE data_version = '20230930'")
    List<TempDwsTfcWideTfcdlineTfcunitLevelInfo> selectByDateVersion();

    @Select("SELECT * FROM dws_tfc_wide_tfcdline_tfcunit_level_info_copy1 WHERE data_version = '20230930' and tfcdline_id = #{tfcdlineId} and tfcdline_name = #{tfcdlineName} and road_level = #{roadLevel} and tfcunit_id = #{tfcunitId} limit 1")
    TempDwsTfcWideTfcdlineTfcunitLevelInfo selectIndexCodeByRoad(@Param("tfcdlineId") String tfcdlineId, @Param("tfcdlineName")String tfcdlineName, @Param("roadLevel")String roadLevel, @Param("tfcunitId")String tfcunitId);
}
