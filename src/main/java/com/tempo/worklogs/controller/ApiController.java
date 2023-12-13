package com.tempo.worklogs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tempo.worklogs.service.ExternalApiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ExternalApiService externalApiService;

    public ApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/worklogs")
    public void getWorklogsFromExternalAPI() {
        externalApiService.retrieveWorklogsBasedOnUserChoice();
    }
}
