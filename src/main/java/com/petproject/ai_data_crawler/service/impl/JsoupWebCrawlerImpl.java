package com.petproject.ai_data_crawler.service.impl;

import com.petproject.ai_data_crawler.service.WebCrawler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class JsoupWebCrawlerImpl implements WebCrawler {

    @Override
    @SneakyThrows
    public List<String> getContents(String uri) {
        Document document = Jsoup.connect(uri).get();
        List<String> contents = Arrays.stream(document.text().split("\\. "))
                .map(String::trim)
                .toList();
        log.info(">>> Contents for URI {} successfully loaded! Total size: {}", uri, contents.size());
        return contents;
    }
}
