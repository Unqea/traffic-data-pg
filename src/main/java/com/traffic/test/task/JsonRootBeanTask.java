package com.traffic.test.task;

import com.aliyun.odps.simpleframework.xml.core.Complete;
import com.traffic.test.domain.JsonData;
import com.traffic.test.domain.JsonRootBean;
import com.traffic.test.vo.JsonRootBeanVo;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JsonRootBeanTask {
    /**
     * JsonRoot
     * @param jsonRootBean
     * @return
     */
    public List<JsonRootBeanVo> getTablePropertiesPageList(JsonRootBean jsonRootBean) {
        List<JsonRootBeanVo> result = new ArrayList<>();
        List<JsonData> data = jsonRootBean.getData();
        for (JsonData datum : data) {
            JsonRootBeanVo jsonRootBeanVo = new JsonRootBeanVo();
            jsonRootBeanVo.setName(datum.getName());
            jsonRootBeanVo.setScnCode(datum.getScnCode());
            jsonRootBeanVo.setScnParam(datum.getScnParam());
            String sdfInfo = datum.getSdfInfo();
            String[] split = sdfInfo.split(",");
            Set<String> strings = new HashSet<>();
            strings.addAll(Arrays.asList(split));
            jsonRootBeanVo.setSdfInfos(strings);
            result.add(jsonRootBeanVo);

        }

        return result;
    }
}
