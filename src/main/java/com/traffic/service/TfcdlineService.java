package com.traffic.service;

import cn.hutool.core.bean.BeanUtil;
import com.traffic.entity.Geometry;
import com.traffic.entity.Tfcdline;
import com.traffic.entity.TfcdlineVo;
import com.traffic.mapper.TfcdlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TfcdlineService {

    @Autowired
    private TfcdlineMapper tfcdlineMapper;

    public List<Tfcdline> getTfcdlineData() {
        List<TfcdlineVo> data = tfcdlineMapper.fetchTfcdlineData();
        List<Tfcdline> result = new ArrayList<>();
        for (TfcdlineVo record : data) {
            Tfcdline tfcdline = new Tfcdline();
            BeanUtil.copyProperties(record,tfcdline);
            tfcdline.setGeometry(new Geometry(record.getLnglatSeq()));
            result.add(tfcdline);
        }
        
        return result;
    }
}
