package com.traffic.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Data
public class ExecutorConfig{

    /** 线程池中的核心线程数量,默认为1 */
    private int corePoolSize = 10;
    /** 线程池中的最大线程数量 */
    private int maxPoolSize = 12;
    /** 线程池中允许线程的空闲时间,默认为 60s */
    private int keepAliveTime = ((int) TimeUnit.SECONDS.toSeconds(30));
    /** 线程池中的队列最大数量 */
    private int queueCapacity = 1000;
    /** 线程的名称前缀 */
    private static final String THREAD_PREFIX = "thread-call-runner-%d";

    @Bean("myExecutor")
    public Executor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(THREAD_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }



}
