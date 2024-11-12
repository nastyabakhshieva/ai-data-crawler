package com.petproject.ai_data_crawler.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ScanUrisRequest {

    @NotNull
    @NotEmpty
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonAlias({"toScan", "urisToScan", "urls", "urlsToScan"})
    private List<String> uris;
}
