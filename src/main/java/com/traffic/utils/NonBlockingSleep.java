package com.traffic.utils;

import java.util.concurrent.*;

public class NonBlockingSleep {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void sleep(long milliseconds, Runnable callback) {
        scheduler.schedule(callback, milliseconds, TimeUnit.MILLISECONDS);
    }

    // 关闭调度器的方法（应用程序结束时调用）
    public static void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {

        System.out.println("开始了");
        NonBlockingSleep.sleep(30000,()->{});
        System.out.println("结束了");

//
//        System.out.println("Task start");
//
//        NonBlockingSleep.sleep(3000, () -> {
//            System.out.println("Task executed after 3 seconds");
//        });
//
//        System.out.println("Task end");

        // 关闭调度器（示例中强制关闭，但在实际应用程序中应该在适当的位置调用）
        NonBlockingSleep.shutdown();
    }
}
