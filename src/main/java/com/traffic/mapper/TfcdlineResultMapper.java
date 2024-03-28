package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.TfcdlineResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TfcdlineResultMapper extends BaseMapper<TfcdlineResult> {
    @Select("SELECT 0 as id,\n" +
            "       t1.tfcdline_id\n" +
            "     , t1.tfcdline_name\n" +
            "     , if(t1.road_level = '43000', '快速路', '地面道路') as road_type\n" +
            "     , ifnull(t1.jam_period_dur, '0')             AS jam_dur\n" +
            "     , t1.jam_period\n" +
            "     , t1.avg_jam_delay_index\n" +
            "     , '' as flow\n" +
            "     , t1.avg_speed\n" +
            "FROM service_tfc_state_tfcdline_doe_jamperiodindex_m t1\n" +
            "         LEFT JOIN dws_tfc_wide_tfcdline_tfcunit_level_info t2 ON t1.tfcdline_id = t2.tfcdline_id\n" +
            "WHERE t1.period_type = #{period_type} \n" +
            "  AND t1.date_type_no = 1\n" +
            "  AND t1.stat_month = '202401'\n" +
            "  AND t1.tfcunit_id IN ('08330102', '08330105', '08330108', '08330106')\n" +
            "  AND t1.road_level IN ('43000', '44000', '45000')\n" +
            "ORDER BY CAST(jam_period_dur AS UNSIGNED) DESC")
    List<TfcdlineResult> selectAllLine(@Param("period_type") Integer period_type);


    @Select("SELECT ifnull(t1.jam_period_dur, '0')             AS jam_dur\n" +
            "FROM service_tfc_state_tfcdline_doe_jamperiodindex_m t1\n" +
            "         LEFT JOIN dws_tfc_wide_tfcdline_tfcunit_level_info t2 ON t1.tfcdline_id = t2.tfcdline_id\n" +
            "WHERE t1.period_type = #{period_type}\n" +
            "  AND t1.date_type_no = 1\n" +
            "  AND t1.stat_month = '202401'\n" +
            "  AND t1.tfcunit_id IN ('08330102', '08330105', '08330108', '08330106')\n" +
            "  AND t1.road_level IN ('43000', '44000', '45000')\n" +
            "  AND t1.tfcdline_id = #{tfcdline_id}\n" +
            "limit 1")
    String selectOneLine(@Param("period_type") Integer period_type, @Param("tfcdline_id") String tfcdline_id);
}
