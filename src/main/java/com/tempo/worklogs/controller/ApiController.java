package com.tempo.worklogs.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tempo.worklogs.DomainTeam.Root;
import com.tempo.worklogs.service.ExternalApiService;
import com.tempo.worklogs.service.TeamsService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ExternalApiService externalApiService;
    private final TeamsService team;

    public ApiController(ExternalApiService externalApiService,TeamsService team) {
        this.externalApiService = externalApiService;
        this.team=team;
    }

   /* @GetMapping("/worklogs")
    public void getWorklogsFromExternalAPI() throws IllegalArgumentException, IllegalAccessException, IOException {
        externalApiService.retrieveWorklogsBasedOnUserChoice();
    }
    @GetMapping("/teams")
    public void getTeams() {
         team.generateExcelFromTempoAPI();
    }*/
}
