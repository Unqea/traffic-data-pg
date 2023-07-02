package com.traffic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.traffic.entity.TableProperties;
import com.traffic.mapper.TablePropertiesMapper;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.service.TablePropertiesService;
import com.traffic.vo.TablePropertiesQueryVo;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * <pre>
 *  服务实现类
 * </pre>
 *
 * @author Yinhenan
 * @since 2023-06-28
 */
@Slf4j
@Service
public class TablePropertiesServiceImpl extends ServiceImpl<TablePropertiesMapper, TableProperties> implements TablePropertiesService {

    @Autowired
    private TablePropertiesMapper tablePropertiesMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTableProperties(TableProperties tableProperties) throws Exception {
        return super.save(tableProperties);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTableProperties(TableProperties tableProperties) throws Exception {
        return super.updateById(tableProperties);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTableProperties(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public TablePropertiesQueryVo getTablePropertiesById(Serializable id) throws Exception {
        return tablePropertiesMapper.getTablePropertiesById(id);
    }

    /**
     * 获取分页对象
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public IPage<TableProperties> getTablePropertiesPageList(TablePropertiesQueryParam param) throws Exception {
        Page pa = new Page<>(param.getCurrent(),param.getSize());
        return this.baseMapper.getTablePropertiesPageList(pa,param);
    }

}
