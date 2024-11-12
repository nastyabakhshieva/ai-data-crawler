package com.petproject.ai_data_crawler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("server.executor")
public class ExecutorProperties {

    private boolean virtualThreadsEnabled;
    private int threadPoolSize;
}
