package com.traffic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.traffic.entity.ExcelBean;

@DS("local")
public interface ExcelBeanService extends IService<ExcelBean> {
}
