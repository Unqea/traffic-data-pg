package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.traffic.entity.UucOrganization;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UucOrganizationMapper extends BaseMapper<UucOrganization> {

    @Select("select guid,org_name,parent_id from uuc_organization where is_deleted != 1;")
    List<UucOrganization> getAll();
}
