package com.petproject.ai_data_crawler.config;

import com.petproject.ai_data_crawler.properties.ExecutorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(ExecutorProperties.class)
public class ExecutorsConfiguration {

    @Bean
    public ExecutorService executor(ExecutorProperties executorProperties) {
        return executorProperties.isVirtualThreadsEnabled() ?
                Executors.newVirtualThreadPerTaskExecutor() :
                Executors.newFixedThreadPool(executorProperties.getThreadPoolSize());
    }
}

