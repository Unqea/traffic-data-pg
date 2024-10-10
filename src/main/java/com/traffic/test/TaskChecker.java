//package com.traffic.test;
//
//import cn.hutool.core.collection.CollUtil;
//
//import java.util.*;
//import java.util.concurrent.*;
//
//public class TaskChecker {
//    private static final int CORE_POOL_SIZE = 6;
//    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
//    private static final ExecutorService executorService = Executors.newFixedThreadPool(CORE_POOL_SIZE);
//
//    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>(); // 你的任务列表
//        String topicGroup = "first_version"; // 你的topicGroup
//        String tfcunitId = "exampleId"; // 你的tfcunitId
//        String month = "2024-05"; // 你的month
//
//        if (CollUtil.isNotEmpty(list) && "first_version".equals(topicGroup)) {
//            executorService.submit(() -> checkTasks(tfcunitId, month));
//        }
//
//        // 其他任务的提交...
//        executorService.shutdown();
//    }
//
//    private static void checkTasks(String tfcunitId, String month) {
//        final int maxRetries = 1400;
//        final long delay = 1; // 1分钟
//
//        for (int i = 0; i < maxRetries; i++) {
//            int attempt = i;
//            scheduler.schedule(() -> {
//                List<TfcdunitMonthExecInfo> tasks = tfcdunitMonthExecInfoMapper.selectRunningTask(tfcunitId + month);
//                if (CollUtil.isEmpty(tasks)) {
//                    scheduler.shutdown();
//                } else if (attempt == maxRetries - 1) {
//                    // 兜底逻辑
//                    TfcdunitMonthExecInfo one = tasks.stream().min(Comparator.comparing(TfcdunitMonthExecInfo::getId)).orElse(null);
//                    if (ObjUtil.isNotEmpty(one)) {
//                        Objects.requireNonNull(one).setGmtModified(new Date());
//                        one.setDeleteFlag(1);
//                        tfcdunitMonthExecInfoService.updateById(one);
//                    }
//                    scheduler.shutdown();
//                }
//            }, delay * (i + 1), TimeUnit.MINUTES);
//        }
//    }
//}
