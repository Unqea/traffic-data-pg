package com.traffic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import com.traffic.http.DataQHttpClient;
import com.traffic.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class OtsUtilTest {

    @Resource
    private DataQHttpClient dataQHttpClient;

    //删除30mi天
    @Test
    public void test01() {
        String endPoint = "http://10.54.37.35";
        String accessKeyId = "No5ZllRp4hYUq1IF";
        String accessKeySecret ="werEhhLBysrj19gD77gCmMiSWH28sE";
        final String instanceName = "hzjtysots";
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        //20240401 — 20240508
        List<String> dateRange = getDateRange("20240401","20240508");
        for (String dt : dateRange) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("dt",dt);
            //每次删5000，如果某次数量少于5000，说明最后一次，执行完结束任务
            while (true){
                String result = dataQHttpClient.delete_ots_service_tfc_state_tfcdline2tfcunit_index_d(jsonObject);
                RoadHalfHourRes roadHalfHourRes = JSONUtil.toBean(result, RoadHalfHourRes.class);
                List<RoadHalfHour> data = roadHalfHourRes.getData();
                if (CollUtil.isNotEmpty(data)){
                    for (RoadHalfHour datum : data) {
                        myDeleteRow30mi_d(client,datum.getDt(),datum.getMonth(),datum.getDay_hour(),datum.getRoad_level(),datum.getTfcunit_id(),datum.getTfcdline_id(),Long.valueOf(datum.getPeriod_type()),Long.valueOf(datum.getDate_type_no()));
                    }
                }
                if (data.size() <= 5000){
                    break;
                }
            }
        }
        client.shutdown();
    }


    //删除高峰天
    @Test
    public void test02() {
        String endPoint = "http://10.54.37.35";
        String accessKeyId = "No5ZllRp4hYUq1IF";
        String accessKeySecret ="werEhhLBysrj19gD77gCmMiSWH28sE";
        final String instanceName = "hzjtysots";
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        //20240401 — 20240508
        List<String> dateRange = getDateRange("20240401","20240508");
        for (String dt : dateRange) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("dt",dt);
            while (true){
                String result = dataQHttpClient.delete_ots_service_tfc_state_tfcdline_period_type_index_d(jsonObject);
                RoadPeriodTypeRes roadPeriodTypeRes = JSONUtil.toBean(result, RoadPeriodTypeRes.class);
                List<RoadPeriodType> data = roadPeriodTypeRes.getData();
                if (CollUtil.isNotEmpty(data)){
                    for (RoadPeriodType datum : data) {
                        myDeleteRowPeriod_d(client,datum.getDt(),Long.valueOf(datum.getPeriod_type()),datum.getRoad_level(),datum.getTfcunit_id(),Long.valueOf(datum.getDate_type_no()),datum.getTfcdline_id());
                    }
                }
                if (data.size() <= 5000){
                    break;
                }
            }
        }
        client.shutdown();
    }



    //删除30mi月
    @Test
    public void test03() {
        String endPoint = "http://10.54.37.35";
        String accessKeyId = "No5ZllRp4hYUq1IF";
        String accessKeySecret ="werEhhLBysrj19gD77gCmMiSWH28sE";
        final String instanceName = "hzjtysots";
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("month","202404");
        while (true){
            String result = dataQHttpClient.delete_ots_service_tfc_state_tfcdline2tfcunit_index_m(jsonObject);
            RoadHalfHourMonthRes roadHalfHourMonthRes = JSONUtil.toBean(result, RoadHalfHourMonthRes.class);
            List<RoadHalfHourMonth> data = roadHalfHourMonthRes.getData();
            if (CollUtil.isNotEmpty(data)){
                for (RoadHalfHourMonth datum : data) {
                    myDeleteRow30mi_m(client,datum.getMonth(),datum.getDay_hour(),datum.getRoad_level(),datum.getTfcunit_id(),datum.getTfcdline_id(),Long.valueOf(datum.getPeriod_type()),Long.valueOf(datum.getDate_type_no()));
                }
            }
            if (data.size() <= 5000){
                break;
            }
        }

        client.shutdown();
    }


    //删除高峰月
    @Test
    public void test04() {
        String endPoint = "http://10.54.37.35";
        String accessKeyId = "No5ZllRp4hYUq1IF";
        String accessKeySecret ="werEhhLBysrj19gD77gCmMiSWH28sE";
        final String instanceName = "hzjtysots";
        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("month","202404");
        while (true){
            String result = dataQHttpClient.delete_ots_service_tfc_state_tfcdline_period_type_index_m(jsonObject);
            RoadPeriodTypMonthRes roadPeriodTypMonthRes = JSONUtil.toBean(result, RoadPeriodTypMonthRes.class);
            List<RoadPeriodTypeMonth> data = roadPeriodTypMonthRes.getData();
            if (CollUtil.isNotEmpty(data)){
                for (RoadPeriodTypeMonth datum : data) {
                    myDeleteRowPeriod_m(client,datum.getMonth(),Long.valueOf(datum.getPeriod_type()),datum.getRoad_level(),datum.getTfcunit_id(),Long.valueOf(datum.getDate_type_no()),datum.getTfcdline_id());
                }
            }
            if (data.size() <= 5000){
                break;
            }
        }

        client.shutdown();
    }


    /**
     * 删除高峰天
     * 根据主键删除数据
     */
    private static void myDeleteRowPeriod_m(SyncClient client, String month,Long period_type, String road_level,String tfcunit_id, Long date_type_no ,String tfcdline_id) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        //构造主键。
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("month", PrimaryKeyValue.fromString(month));
        primaryKeyBuilder.addPrimaryKeyColumn("road_level", PrimaryKeyValue.fromString(road_level));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcunit_id", PrimaryKeyValue.fromString(tfcunit_id));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcdline_id", PrimaryKeyValue.fromString(tfcdline_id));
        primaryKeyBuilder.addPrimaryKeyColumn("period_type", PrimaryKeyValue.fromLong(period_type));
        primaryKeyBuilder.addPrimaryKeyColumn("date_type_no", PrimaryKeyValue.fromLong(date_type_no));
        PrimaryKey primaryKey = primaryKeyBuilder.build();
        //设置数据表名称。
        RowDeleteChange rowDeleteChange = new RowDeleteChange("service_tfc_state_tfcdline_period_type_index_m", primaryKey);
        //添加到batch操作中。
        batchWriteRowRequest.addRowChange(rowDeleteChange);
        BatchWriteRowResponse response = client.batchWriteRow(batchWriteRowRequest);
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                System.out.println("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                System.out.println("失败原因：" + rowResult.getError());
            }
        }
    }




    /**
     * 删除30mi月
     * 根据主键删除数据
     */
    private static void myDeleteRow30mi_m(SyncClient client, String month,String day_hour, String road_level,String tfcunit_id,String tfcdline_id,Long period_type,Long date_type_no) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        //构造主键。
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("month", PrimaryKeyValue.fromString(month));
        primaryKeyBuilder.addPrimaryKeyColumn("day_hour", PrimaryKeyValue.fromString(day_hour));
        primaryKeyBuilder.addPrimaryKeyColumn("road_level", PrimaryKeyValue.fromString(road_level));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcunit_id", PrimaryKeyValue.fromString(tfcunit_id));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcdline_id", PrimaryKeyValue.fromString(tfcdline_id));
        primaryKeyBuilder.addPrimaryKeyColumn("period_type", PrimaryKeyValue.fromLong(period_type));
        primaryKeyBuilder.addPrimaryKeyColumn("date_type_no", PrimaryKeyValue.fromLong(date_type_no));
        PrimaryKey primaryKey = primaryKeyBuilder.build();
        //设置数据表名称。
        RowDeleteChange rowDeleteChange = new RowDeleteChange("service_tfc_state_tfcdline2tfcunit_index_m", primaryKey);
        //添加到batch操作中。
        batchWriteRowRequest.addRowChange(rowDeleteChange);
        BatchWriteRowResponse response = client.batchWriteRow(batchWriteRowRequest);
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                System.out.println("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                System.out.println("失败原因：" + rowResult.getError());
            }
        }
    }






    /**
     * 删除高峰天
     * 根据主键删除数据
     */
    private static void myDeleteRowPeriod_d(SyncClient client, String dt,Long period_type, String road_level,String tfcunit_id, Long date_type_no ,String tfcdline_id) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        //构造主键。
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("dt", PrimaryKeyValue.fromString(dt));
        primaryKeyBuilder.addPrimaryKeyColumn("road_level", PrimaryKeyValue.fromString(road_level));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcunit_id", PrimaryKeyValue.fromString(tfcunit_id));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcdline_id", PrimaryKeyValue.fromString(tfcdline_id));
        primaryKeyBuilder.addPrimaryKeyColumn("period_type", PrimaryKeyValue.fromLong(period_type));
        primaryKeyBuilder.addPrimaryKeyColumn("date_type_no", PrimaryKeyValue.fromLong(date_type_no));
        PrimaryKey primaryKey = primaryKeyBuilder.build();
        //设置数据表名称。
        RowDeleteChange rowDeleteChange = new RowDeleteChange("service_tfc_state_tfcdline_period_type_index_d", primaryKey);
        //添加到batch操作中。
        batchWriteRowRequest.addRowChange(rowDeleteChange);
        BatchWriteRowResponse response = client.batchWriteRow(batchWriteRowRequest);
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                System.out.println("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                System.out.println("失败原因：" + rowResult.getError());
            }
        }
    }

    /**
     * 删除30mi天
     * 根据主键删除数据
     */
    private static void myDeleteRow30mi_d(SyncClient client, String dt,String month,String day_hour, String road_level,String tfcunit_id,String tfcdline_id,Long period_type,Long date_type_no) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        //构造主键。
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("dt", PrimaryKeyValue.fromString(dt));
        primaryKeyBuilder.addPrimaryKeyColumn("month", PrimaryKeyValue.fromString(month));
        primaryKeyBuilder.addPrimaryKeyColumn("day_hour", PrimaryKeyValue.fromString(day_hour));
        primaryKeyBuilder.addPrimaryKeyColumn("road_level", PrimaryKeyValue.fromString(road_level));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcunit_id", PrimaryKeyValue.fromString(tfcunit_id));
        primaryKeyBuilder.addPrimaryKeyColumn("tfcdline_id", PrimaryKeyValue.fromString(tfcdline_id));
        primaryKeyBuilder.addPrimaryKeyColumn("period_type", PrimaryKeyValue.fromLong(period_type));
        primaryKeyBuilder.addPrimaryKeyColumn("date_type_no", PrimaryKeyValue.fromLong(date_type_no));
        PrimaryKey primaryKey = primaryKeyBuilder.build();
        //设置数据表名称。
        RowDeleteChange rowDeleteChange = new RowDeleteChange("service_tfc_state_tfcdline2tfcunit_index_d", primaryKey);
        //添加到batch操作中。
        batchWriteRowRequest.addRowChange(rowDeleteChange);
        BatchWriteRowResponse response = client.batchWriteRow(batchWriteRowRequest);
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                System.out.println("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                System.out.println("失败原因：" + rowResult.getError());
            }
        }
    }



    /**
     * 获取两个日期直接的天数
     */
    public static List<String> getDateRange(String startDateStr, String endDateStr) {
        List<String> dateRange = new ArrayList<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            while (!calendar.getTime().after(endDate)) {
                dateRange.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateRange;
    }






}
