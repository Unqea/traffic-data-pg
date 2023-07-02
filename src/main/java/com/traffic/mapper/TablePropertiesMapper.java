package com.traffic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.traffic.entity.TableProperties;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.vo.TablePropertiesQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  Mapper 接口
 * </pre>
 *
 * @author Yinhenan
 * @since 2023-06-28
 */
@Repository
public interface TablePropertiesMapper extends BaseMapper<TableProperties> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    TablePropertiesQueryVo getTablePropertiesById(Serializable id);

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    IPage<TableProperties> getTablePropertiesPageList(Page pa,@Param("param") TablePropertiesQueryParam param);

}
