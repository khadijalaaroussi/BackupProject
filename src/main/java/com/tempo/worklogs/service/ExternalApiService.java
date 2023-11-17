package com.tempo.worklogs.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tempo.worklogs.WorklogResponse;
import com.tempo.worklogs.WorklogResponse.Result;

import reactor.core.publisher.Mono;

@Service
public class ExternalApiService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    // Other properties...

    public ExternalApiService(ObjectMapper objectMapper) {
        this.webClient = WebClient.builder().baseUrl("https://api.tempo.io").build();
        this.objectMapper = objectMapper;
    }

    public  void fetchAndLogWorklogsFromTempo() {
    	
       webClient.get()
            .uri("/4/worklogs")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " +"Yf8asoccr9eqtEbXJOaYgtQNJwzzHK-us")
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> {
                try {
                	Result result = objectMapper.readValue(response, Result.class);
                    // Log the parsed response or perform operations as needed
                    System.out.println(result); // Example: Printing the response
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
            .subscribe();
    }
}
