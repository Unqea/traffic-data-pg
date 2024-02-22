package com.traffic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.traffic.entity.ExcelBean;
import com.traffic.mapper.ExcelBeanMapper;
import com.traffic.service.ExcelBeanService;
import org.springframework.stereotype.Service;

@Service
public class ExcelBeanServiceImpl extends ServiceImpl<ExcelBeanMapper, ExcelBean> implements ExcelBeanService {
}
