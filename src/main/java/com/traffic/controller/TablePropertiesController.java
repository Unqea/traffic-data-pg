package com.traffic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.traffic.common.ApiResult;
import com.traffic.entity.TableProperties;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.service.TablePropertiesService;
import com.traffic.vo.TablePropertiesQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *  前端控制器
 * </pre>
 *
 * @author Yinhenan
 * @since 2023-06-28
 */
@Slf4j
@RestController
@RequestMapping("/tableProperties")
@Api(tags = "API")
public class TablePropertiesController  {

    @Autowired
    private TablePropertiesService tablePropertiesService;

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", notes = "分页列表", response = TablePropertiesQueryVo.class)
    public ApiResult<IPage<TableProperties>> getTablePropertiesPageList(@RequestBody TablePropertiesQueryParam tablePropertiesQueryParam) throws Exception {
        IPage<TableProperties> paging = tablePropertiesService.getTablePropertiesPageList(tablePropertiesQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看", notes = "查看", response = TablePropertiesQueryVo.class)
    public ApiResult<TablePropertiesQueryVo> getTableProperties(@PathVariable("id") Long id) throws Exception {
        TablePropertiesQueryVo tablePropertiesQueryVo = tablePropertiesService.getTablePropertiesById(id);
        return ApiResult.ok(tablePropertiesQueryVo);
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加", notes = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addTableProperties( @RequestBody TableProperties tableProperties) throws Exception {
        boolean flag = tablePropertiesService.saveTableProperties(tableProperties);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateTableProperties( @RequestBody TableProperties tableProperties) throws Exception {
        boolean flag = tablePropertiesService.updateTableProperties(tableProperties);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除", notes = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteTableProperties(@PathVariable("id") Long id) throws Exception {
        boolean flag = tablePropertiesService.deleteTableProperties(id);
        return ApiResult.result(flag);
    }





}

