package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.DwsChangFaM;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwsChangFaMMapper extends BaseMapper<DwsChangFaM> {
    @Select("select * from dws_chang_fa_m")
    List<DwsChangFaM> getAll();

    @Select("select if(factor_type = 1,'常规性拥堵',if(factor_type = 2,'结构性拥堵','秩序性拥堵')) as factor_type  from t_zy_leida_type_dict where subclass_name = #{msg} limit 1")
    String getType(@Param("msg") String msg);

    @Select("SELECT jam_period FROM service_tfc_state_tfcdline_doe_jamperiodindex_m WHERE `month` = '202401' AND period_type = 0 AND date_type_no = 0 AND tfcdline_id = #{tfcdline_id} AND tfcunit_id = '08330100' LIMIT 1")
    String getPeriod(@Param("tfcdline_id") String tfcdline_id);
}
