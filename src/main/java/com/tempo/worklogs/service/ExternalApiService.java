package com.tempo.worklogs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tempo.worklogs.WorklogResponse;
import com.tempo.worklogs.WorklogResponse.Result;

import reactor.core.publisher.Mono;

@Service
public class ExternalApiService {
	@Value("${external.api.base-url}")
    private String baseUrl;

    @Value("${external.api.token}")
    private String token;
    private final WebClient webClient;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    // Other properties...

    public ExternalApiService(ObjectMapper objectMapper) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.objectMapper = objectMapper;
    }

    public  void fetchAndLogWorklogsFromTempo() {
    	 WorklogResponse worklogResponse = new WorklogResponse();
       webClient.get()
            .uri("https://api.tempo.io/4/worklogs")
            .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> {
                try {
                	
                	WorklogResponse.Result result  = objectMapper.readValue(response, Result.class);
                    // Log the parsed response or perform operations as needed
                    
                	 
                      
                          System.out.println(result.toString());
                	
                	
                	 // Example: Printing the response
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
            .subscribe();
    }
}
