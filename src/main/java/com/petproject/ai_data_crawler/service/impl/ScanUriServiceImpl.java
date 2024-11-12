package com.petproject.ai_data_crawler.service.impl;

import com.petproject.ai_data_crawler.constants.UriScannerConstants;
import com.petproject.ai_data_crawler.service.ScanUriService;
import com.petproject.ai_data_crawler.service.VectorStoreService;
import com.petproject.ai_data_crawler.service.WebCrawler;
import com.petproject.ai_data_crawler.utils.ThrowableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import static java.util.Collections.singletonMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScanUriServiceImpl implements ScanUriService {

    private final WebCrawler webCrawler;
    private final VectorStoreService vectorStoreService;
    private final ExecutorService executor;

    @Override
    public void scan(String clientId, List<String> uris) {
        uris.stream()
                .map(this::scan)
                .reduce(CompletableFuture.completedFuture(Collections.emptyList()),
                        (l1, l2) -> l1.thenCombine(l2, ListUtils::union))
                .thenAccept(contents -> vectorStoreService.saveBulk(contents, singletonMap(UriScannerConstants.CLIENT_ID, clientId)))
                .thenAccept(ignore -> log.info(">>> Contents for clientId '{}' successfully loaded & saved in vector store!", clientId))
                .exceptionally(ex -> ThrowableUtils.handle(log, "Exception thrown while trying to scan URIs for client: {}", ex, clientId));
    }

    private CompletableFuture<List<String>> scan(String uri) {
        return CompletableFuture.supplyAsync(() -> webCrawler.getContents(uri), executor);
    }
}
