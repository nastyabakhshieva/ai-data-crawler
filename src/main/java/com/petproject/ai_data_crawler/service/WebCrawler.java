package com.petproject.ai_data_crawler.service;

import java.util.List;

public interface WebCrawler {

    List<String> getContents(String uri);
}
