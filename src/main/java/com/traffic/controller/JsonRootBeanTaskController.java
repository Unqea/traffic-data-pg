package com.traffic.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.traffic.common.ApiResult;
import com.traffic.entity.TableProperties;
import com.traffic.param.TablePropertiesQueryParam;
import com.traffic.test.domain.JsonRootBean;
import com.traffic.test.task.JsonRootBeanTask;
import com.traffic.test.vo.JsonRootBeanVo;
import com.traffic.vo.TablePropertiesQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/json")
@RestController
public class JsonRootBeanTaskController {

    @Resource
    private JsonRootBeanTask jsonRootBeanTask;
    /**
     * JsonRoot
     */
    @PostMapping("/JsonRoot")
    @ApiOperation(value = "JsonRoot", notes = "JsonRoot", response = TablePropertiesQueryVo.class)
    public ApiResult<List<JsonRootBeanVo>> getTablePropertiesPageList(@RequestBody JsonRootBean jsonRootBean) throws Exception {
        List<JsonRootBeanVo> list = jsonRootBeanTask.getTablePropertiesPageList(jsonRootBean);
        return ApiResult.ok(list);
    }

}
