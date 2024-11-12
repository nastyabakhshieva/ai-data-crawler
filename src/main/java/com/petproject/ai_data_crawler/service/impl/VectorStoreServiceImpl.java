package com.petproject.ai_data_crawler.service.impl;

import com.petproject.ai_data_crawler.properties.VectorStoreProperties;
import com.petproject.ai_data_crawler.service.VectorStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(VectorStoreProperties.class)
public class VectorStoreServiceImpl implements VectorStoreService {

    private final VectorStore vectorStore;
    private final VectorStoreProperties vectorStoreProperties;

    @Override
    public void saveBulk(List<String> contents, Map<String, Object> metadata) {
        List<Document> documents = contents.stream()
                .map(content -> toDocument(content, metadata))
                .toList();
        vectorStore.add(documents);
        log.info(">>> Contents of size {} successfully saved in vector store!", documents.size());
    }

    @Override
    public List<Document> similaritySearch(String query, Map<String, Object> metadata) {
        SearchRequest searchRequest = SearchRequest.query(query)
                .withTopK(vectorStoreProperties.getTopkRetrieveDefaultSize())
                .withSimilarityThreshold(vectorStoreProperties.getSimilarityThreshold())
                .withFilterExpression(toFilterExpression(metadata));
        return vectorStore.similaritySearch(searchRequest);
    }

    private Filter.Expression toFilterExpression(Map<String, Object> metadata) {
        FilterExpressionBuilder b = new FilterExpressionBuilder();
        return metadata.entrySet().stream()
                .map(entry -> b.eq(entry.getKey(), entry.getValue()))
                .reduce(b::and)
                .map(FilterExpressionBuilder.Op::build)
                .orElseThrow();
    }

    private Document toDocument(String content, Map<String, Object> metadata) {
        return new Document(content, metadata);
    }
}
