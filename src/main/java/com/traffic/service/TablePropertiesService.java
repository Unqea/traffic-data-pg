package com.traffic.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.traffic.controller.TablePropertiesController;
import com.traffic.entity.TableProperties;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.vo.TablePropertiesQueryVo;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  服务类
 * </pre>
 *
 * @author Yinhenan
 * @since 2023-06-28
 */
public interface TablePropertiesService extends IService<TableProperties> {

    /**
     * 保存
     *
     * @param tableProperties
     * @return
     * @throws Exception
     */
    boolean saveTableProperties(TableProperties tableProperties) throws Exception;

    /**
     * 修改
     *
     * @param tableProperties
     * @return
     * @throws Exception
     */
    boolean updateTableProperties(TableProperties tableProperties) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTableProperties(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    TablePropertiesQueryVo getTablePropertiesById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param tablePropertiesQueryParam
     * @return
     * @throws Exception
     */
    IPage<TableProperties> getTablePropertiesPageList(TablePropertiesQueryParam tablePropertiesQueryParam) throws Exception;

}
