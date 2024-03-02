package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.JingQu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JingQuMapper extends BaseMapper<JingQu> {
    @Select("select\n" +
            "    序号, 道路id, 道路名称, 道路类型, 拥堵指数, 是否常发拥堵，是则拥堵时长 as 是否常发拥堵是则拥堵时长,\n" +
            "    GROUP_CONCAT(diag_result) AS 成因,\n" +
            "    GROUP_CONCAT(concat('【',t2.leida_name,'】')) AS 类别\n" +
            "from\n" +
            "    jingqu as t1\n" +
            "    left join c_luduan as t2 on t1.道路id = t2.tfcdline_id\n" +
            "group by 序号, 道路id, 道路名称, 道路类型, 拥堵指数, 是否常发拥堵，是则拥堵时长\n" +
            "\n" +
            "ORDER BY CAST(t1.序号 AS UNSIGNED) ")
    List<JingQu> getAll();
}
