package com.traffic.utils;

import cn.hutool.json.JSONObject;
import com.alicloud.openservices.tablestore.SyncClient;

import java.util.HashMap;

/**
 * 删除ots数据
 */
public class OtsUtil {

    private static SyncClient getSyncClient() {
        String endPoint = "http://10.54.37.35";
        String accessKeyId = "No5ZllRp4hYUq1IF";
        String accessKeySecret ="werEhhLBysrj19gD77gCmMiSWH28sE";
        final String instanceName = "hzjtysots";
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        return client;
    }

    public static void main(String[] args) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("dt","20240413");



    }
}
