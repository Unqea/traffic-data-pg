package com.traffic;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.aliyun.odps.simpleframework.xml.transform.InvalidFormatException;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.traffic.entity.*;
import com.traffic.mapper.*;
import com.traffic.service.DwdTfcBasRdnetDsecroadLinkTwokmInfoService;
import com.traffic.service.DwsHzjjzdMonitoryPointService;
import com.traffic.service.ExcelBeanService;
import com.traffic.service.LbsService;
import com.traffic.utils.CoordinateUtils;
import com.traffic.utils.MyExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MyTest {

    @Resource
    private UucOrganizationMapper uucOrganizationMapper;
    @Resource
    private OdsNewCheckpointDeviceInfoMapper odsNewCheckpointDeviceInfoMapper;

    @Test
    public void test11() {
        List<OdsNewCheckpointDeviceInfo> list = odsNewCheckpointDeviceInfoMapper.getAll();
        for (OdsNewCheckpointDeviceInfo odsNewCheckpointDeviceInfo : list) {
            String lat = odsNewCheckpointDeviceInfo.getLat();
            String lng = odsNewCheckpointDeviceInfo.getLng();

            odsNewCheckpointDeviceInfo.setLat(lng);
            odsNewCheckpointDeviceInfo.setLng(lat);

            odsNewCheckpointDeviceInfoMapper.updateById(odsNewCheckpointDeviceInfo);
        }
    }


    @Test
    public void test10() {
        //所有数据查出来
        List<UucOrganization> list = uucOrganizationMapper.getAll();
        //将每个根节点找出来
        List<UucOrganization> collect = list.stream().filter(tbLabel -> tbLabel.getParentId().equals("root")).map((menu) -> {
            //传入当前标签和所有标签，在所有标签中找到当前标签的子标签
            menu.setList(getChildrenList(menu,list));
            return menu;
        }).collect(Collectors.toList());
    }

    private List<UucOrganization> getChildrenList(UucOrganization current, List<UucOrganization> all){
        //将该节点的所有子节点找出来
        List<UucOrganization> collect = all.stream().filter(tbLabel -> tbLabel.getParentId().equals(current.getGuid()))
                .map(tbLabel -> {
                    //重复过程，根据当前节点继续查询当前节点下的子节点； 最后将所有子节点放入该父节点下
                    tbLabel.setList(getChildrenList(tbLabel,all));
                    return tbLabel;
                }).collect(Collectors.toList());
        return collect;
    }



    @Test
    public void test09() {
        //所有数据查出来
        List<UucOrganization> list = uucOrganizationMapper.getAll();
        //在所有数据中找到根节点
        List<UucOrganization> parentList = list.stream().filter(s -> s.getParentId().equals("root")).collect(Collectors.toList());
        //循环查找每个根节点的子节点
        List<UucOrganization> result = parentList.stream().map(
                item -> {
                    List<UucOrganization> child = findChild(item, list);
                    item.setList(child);
                    return item;
                }
        ).collect(Collectors.toList());
        System.out.println(result);
    }

    private List<UucOrganization> findChild(UucOrganization parent, List<UucOrganization> list) {
        List<UucOrganization> children = new ArrayList<>();
        for (UucOrganization org : list) {
            if (org.getParentId().equals(parent.getGuid())) {
                List<UucOrganization> subChildren = findChild(org, list);
                org.setList(subChildren);
                children.add(org);
            }
        }
        return children;
    }


    /*private List<UucOrganization> findChild(UucOrganization uucOrganization, List<UucOrganization> list, List<UucOrganization> temp) {
        //从全部数据中查询该id的子id
        List<UucOrganization> collect = list.stream().filter(e -> e.getParentId().equals( uucOrganization.getGuid())).collect(Collectors.toList());
        //若子id不为空
        if (ObjUtil.isNotEmpty(collect)){
            //将此次的子集id放到该父级id中
            uucOrganization.setList(collect);
            //只有父标签才添加到集合中
            if(uucOrganization.getGuid().equals("root")){
                temp.add(uucOrganization);
            }
            //再查询每个子集，是否还有子集
            for (UucOrganization organization : collect) {
                findChild(organization,list,temp);
            }
        }
        //如果没有子集，该次查询完成
        return temp;
    }*/




}
