package com.traffic;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.aliyun.odps.simpleframework.xml.transform.InvalidFormatException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.traffic.entity.*;
import com.traffic.mapper.ChangFaMapper;
import com.traffic.mapper.DwdTfcBasRdnetDsecroadCentPointInfoMapper;
import com.traffic.mapper.JingQuMapper;
import com.traffic.mapper.LuKouMapper;
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

@SpringBootTest
class TrafficJsonDataRecordApplicationTests {
    @Resource
    private DwdTfcBasRdnetDsecroadCentPointInfoMapper mapper;
    @Resource
    private DwdTfcBasRdnetDsecroadLinkTwokmInfoService service;
    @Resource
    private DwsHzjjzdMonitoryPointService dwsHzjjzdMonitoryPointService;

    @Resource
    private LbsService lbsService;

    @Resource
    private ExcelBeanService excelBeanService;


    @Resource
    private JingQuMapper jingQuMapper;

    @Resource
    private LuKouMapper luKouMapper;

    @Resource
    private ChangFaMapper changFaMapper;

    @Test
    public void test08() throws IOException, InvalidFormatException {
        List<ChangFa> list = changFaMapper.getAll();
        for (ChangFa luKou : list) {
            String 成因 = luKou.get成因();
            String 类别 = luKou.get类别();
            String res = "";
            if (StrUtil.isNotBlank(成因)){
                if (成因.contains(",")){

                    String[] chengyin = 成因.split(",");
                    String[] leibie = 类别.split(",");
                    for (int i = 0; i < chengyin.length; i++) {
                        for (int i1 = 0; i1 < leibie.length; i1++) {
                            if (i == i1){
                                if (i + 1 == chengyin.length){
                                    res = res + leibie[i] +chengyin[i1];
                                    break;
                                }
                                res = res + leibie[i] +chengyin[i1] + ",";
                                break;
                            }
                        }
                    }

                }else {
                    res = res + 类别 + 成因;
                }
            }
            luKou.set成因(res);
        }

        for (ChangFa changFa : list) {
            System.out.println(changFa);
        }
    }

    @Test
    public void test07() throws IOException, InvalidFormatException {
        List<LuKou> list = luKouMapper.getAll();
        for (LuKou luKou : list) {
            String 成因 = luKou.get成因();
            String 类别 = luKou.get类别();
            String res = "";
            if (StrUtil.isNotBlank(成因)){
                if (成因.contains(",")){

                    String[] chengyin = 成因.split(",");
                    String[] leibie = 类别.split(",");
                    for (int i = 0; i < chengyin.length; i++) {
                        for (int i1 = 0; i1 < leibie.length; i1++) {
                            if (i == i1){
                                if (i + 1 == chengyin.length){
                                    res = res + leibie[i] +chengyin[i1];
                                    break;
                                }
                                res = res + leibie[i] +chengyin[i1] + ",";
                                break;
                            }
                        }
                    }

                }else {
                    res = res + 类别 + 成因;
                }
            }
            luKou.set成因(res);
        }

        for (LuKou luKou : list) {
            System.out.println(luKou);
        }
    }

    @Test
    public void test06() throws IOException, InvalidFormatException {
        List<JingQu> list = jingQuMapper.getAll();
        for (JingQu jingQu : list) {
            String 成因 = jingQu.get成因();
            String 类别 = jingQu.get类别();
            String res = "";
            if (StrUtil.isNotBlank(成因)){
                if (成因.contains(",")){

                    String[] chengyin = 成因.split(",");
                    String[] leibie = 类别.split(",");
                    for (int i = 0; i < chengyin.length; i++) {
                        for (int i1 = 0; i1 < leibie.length; i1++) {
                            if (i == i1){
                                if (i + 1 == chengyin.length){
                                    res = res + leibie[i] +chengyin[i1];
                                    break;
                                }
                                res = res + leibie[i] +chengyin[i1] + ",";
                                break;
                            }
                        }
                    }

                }else {
                    res = res + 类别 + 成因;
                }
            }

            jingQu.set成因(res);
        }

        for (JingQu jingQu : list) {
            System.out.println(jingQu);
        }
    }



    @Test
    public void test05() throws IOException, InvalidFormatException {
        List<List<String>> lists = MyExcelUtil.readExcelData("/Users/yin/Downloads/cam.xlsx", "春运场景 (2)");
        List<ExcelBean> excelBeans = new ArrayList<>();
        JSONArray objects = JSONUtil.parseArray(lists);


        for (Object object : objects) {
            JSONArray objects1 = JSONUtil.parseArray(object);

            if (objects1.size() == 7) {
                if (objects1.get(0).equals("一级")){
                    continue;
                }
                ExcelBean excelBean = new ExcelBean();
                excelBean.setOne((String) objects1.get(0));
                excelBean.setTwo((String) objects1.get(1));
                excelBean.setThree((String) objects1.get(2));
                excelBean.setNumber((String) objects1.get(3));
                excelBean.setName((String) objects1.get(4));
                excelBean.setCode((String) objects1.get(5));
                excelBean.setNotes((String) objects1.get(6));
                excelBeans.add(excelBean);
            }
        }

        for (int i = 0; i < excelBeans.size(); i++) {

            ExcelBean excelBean = excelBeans.get(i);
            if (ObjUtil.isEmpty(excelBean.getOne())) {
                excelBean.setOne(excelBeans.get(i - 1).getOne());
            }
            if (ObjUtil.isEmpty(excelBean.getTwo())) {
                excelBean.setTwo(excelBeans.get(i - 1).getTwo());
            }
            if (ObjUtil.isEmpty(excelBean.getThree())) {
                excelBean.setThree(excelBeans.get(i - 1).getThree());
            }
        }

        for (ExcelBean excelBean : excelBeans) {
            System.out.println(excelBean);
        }

        excelBeanService.saveBatch(excelBeans);
    }



    @Test
    public void test04(){
        List<String> res = new ArrayList<>();

        List<String> tables = lbsService.allTables();

        for (String table : tables) {
            if (table.startsWith("dws") || table.startsWith("dwd")){
                List<String> patt = null;
                try {
                    patt = lbsService.descTable(table);
                }catch (RuntimeException e){
                    continue;
                }

                for (String tab : patt) {
                    if (tab.contains("source=lbs")){
                        res.add(table);
                        break;
                    }
                }
            }
        }

        for (String re : res) {
            System.out.println(re);
        }

    }


    /**
     * 将WGS84坐标系转GCJ02坐标系
     */
    @Test
    public void test01(){
        List<DwsHzjjzdMonitoryPoint> list = dwsHzjjzdMonitoryPointService.list();
        List<DwsHzjjzdMonitoryPoint> objects = Lists.newArrayList();
        for (DwsHzjjzdMonitoryPoint dwsHzjjzdMonitoryPoint : list) {
            String longitude = dwsHzjjzdMonitoryPoint.getLongitude();//120
            String latitude = dwsHzjjzdMonitoryPoint.getLatitude();//30
            double[] doubles = CoordinateUtils.wgs84ToGcj02(Double.parseDouble(longitude),Double.parseDouble(latitude));
            dwsHzjjzdMonitoryPoint.setLongitude(String.valueOf(doubles[0]));
            dwsHzjjzdMonitoryPoint.setLatitude(String.valueOf(doubles[1]));
            objects.add(dwsHzjjzdMonitoryPoint);
        }

        for (DwsHzjjzdMonitoryPoint object : objects) {
            System.out.println(object);
        }

        dwsHzjjzdMonitoryPointService.saveOrUpdateBatch(objects);
    }














    @Test
    void contextLoads() {
        //1.查询所有道路
        LambdaQueryWrapper<DwdTfcBasRdnetDsecroadCentPointInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(DwdTfcBasRdnetDsecroadCentPointInfo::getCentPoint);
        List<DwdTfcBasRdnetDsecroadCentPointInfo> infos = mapper.selectList(wrapper);

        //2.遍历所有道路
        for (DwdTfcBasRdnetDsecroadCentPointInfo info : infos) {
            //3.构建目标对象
            DwdTfcBasRdnetDsecroadLinkTwokmInfo linkTwokmInfo = new DwdTfcBasRdnetDsecroadLinkTwokmInfo();
            //4.将属性拷贝到目标对象
            BeanUtil.copyProperties(info,linkTwokmInfo);
            //5.根据坐标去查询相关2000米的道路
            List<DwdTfcBasRdnetDsecroadCentPointInfo> list = mapper.getLinks(info.getCentPoint());
            //6.如何2000m范围内有匹配到
            if (list != null && list.size() > 0){
                //7.字符串拼接对象
                StringBuilder sb = new StringBuilder();
                //8.遍历循环匹配道路
                for (int i = 0; i < list.size(); i++) {
                    //9.获取道路
                    DwdTfcBasRdnetDsecroadCentPointInfo centPointInfo = list.get(i);
                    //9.获取道路ID
                    String dsecroadId = centPointInfo.getDsecroadId();
                    //10.拼接道路ID
                    sb.append(dsecroadId);
                    //11.如何不是最后一个，不需要拼接逗号
                    if (i != list.size() - 1){
                        sb.append(",");
                    }
                }
                //12.将拼接好的属性设置到目标对象中
                linkTwokmInfo.setLinkDsecroadId(sb.toString());
                //13.存储到数据库
                service.save(linkTwokmInfo);
            }
        }

    }

}
