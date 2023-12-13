package com.tempo.worklogs.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tempo.worklogs.domain.Result;
import com.tempo.worklogs.domain.WorklogResponse;

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
    
    
    public void retrieveWorklogsBasedOnUserChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voulez-vous récupérer tous les worklogs ? (Oui/Non)");
        String userChoice = scanner.nextLine();

        if ("Oui".equalsIgnoreCase(userChoice)) {
            getAllWorklogs();
        } else if ("Non".equalsIgnoreCase(userChoice)) {
            retrieveMonthlyWorklogs();
        } else {
            System.out.println("Réponse non valide. Veuillez saisir 'Oui' ou 'Non'.");
        }
    }
    public void retrieveMonthlyWorklogs() {
        // Récupération du mois courant
        YearMonth currentMonth = YearMonth.now();
        LocalDate startOfMonth = currentMonth.atDay(1);
        LocalDate endOfMonth = currentMonth.atEndOfMonth();

        // Utilisation de la méthode getWorklogsForDateRange() pour récupérer les worklogs du mois courant
        List<Result> monthlyWorklogs = getWorklogsForDateRange(startOfMonth, endOfMonth);

        try {
            File outputFile = new File("monthly_worklogs.json");
            objectMapper.writeValue(outputFile, monthlyWorklogs);
            System.out.println("Monthly worklogs saved to monthly_worklogs.json");
            System.out.println("Number of worklogs for this month: " + monthlyWorklogs.size());
        } catch (IOException e) {
            System.err.println("Error occurred while writing monthly worklogs to file: " + e.getMessage());
        }
    }
    public void getAllWorklogs() {
        List<Result> allWorklogs = new ArrayList<>();
        int limit = 5000;
        int offset = 0;
        int worklogCount = 0;

        RestTemplate restTemplate = new RestTemplate();

        while (true) {
            String url = "https://api.tempo.io/4/worklogs?limit=" + limit + "&offset=" + offset;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<WorklogResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                WorklogResponse.class
            );

            WorklogResponse worklogResponse = response.getBody();
            if (worklogResponse != null && worklogResponse.getResults() != null && !worklogResponse.getResults().isEmpty()) {
                allWorklogs.addAll(worklogResponse.getResults());
                offset += limit;
                worklogCount += worklogResponse.getResults().size();
                
                
            } else {
                break; // Break the loop if no more worklogs are available
            }
        }

        try {
            File outputFile = new File("worklogs.json");
            objectMapper.writeValue(outputFile, allWorklogs);
            System.out.println("Worklogs saved to worklogs.json");
            System.out.println("Total number of worklogs: " + worklogCount); 
        } catch (IOException e) {
            System.err.println("Error occurred while writing worklogs to file: " + e.getMessage());
        }
    }



public List<Result> getWorklogsForDateRange(LocalDate startDate, LocalDate endDate) {
    List<Result> worklogsForDateRange = new ArrayList<>();
    int limit = 5000;
    int offset = 0;

    RestTemplate restTemplate = new RestTemplate();

    while (true) {
        String url = "https://api.tempo.io/4/worklogs?limit=" + limit + "&offset=" + offset;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<WorklogResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            WorklogResponse.class
        );

        WorklogResponse worklogResponse = response.getBody();
        if (worklogResponse != null && worklogResponse.getResults() != null && !worklogResponse.getResults().isEmpty()) {
            for (Result result : worklogResponse.getResults()) {
                LocalDate worklogDate = LocalDate.parse(result.getStartDate());
                if (!worklogDate.isBefore(startDate) && !worklogDate.isAfter(endDate)) {
                    worklogsForDateRange.add(result);
                }
            }
            offset += limit;
        } else {
            break;
        }
    }

    return worklogsForDateRange;
}
}


  /*  public  void fetchAndLogWorklogsFromTempo() {
    	
           
       webClient.get()
            .uri("https://api.tempo.io/4/worklogs")
            .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> {
                try {
                	
                	WorklogResponse result  = objectMapper.readValue(response, WorklogResponse .class);
                    // Log the parsed response or perform operations as needed
                    
                	writeWorklogResponseToFile(result);
                      
                        
                	
                	
                	 // Example: Printing the response
                } catch (Exception e) {
                    e.printStackTrace();
                }
            })
            .subscribe();
       
    }
    private void writeWorklogResponseToFile(WorklogResponse response) {
        try {
            // Convert WorklogResponse to JSON string
            String json = objectMapper.writeValueAsString(response);

            // Write JSON string to a file
            File file = new File("worklogYassineBendadda.json");
            objectMapper.writeValue(file, response);

            // Optionally, print a confirmation message
            System.out.println("WorklogResponse written to file: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/