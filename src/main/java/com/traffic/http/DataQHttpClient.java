package com.traffic.http;

import cn.hutool.json.JSONObject;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;

/**
 * @author zhonglinjie
 * @version 1.0
 * @date 2023/2/23 11:13
 */
public interface DataQHttpClient {

    /**
     * 月维度路网流量
     * @param jsonObject
     * @return
     */
    @Post(value = "http://172.18.91.192:8099/hzsjtysj/yun_power/ywdrwll", headers = {"Content-TypeVo:application/json", "Date:Thu, 16 Feb 2023 11:15:35 GMT", "Authorization:yun_power yOobGuOcjgKS6cuq"})
    String ywdrwll(@JSONBody JSONObject jsonObject);

    /**
     * 路网月指标高峰类型
     * @param jsonObject
     * @return
     */
    @Post(value = "http://172.18.91.192:8099/hzsjtysj/yun_power/getRoadINdexPeriodByMonthTs", headers = {"Content-TypeVo:application/json", "Date:Thu, 16 Feb 2023 11:15:35 GMT", "Authorization:yun_power yOobGuOcjgKS6cuq"})
    String getRoadINdexPeriodByMonthTs(@JSONBody JSONObject jsonObject);
}
