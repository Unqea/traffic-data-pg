package com.traffic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.odps.simpleframework.xml.transform.InvalidFormatException;
import com.traffic.entity.TfcdlineResult;
import com.traffic.entity.TfcdlineResultTraffic;
import com.traffic.http.DataQHttpClient;
import com.traffic.mapper.*;
import com.traffic.service.TfcdlineResultService;
import com.traffic.service.TfcdlineResultTrafficService;
import com.traffic.vo.RoadINdexPeriodByMonthTs;
import com.traffic.vo.RoadINdexPeriodByMonthTsVO;
import com.traffic.vo.Ywdrwll;
import com.traffic.vo.YwdrwllVo;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TrafficTest {

    @Resource
    private TfcdlineResultService service;

    @Resource
    private DataQHttpClient dataQHttpClient;


    @Resource
    private TfcdlineResultTrafficService resultTrafficService;


    @Test
    public void test10() throws IOException, InvalidFormatException, InterruptedException {
        List<TfcdlineResultTraffic> list = resultTrafficService.list();
        for (TfcdlineResultTraffic tfcdlineResultTraffic : list) {
            String jamPeriod = tfcdlineResultTraffic.getJamPeriod();
            int i = calculateMonTrafficJam(jamPeriod,420,540);
            int j = calculateMonTrafficJam(jamPeriod,990,1110);
            tfcdlineResultTraffic.setMonJamDur(String.valueOf(i));
            tfcdlineResultTraffic.setEveJamDur(String.valueOf(j));
            //System.out.println(tfcdlineResultTraffic);
            resultTrafficService.updateById(tfcdlineResultTraffic);
        }
    }

    public static int calculateMonTrafficJam(String jamPeriod,int provide_start, int provide_end) {
        int res = 0;
        String[] jamSplit = jamPeriod.split(",");
        for (String per : jamSplit) {
            int start = Integer.parseInt(per.split("-")[0].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[0].split(":")[1]);
            int end = Integer.parseInt(per.split("-")[1].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[1].split(":")[1]);
            //开始和结束都在早高峰
            if ((start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - start);
            }
            //开始时间在，结束时间不在
            if ((start >= provide_start && start <= provide_end) && !(end >= provide_start && end <= provide_end)){
                res = res + (provide_end - start);
            }
            //开始时间不在，结束时间在
            if (!(start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - provide_start);
            }
            //开始和结束横跨早高峰
            if (start <= provide_start && end >= provide_end){
                res = res + (provide_end - provide_start);
            }
        }
        return   res == 0 ? res : (res == 120 ? res : res + 1);
    }


    public static int calculateJamDur(String jamPeriod,int provide_start, int provide_end) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(420,540);
        map.put(990,1110);

        int res = 0;
        String[] jamSplit = jamPeriod.split(",");
        for (String per : jamSplit) {
            int start = Integer.parseInt(per.split("-")[0].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[0].split(":")[1]);
            int end = Integer.parseInt(per.split("-")[1].split(":")[0]) * 60 + Integer.parseInt(per.split("-")[1].split(":")[1]);
            //开始和结束都在早高峰
            if ((start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - start);
            }
            //开始时间在，结束时间不在
            if ((start >= provide_start && start <= provide_end) && !(end >= provide_start && end <= provide_end)){
                res = res + (provide_end - start);
            }
            //开始时间不在，结束时间在
            if (!(start >= provide_start && start <= provide_end) &&  (end >= provide_start && end <= provide_end)){
                res = res + (end - provide_start);
            }
            //开始和结束横跨早高峰
            if (start <= provide_start && end >= provide_end){
                res = res + (provide_end - provide_start);
            }
        }
        return res == 0 ? res : (res == 121 ? 120 : res);
    }







    /**
     * 计算分钟数
     * @param time
     * @return
     */
    public static int convertToMinutes(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        return hour * 60 + minute;
    }




    @Test
    public void test09() throws IOException, InvalidFormatException, InterruptedException {

        List<TfcdlineResult> list = service.selectAllLine(0);


        for (TfcdlineResult tfcdlineResult : list) {
            TfcdlineResultTraffic result = new TfcdlineResultTraffic();
            result.setTfcdlineId(tfcdlineResult.getTfcdlineId());
            result.setTfcdlineName(tfcdlineResult.getTfcdlineName());
            result.setRoadType(tfcdlineResult.getRoadType());
            result.setJamDur(tfcdlineResult.getJamDur());
            result.setJamPeriod(tfcdlineResult.getJamPeriod());
            result.setAvgJamDelayIndex(tfcdlineResult.getAvgJamDelayIndex());
            String roadFlow = getRoadFlow(0, tfcdlineResult.getTfcdlineId());
            result.setFlow(roadFlow);
            result.setAvgSpeed(tfcdlineResult.getAvgSpeed());

            //mon
            RoadINdexPeriodByMonthTs roadIndex = getRoadIndex(1,tfcdlineResult.getTfcdlineId());
            result.setMonJamIndex(roadIndex.getAvg_jam_delay_index());
            result.setMonSpeed(roadIndex.getAvg_speed());
            String monJamDur = service.selectOneLine(1,tfcdlineResult.getTfcdlineId());
            result.setMonJamDur(monJamDur);
            String roadFlow1 = getRoadFlow(1, tfcdlineResult.getTfcdlineId());
            result.setMonFlow(roadFlow1);

            //eve
            RoadINdexPeriodByMonthTs roadIndexEve = getRoadIndex(2,tfcdlineResult.getTfcdlineId());
            result.setEveJamIndex(roadIndexEve.getAvg_jam_delay_index());
            result.setEveSpeed(roadIndexEve.getAvg_speed());
            String monJamDurEve = service.selectOneLine(2,tfcdlineResult.getTfcdlineId());
            result.setEveJamDur(monJamDurEve);
            String roadFlow2 = getRoadFlow(2,tfcdlineResult.getTfcdlineId());
            result.setEveFlow(roadFlow2);



            resultTrafficService.save(result);
            Thread.sleep(5000);
        }



    }

    private String getRoadFlow(Integer period_type,String tfcdline_id ){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("period_type",period_type);
            jsonObject.set("date_type_no",1);
            jsonObject.set("tfcdline_id",tfcdline_id);
            String ywdrwll = dataQHttpClient.ywdrwll(jsonObject);
            if (StrUtil.isBlank(ywdrwll)){
                return "0";
            }
            YwdrwllVo ywdrwllVo = JSONUtil.toBean(ywdrwll, YwdrwllVo.class);
            if (ObjUtil.isEmpty(ywdrwllVo)){
                return "0";
            }
            List<Ywdrwll> data = ywdrwllVo.getData();
            if (CollUtil.isEmpty(data)){
                return "0";
            }
            Ywdrwll ywdrwll1 = data.get(0);
            if (ObjUtil.isEmpty(ywdrwll1)){
                return "0";
            }
            String flow = ywdrwll1.getFlow();
            if (StrUtil.isBlank(flow)){
                return "0";
            }
            return flow;
        }catch (Exception e){
            return "0";
        }

    }

    private RoadINdexPeriodByMonthTs getRoadIndex(Integer period_type,String tfcdline_id ){
        RoadINdexPeriodByMonthTs roadINdexPeriodByMonthTs = new RoadINdexPeriodByMonthTs();
        roadINdexPeriodByMonthTs.setAvg_speed("0");
        roadINdexPeriodByMonthTs.setAvg_jam_delay_index("0");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("period_type",period_type);
            jsonObject.set("tfcdline_id",tfcdline_id);
            String roadIndex = dataQHttpClient.getRoadINdexPeriodByMonthTs(jsonObject);
            RoadINdexPeriodByMonthTsVO roadINdexPeriodByMonthTsVO = JSONUtil.toBean(roadIndex, RoadINdexPeriodByMonthTsVO.class);
            if (ObjUtil.isEmpty(roadINdexPeriodByMonthTsVO)){
                return roadINdexPeriodByMonthTs;
            }

            List<RoadINdexPeriodByMonthTs> data = roadINdexPeriodByMonthTsVO.getData();
            if (CollUtil.isEmpty(data)){
                return roadINdexPeriodByMonthTs;
            }
            RoadINdexPeriodByMonthTs res = data.get(0);
            if (ObjUtil.isEmpty(res)){
                return roadINdexPeriodByMonthTs;
            }
            return res;
        }catch (Exception e){
            return roadINdexPeriodByMonthTs;
        }



        //return  JSONUtil.toBean(roadIndex, RoadINdexPeriodByMonthTsVO.class).getData().get(0);
    }




}
