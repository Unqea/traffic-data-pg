package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.ChangFa;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangFaMapper extends BaseMapper<ChangFa> {
    @Select("select\n" +
            "    排名, 道路id, 名称, 拥堵时长, 拥堵指数, 道路类型,\n" +
            "    GROUP_CONCAT(diag_result) AS 成因,\n" +
            "    GROUP_CONCAT(concat('【',t2.leida_name,'】')) AS 类别\n" +
            "from\n" +
            "    yongdu as t1\n" +
            "    left join c_luduan as t2 on t1.道路id = t2.tfcdline_id\n" +
            "group by\n" +
            " 排名, 道路id, 名称, 拥堵时长, 拥堵指数, 道路类型\n" +
            "ORDER BY CAST(t1.排名 AS UNSIGNED)")
    List<ChangFa> getAll();
}
