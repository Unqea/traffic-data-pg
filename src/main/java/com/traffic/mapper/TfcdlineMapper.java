package com.traffic.mapper;

import com.traffic.entity.Tfcdline;
import com.traffic.entity.TfcdlineVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TfcdlineMapper {
    @Select("SELECT tfcdline_id AS id, road_level AS level, tfcdline_name AS name, len, lnglat_seq FROM dws_tfc_net_tfcdline_nd_info_d WHERE line_type_no = '04' AND data_version = '20230930' AND adcode = '330100'")
    List<TfcdlineVo> fetchTfcdlineData();
}
