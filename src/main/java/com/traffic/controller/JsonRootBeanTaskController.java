package com.traffic.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.traffic.common.ApiResult;
import com.traffic.entity.*;
import com.traffic.mapper.ChangFaMapper;
import com.traffic.mapper.DwsChangFaMMapper;
import com.traffic.mapper.JingQuMapper;
import com.traffic.mapper.LuKouMapper;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.test.domain.JsonRootBean;
import com.traffic.test.task.JsonRootBeanTask;
import com.traffic.test.vo.JsonRootBeanVo;
import com.traffic.vo.TablePropertiesQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/json")
@RestController
public class JsonRootBeanTaskController {

    @Resource
    private JsonRootBeanTask jsonRootBeanTask;

    @Resource
    private JingQuMapper jingQuMapper;

    @Resource
    private LuKouMapper luKouMapper;

    @Resource
    private ChangFaMapper changFaMapper;

    @Resource
    private DwsChangFaMMapper dwsChangFaMMapper;

    /**
     * 获取
     */
    @GetMapping("yyy")
    @ApiOperation(value = "yyy", notes = "yyy", response = TablePropertiesQueryVo.class)
    public ApiResult<String> yyy(String yyy) throws Exception {
        return ApiResult.ok("yyy");
    }

    /**
     * 获取
     */
    @GetMapping("getYongdu")
    @ApiOperation(value = "getYongdu", notes = "getYongdu", response = TablePropertiesQueryVo.class)
    public ApiResult<DwsChangFaMVo> getYongdu() throws Exception {
        List<DwsChangFaM> list = dwsChangFaMMapper.getAll();
        List<DwsChangFaMVo> res = list.stream().map(
                item -> {
                    DwsChangFaMVo dwsChangFaMVo = new DwsChangFaMVo();
                    BeanUtil.copyProperties(item,dwsChangFaMVo);
                    String jamPeriod = dwsChangFaMMapper.getPeriod(item.get道路id());
                    dwsChangFaMVo.set成因(jamPeriod);
                    StringBuilder factorType = new StringBuilder();
                    String reason = item.get成因();
                    if (StrUtil.isBlank(reason)){
                        return dwsChangFaMVo;
                    }
                    String[] split = reason.split(",");
                    for (int i = 0; i < split.length; i++) {
                        String s = split[i];
                        String msg = s.split("】")[1];
                        //1常规性拥堵2结构性拥堵3秩序性拥堵
                        String type = dwsChangFaMMapper.getType(msg);
                        if (i + 1 == split.length){
                            factorType.append(type);
                        }else {
                            factorType.append(type).append(",");
                        }
                    }
                    dwsChangFaMVo.set拥堵类型(factorType.toString());
                    return dwsChangFaMVo;
                }
        ).collect(Collectors.toList());

        return ApiResult.ok(res);
    }

    /**
     * 获取
     */
    @GetMapping("getChangFa")
    @ApiOperation(value = "getChangFa", notes = "getChangFa", response = TablePropertiesQueryVo.class)
    public ApiResult<ChangFa> getChangFa() throws Exception {
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

        return ApiResult.ok(list);
    }


    /**
     * JsonRoot
     */
    @PostMapping("/JsonRoot")
    @ApiOperation(value = "JsonRoot", notes = "JsonRoot", response = TablePropertiesQueryVo.class)
    public ApiResult<List<JsonRootBeanVo>> getTablePropertiesPageList(@RequestBody JsonRootBean jsonRootBean) throws Exception {
        List<JsonRootBeanVo> list = jsonRootBeanTask.getTablePropertiesPageList(jsonRootBean);
        return ApiResult.ok(list);
    }


    /**
     * 获取
     */
    @GetMapping("getLuKou")
    @ApiOperation(value = "getLuKou", notes = "getLuKou", response = TablePropertiesQueryVo.class)
    public ApiResult<LuKou> getLuKou() throws Exception {
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

        return  ApiResult.ok(list);
    }


    /**
     * 获取
     */
    @GetMapping("getJingQu")
    @ApiOperation(value = "getJingQu", notes = "getJingQu", response = TablePropertiesQueryVo.class)
    public ApiResult<JingQu> get() throws Exception {
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

        return   ApiResult.ok(list);


    }

}
