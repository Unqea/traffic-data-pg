package com.traffic.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.traffic.entity.DwsCustomTfcunitScnStatus;
import com.traffic.entity.TbTfcunitId;
import com.traffic.http.DataQHttpClient;

import com.traffic.mapper.TbTfcunitIdMapper;
import com.traffic.service.MyDataQService;
import com.traffic.utils.BeanTool;
import com.traffic.utils.NonBlockingSleep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MyDataQServiceImpl implements MyDataQService {
    @Resource
    private TbTfcunitIdMapper tbTfcunitIdMapper;
    /**
     * 检验是否有未完成任务，等待未完成任务完成
     * @param tfcunitId 自定义区域id
     * @throws InterruptedException 中断异常
     */
    private void checkLoop(String tfcunitId) throws InterruptedException {
        TbTfcunitId tbTfcunitId = new TbTfcunitId();
        tbTfcunitId.setTfcunitId(tfcunitId);
        tbTfcunitId.setCreateTime(new Date());
        tbTfcunitId.setStu(0);
        tbTfcunitIdMapper.insert(tbTfcunitId);


        LambdaQueryWrapper<TbTfcunitId> wrappers = new LambdaQueryWrapper<>();
        wrappers.eq(TbTfcunitId::getTfcunitId,tfcunitId);
        int id = tbTfcunitIdMapper.selectOne(wrappers).getId();
        System.out.println("id是=" + id);

        for (int i = 0; i < 1000; i++) {
            LambdaQueryWrapper<TbTfcunitId> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TbTfcunitId::getStu,1);
            wrapper.eq(TbTfcunitId::getId,id);
            Integer integer = tbTfcunitIdMapper.selectCount(wrapper);
            if (integer > 0){
                break;
            }else {
                Thread.sleep(60000);
                //NonBlockingSleep.sleep(10000,()->{});
                System.out.println("十秒循环查询一次状态的id = " + id);
            }
        }


    }


    @Override
    public List<DwsCustomTfcunitScnStatus> executionByExecuteSequence(String tfcunitId) throws IOException, InterruptedException, IllegalAccessException {
        log.info("执行入参:tfcunitId=[{}]",tfcunitId);
        //校验前面是否有正在执行的区域，若有则等等
        checkLoop(tfcunitId);
        Thread.sleep(60000);
        //NonBlockingSleep.sleep(60000,()->{});
        LambdaQueryWrapper<TbTfcunitId> wrappers = new LambdaQueryWrapper<>();
        wrappers.eq(TbTfcunitId::getTfcunitId,tfcunitId);
        TbTfcunitId tbTfcunitId = tbTfcunitIdMapper.selectOne(wrappers);
        tbTfcunitId.setStu(1);
        System.out.println("执行成功的id为 = " + tbTfcunitId.getId());
        log.info("执行成功:tfcunitId=[{}]",tfcunitId);
        return null;
    }
}
