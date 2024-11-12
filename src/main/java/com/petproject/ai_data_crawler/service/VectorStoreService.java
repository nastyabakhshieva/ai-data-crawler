package com.petproject.ai_data_crawler.service;

import org.springframework.ai.document.Document;

import java.util.List;
import java.util.Map;

public interface VectorStoreService {

    void saveBulk(List<String> contents, Map<String, Object> metadata);

    @SuppressWarnings("unused")
    List<Document> similaritySearch(String query, Map<String, Object> metadata);

}
