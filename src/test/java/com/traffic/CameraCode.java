package com.traffic;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.traffic.entity.*;
import com.traffic.mapper.DwdTfcBasRdnetDsecroadWithCameraInfoMapper;
import com.traffic.service.*;
import com.traffic.utils.CoordinateExtractor;
import com.traffic.utils.DistanceCalculator;
import com.traffic.vo.CameraIndex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class CameraCode {

    @Resource
    private DwsHzjjzdMonitoryPointService dwsHzjjzdMonitoryPointService;
    @Resource
    private DwdTfcBasRdnetDsecroadInfoService dwdTfcBasRdnetDsecroadInfoService;
    @Resource
    private DwdTfcBasRdnetDsecroadWithCameraInfoService dwdTfcBasRdnetDsecroadWithCameraInfoService;
    @Resource
    private DwdTfcRltnWideDsecroadCameraInfoService dwdTfcRltnWideDsecroadCameraInfoService;
    @Resource
    private DwdTfcBasRdnetDsecroadWithCameraInfoMapper dwdTfcBasRdnetDsecroadWithCameraInfoMapper;
    @Resource
    private DwdTfcRltnWideDsecroadCameraInfoCopyService dwdTfcRltnWideDsecroadCameraInfoCopyService;

    @Test
    public void dlc() {
        // 定义每页的条数
        int pageSize = 1000;
        int currentPage = 1;
        //新数据
        List<Scenarios> scenarios  =  dwdTfcBasRdnetDsecroadWithCameraInfoMapper.getScenarios();
        while (true){
            Page<DwdTfcRltnWideDsecroadCameraInfo> page = new Page<>(currentPage, pageSize);
            LambdaQueryWrapper<DwdTfcRltnWideDsecroadCameraInfo> roadWrapper = new LambdaQueryWrapper<>();
            roadWrapper.eq(DwdTfcRltnWideDsecroadCameraInfo::getDataVersion, "20230930");
            IPage<DwdTfcRltnWideDsecroadCameraInfo> roadPage = dwdTfcRltnWideDsecroadCameraInfoService.page(page, roadWrapper);
            //旧数据
            List<DwdTfcRltnWideDsecroadCameraInfo> roadList = roadPage.getRecords();
            // 判断是否有数据
            if (CollUtil.isEmpty(roadList)) {
                break; // 如果没有数据了，跳出循环
            }
            //需要提交的数据
            List<DwdTfcRltnWideDsecroadCameraInfoCopy> infos = new ArrayList<>();

            for (DwdTfcRltnWideDsecroadCameraInfo info : roadList) {
                //创建目标对象
                DwdTfcRltnWideDsecroadCameraInfoCopy cameraInfoCopy = new DwdTfcRltnWideDsecroadCameraInfoCopy();
                //拷贝其他属性
                BeanUtil.copyProperties(info, cameraInfoCopy);

                //======处理监控数据开始======
                List<CameraIndex> cameraIndices = new ArrayList<>();
                if (StrUtil.isNotBlank(info.getIndexCodeInfo())){
                    cameraIndices = JSONUtil.toList(info.getIndexCodeInfo(), CameraIndex.class);
                    List<String> codes = cameraIndices.stream().map(CameraIndex::getIndexCode).collect(Collectors.toList());
                    for (Scenarios scenario : scenarios) {
                        if (info.getDsecroadId().equals(scenario.getTfcdlineId()) && !codes.contains(scenario.getIndexCode())){
                            CameraIndex cameraIndex = new CameraIndex();
                            cameraIndex.setCameraName(scenario.getVideoName());
                            cameraIndex.setIndexCode(scenario.getIndexCode());
                            cameraIndex.setLatitude(scenario.getLatitude());
                            cameraIndex.setLongitude(scenario.getLongitude());
                            cameraIndices.add(cameraIndex);
                            System.out.println("=====旧的=====" + info.getDsecroadId() + "=====旧的=====" );
                        }
                    }
                }else {
                    for (Scenarios scenario : scenarios) {
                        if (info.getDsecroadId().equals(scenario.getTfcdlineId())){
                            CameraIndex cameraIndex = new CameraIndex();
                            cameraIndex.setCameraName(scenario.getVideoName());
                            cameraIndex.setIndexCode(scenario.getIndexCode());
                            cameraIndex.setLatitude(scenario.getLatitude());
                            cameraIndex.setLongitude(scenario.getLongitude());
                            cameraIndices.add(cameraIndex);
                            System.out.println("=====新的=====" + info.getDsecroadId() + "=====新的=====" );
                        }
                    }
                }
                if (CollUtil.isNotEmpty(cameraIndices)){
                    cameraInfoCopy.setIndexCodeInfo(JSONUtil.toJsonStr(cameraIndices));
                }
                //======处理监控数据结束======


                infos.add(cameraInfoCopy);
            }

            boolean success = dwdTfcRltnWideDsecroadCameraInfoCopyService.saveBatch(infos);
            infos.clear(); // 提交后清空列表

            if (!success) {
                System.out.println("数据提交失败，分页：" + currentPage);
                break;
            }

            // 判断是否到最后一页
            if (roadPage.getPages() <= currentPage) {
                break;
            }
            // 更新到下一页
            currentPage++;

        }
    }






    @Test
    public void zuihou() {
        // 定义每页的条数
        int pageSize = 1000;
        int currentPage = 1;
        List<CameraName> cameraNames = dwdTfcBasRdnetDsecroadWithCameraInfoMapper.selectCameraName();

        // 分页查询路段信息并逐页提交
        while (true) {
            // 创建分页对象
            Page<DwdTfcBasRdnetDsecroadWithCameraInfo> page = new Page<>(currentPage, pageSize);
            LambdaQueryWrapper<DwdTfcBasRdnetDsecroadWithCameraInfo> roadWrapper = new LambdaQueryWrapper<>();
            roadWrapper.eq(DwdTfcBasRdnetDsecroadWithCameraInfo::getDataVersion, "20230930");

            IPage<DwdTfcBasRdnetDsecroadWithCameraInfo> roadPage = dwdTfcBasRdnetDsecroadWithCameraInfoService.page(page, roadWrapper);
            List<DwdTfcBasRdnetDsecroadWithCameraInfo> roadList = roadPage.getRecords();

            // 判断是否有数据
            if (CollUtil.isEmpty(roadList)) {
                break; // 如果没有数据了，跳出循环
            }

            List<DwdTfcRltnWideDsecroadCameraInfo> infos = new ArrayList<>();
            for (DwdTfcBasRdnetDsecroadWithCameraInfo info : roadList) {
                DwdTfcRltnWideDsecroadCameraInfo cameraInfo = new DwdTfcRltnWideDsecroadCameraInfo();
                BeanUtil.copyProperties(info, cameraInfo);

                if (StrUtil.isNotBlank(info.getIndexCodeInfo())) {
                    String indexCodeInfo = info.getIndexCodeInfo();
                    List<CameraIndex> cameraIndices = new ArrayList<>();
                    String[] split = indexCodeInfo.split(";");

                    for (String s : split) {
                        CameraIndex cameraIndex = new CameraIndex();
                        String[] split1 = s.split(",");
                        cameraIndex.setIndexCode(split1[0]);
                        cameraIndex.setLongitude(split1[1]);
                        cameraIndex.setLatitude(split1[2]);


                        for (CameraName cameraName : cameraNames) {
                            if (cameraName.getIndexcode().equals(split1[0])){
                                cameraIndex.setCameraName(cameraName.getName());
                                break;
                            }
                        }

                        cameraIndices.add(cameraIndex);
                    }
                    cameraInfo.setIndexCodeInfo(JSONUtil.toJsonStr(cameraIndices));
                }

                infos.add(cameraInfo);
            }
            boolean success = dwdTfcRltnWideDsecroadCameraInfoService.saveBatch(infos);
            infos.clear(); // 提交后清空列表

            if (!success) {
                System.out.println("数据提交失败，分页：" + currentPage);
                break;
            }

            // 判断是否到最后一页
            if (roadPage.getPages() <= currentPage) {
                break;
            }

            // 更新到下一页
            currentPage++;

        }

    }






    /**
     * 每个路段，取匹配距离路段最近的摄像头。
     * 1. 取路段坐标的平均5个坐标点(头部、尾部、中间平均取三个点)
     * 2. 路网每个坐标的匹配摄像头坐标的距离：
     *     1. 若该路段的其中一个坐标点与监控的位置超过3000米，说明该监控与路段相距过远，直接跳过该监控。
     *     2. 若该路段的任何一个坐标点与监控的位置不超过3000米，继续参与后续计算
     *         1. 遍历该路段5个坐标点，若存在5个坐标点其中一个坐标与监控小于等于100米，说明该路段于监控很近，记录该路段和监控。记录格式(监控信息,距离m;监控信息,距离m;监控信息,距离m;……)。
     *         2. 遍历该路段5个坐标点，若存在5个坐标点其中一个坐标与监控大于100米，说明该监控与路段相距过远，不参与记录。
     * 3. 最后根据路段后面记录的距离，取每个路段中距离最近的摄像头坐标。
     */
    @Test
    public void test02() {
        // 定义每页的条数
        int pageSize = 1000;
        int currentPage = 1;

        // 查询所有监控信息
        LambdaQueryWrapper<DwsHzjjzdMonitoryPoint> cameraWrapper = new LambdaQueryWrapper<>();
        cameraWrapper.isNotNull(DwsHzjjzdMonitoryPoint::getIndexCode);
        cameraWrapper.isNotNull(DwsHzjjzdMonitoryPoint::getLongitude);
        cameraWrapper.isNotNull(DwsHzjjzdMonitoryPoint::getLatitude);
        List<DwsHzjjzdMonitoryPoint> cameraList = dwsHzjjzdMonitoryPointService.list(cameraWrapper);

        // 分页查询路段信息并逐页提交
        while (true) {
            // 创建分页对象
            Page<DwdTfcBasRdnetDsecroadInfo> page = new Page<>(currentPage, pageSize);
            LambdaQueryWrapper<DwdTfcBasRdnetDsecroadInfo> roadWrapper = new LambdaQueryWrapper<>();
            roadWrapper.eq(DwdTfcBasRdnetDsecroadInfo::getDataVersion, "20230930");

            // 查询当前页的数据
            IPage<DwdTfcBasRdnetDsecroadInfo> roadPage = dwdTfcBasRdnetDsecroadInfoService.page(page, roadWrapper);
            List<DwdTfcBasRdnetDsecroadInfo> roadList = roadPage.getRecords();

            // 判断是否有数据
            if (CollUtil.isEmpty(roadList)) {
                break; // 如果没有数据了，跳出循环
            }

            // 提交数据
            boolean success = submitData(roadList, cameraList);

            if (!success) {
                System.out.println("数据提交失败，分页：" + currentPage);
                break;
            }

            // 判断是否到最后一页
            if (roadPage.getPages() <= currentPage) {
                break;
            }

            // 更新到下一页
            currentPage++;
        }
    }


    private boolean submitData(List<DwdTfcBasRdnetDsecroadInfo> roadList, List<DwsHzjjzdMonitoryPoint> cameraList) {
        List<DwdTfcBasRdnetDsecroadWithCameraInfo> roadWithCameraList = new ArrayList<>();

        for (DwdTfcBasRdnetDsecroadInfo dwdTfcBasRdnetDsecroadInfo : roadList) {
            //目标对象
            DwdTfcBasRdnetDsecroadWithCameraInfo info = new DwdTfcBasRdnetDsecroadWithCameraInfo();
            BeanUtil.copyProperties(dwdTfcBasRdnetDsecroadInfo, info);

            //简化坐标，便于计算
            String newLnglatSeq = CoordinateExtractor.extractCoordinates(dwdTfcBasRdnetDsecroadInfo.getLnglatSeq());
            //遍历监控集合
            for (DwsHzjjzdMonitoryPoint dwsHzjjzdMonitoryPoint : cameraList) {
                String[] Lnglats = newLnglatSeq.split(";");
                for (String lnglat : Lnglats) {
                    String cameraLngLat = dwsHzjjzdMonitoryPoint.getLongitude() + "," + dwsHzjjzdMonitoryPoint.getLatitude();
                    double distance = DistanceCalculator.calculateDistance(lnglat, cameraLngLat);
                    //情况1：若该路段的其中一个坐标点与监控的位置超过3000米，说明该监控与路段相距过远，直接跳过该监控。
                    if (distance >= 3000) {
                        break;
                        //情况2: 遍历该路段5个坐标点，若存在5个坐标点其中一个坐标与监控小于等于100米，说明该路段于监控很近，记录该路段和监控。记录格式(监控信息,距离m;监控信息,距离m;监控信息,距离m;……)。
                    } else if (distance <= 100) {
                        if (StrUtil.isBlank(info.getIndexCodeInfo())){
                            info.setIndexCodeInfo("");
                        }
                        StringBuffer sb = new StringBuffer(info.getIndexCodeInfo());
                        sb.append(dwsHzjjzdMonitoryPoint.getIndexCode());
                        sb.append(",");
                        sb.append(dwsHzjjzdMonitoryPoint.getLongitude());
                        sb.append(",");
                        sb.append(dwsHzjjzdMonitoryPoint.getLatitude());
                        sb.append(",");
                        sb.append(distance);
                        sb.append(";");
                        info.setIndexCodeInfo(sb.toString());
                        break;
                    }
                }
            }
            roadWithCameraList.add(info);
        }
        //批量保存
        return dwdTfcBasRdnetDsecroadWithCameraInfoService.saveBatch(roadWithCameraList);
    }






}
