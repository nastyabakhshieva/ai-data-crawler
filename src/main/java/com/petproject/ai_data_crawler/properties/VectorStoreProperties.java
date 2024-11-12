package com.petproject.ai_data_crawler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.ai.vectorstore")
public class VectorStoreProperties {

    private int topkRetrieveDefaultSize;
    private double similarityThreshold;
}
