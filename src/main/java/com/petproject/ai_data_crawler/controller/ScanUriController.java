package com.petproject.ai_data_crawler.controller;

import com.petproject.ai_data_crawler.model.ScanUrisRequest;
import com.petproject.ai_data_crawler.service.ScanUriService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/webcrawler")
@RequiredArgsConstructor
public class ScanUriController {

    private final ScanUriService scanUriService;

    @PostMapping("/scan/{clientId}")
    public ResponseEntity<Void> scan(@PathVariable("clientId") @NotNull String clientId, @RequestBody ScanUrisRequest request) {
        scanUriService.scan(clientId, request.getUris());
        return ResponseEntity.ok().build();
    }
}
