package com.traffic;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.traffic.entity.OdsNewCheckpointDeviceInfo;
import com.traffic.entity.TempDwsTfcWideTfcdlineTfcunitLevelInfo;
import com.traffic.entity.UucOrganization;
import com.traffic.mapper.TempDwsTfcWideTfcdlineTfcunitLevelInfoMapper;
import com.traffic.mapper.OdsNewCheckpointDeviceInfoMapper;
import com.traffic.mapper.UucOrganizationMapper;
import com.traffic.service.TempDwsTfcWideTfcdlineTfcunitLevelInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MyTest2 {

    @Resource
    private TempDwsTfcWideTfcdlineTfcunitLevelInfoMapper dwsTfcWideTfcdlineTfcunitLevelInfoMapper;

    @Resource
    private TempDwsTfcWideTfcdlineTfcunitLevelInfoService tempDwsTfcWideTfcdlineTfcunitLevelInfoService;

    @Test
    public void test01() {
        //所有
        List<TempDwsTfcWideTfcdlineTfcunitLevelInfo> list = dwsTfcWideTfcdlineTfcunitLevelInfoMapper.selectByDateVersion();
        //有摄像头
        for (TempDwsTfcWideTfcdlineTfcunitLevelInfo info : list) {
            //如果摄像头为空，尝试去匹配
            if (StrUtil.isBlank(info.getIndexCode())){
                TempDwsTfcWideTfcdlineTfcunitLevelInfo indexCode = dwsTfcWideTfcdlineTfcunitLevelInfoMapper.selectIndexCodeByRoad(
                        info.getTfcdlineId(),
                        info.getTfcdlineName(),
                        info.getRoadLevel(),
                        info.getTfcunitId()
                );
                if (ObjUtil.isNotEmpty(indexCode)){
                    info.setIndexCode(indexCode.getIndexCode());
                    info.setLatitude(indexCode.getLatitude());
                    info.setLongitude(indexCode.getLongitude());
                }
            }
        }
        tempDwsTfcWideTfcdlineTfcunitLevelInfoService.saveBatch(list);

    }




}
