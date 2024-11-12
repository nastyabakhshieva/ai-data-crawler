package com.petproject.ai_data_crawler.service;

import java.util.List;

public interface ScanUriService {

    void scan(String clientId, List<String> uris);
}
