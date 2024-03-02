package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.LuKou;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuKouMapper extends BaseMapper<LuKou> {
    @Select("select\n" +
            "    t1.路口id, 路口名称, 延误指数, 饱和度, 延时指数, 延误等级,\n" +
            "    GROUP_CONCAT(diag_info) AS 成因详情,\n" +
            "    GROUP_CONCAT(diag_result) AS 成因,\n" +
            "    GROUP_CONCAT(concat('【',t2.leida_name,'】')) AS 类别\n" +
            "from\n" +
            "    lukou as t1\n" +
            "    left join c_lukou as t2 on t1.路口id = t2.cross_id\n" +
            "group by 路口id, 路口名称, 延误指数, 饱和度, 延时指数, 延误等级\n" +
            "ORDER BY CAST(t1.延时指数 AS UNSIGNED) DESC ")
    List<LuKou> getAll();
}
