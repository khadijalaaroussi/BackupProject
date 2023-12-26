package com.tempo.worklogs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;



import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    @Value("${tempo.api.path}")
    private String TEMPO_Path;
    // Other properties...

    public ExternalApiService(ObjectMapper objectMapper) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.objectMapper = objectMapper;
    }
    public void getAllWorklogsAsExcel() {
    	List<Result> allWorklogs = getAllWorklogs(); // Récupérer tous les worklogs
        String allWorklogsFilePath = TEMPO_Path + File.separator + "all_worklogs.xlsx";

        // Vérifier si le dossier existe, sinon le créer
        File directory = new File(TEMPO_Path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Folder created: " + directory.getAbsolutePath());
            } else {
                System.err.println("Failed to create folder: " + directory.getAbsolutePath());
                return;
            }
        }

        writeToExcel(allWorklogs, allWorklogsFilePath);
    }
    
    
    public void retrieveMonthlyWorklogs() {
        // Récupération du mois courant
        YearMonth currentMonth = YearMonth.now();
        LocalDate startOfMonth = currentMonth.atDay(1);
        LocalDate endOfMonth = currentMonth.atEndOfMonth();

        // Utilisation de la méthode getWorklogsForDateRange() pour récupérer les worklogs du mois courant
        List<Result> monthlyWorklogs = getWorklogsForDateRange(startOfMonth, endOfMonth);
        writeToExcel(monthlyWorklogs, TEMPO_Path + File.separator + "monthly_worklogs_" + currentMonth.getMonth().toString() + ".xlsx");
       /* try {
            File outputFile = new File("monthly_worklogs.json");
            objectMapper.writeValue(outputFile, monthlyWorklogs);
            System.out.println("Monthly worklogs saved to monthly_worklogs.json");
            System.out.println("Number of worklogs for this month: " + monthlyWorklogs.size());
        } catch (IOException e) {
            System.err.println("Error occurred while writing monthly worklogs to file: " + e.getMessage());
        }*/
    }
    public  List<Result> getAllWorklogs() {
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

       /* try {
            File outputFile = new File("worklogs.json");
            objectMapper.writeValue(outputFile, allWorklogs);
            System.out.println("Worklogs saved to worklogs.json");
            System.out.println("Total number of worklogs: " + worklogCount); 
        } catch (IOException e) {
            System.err.println("Error occurred while writing worklogs to file: " + e.getMessage());
        }*/
        return allWorklogs;
    }



    public List<Result> getWorklogsForDateRange(LocalDate startDate, LocalDate endDate) {
        List<Result> worklogsForDateRange = new ArrayList<>();
        int limit = 5000;
        int offset = 0;

        RestTemplate restTemplate = new RestTemplate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            String url = "https://api.tempo.io/4/worklogs?limit=" + limit + "&offset=" + offset +
                         "&from=" + startDate.format(formatter) + "&to=" + endDate.format(formatter);

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
                    worklogsForDateRange.add(result);
                }
                offset += limit;
            } else {
                break;
            }
        }

        return worklogsForDateRange;
    }

//Existing imports and class definition...

private void writeToExcel(List<Result> worklogs, String excelFileName) {
    try (Workbook workbook = new XSSFWorkbook()) {
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Worklogs");
        int rowNum = 0;

        // Headers
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {"self","tempoWorklogId", "issue", "timeSpentSeconds", "billableSeconds", "startDate", "startTime", "description", "createdAt", "updatedAt","author"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Worklog data
        for (Result worklog : worklogs) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(worklog.getSelf());
            row.createCell(1).setCellValue(worklog.getTempoWorklogId());
            row.createCell(2).setCellValue(worklog.getIssue().toString());
            row.createCell(3).setCellValue(worklog.getTimeSpentSeconds());
            row.createCell(4).setCellValue(worklog.getBillableSeconds());
            row.createCell(5).setCellValue(worklog.getStartDate());
            row.createCell(6).setCellValue(worklog.getStartTime());
            row.createCell(7).setCellValue(worklog.getDescription());
            row.createCell(8).setCellValue(worklog.getCreatedAt());
            row.createCell(9).setCellValue(worklog.getUpdatedAt());
            row.createCell(10).setCellValue(worklog.getAuthor().toString());
           
            // Add other cell values for the remaining attributes of the worklog
        }

        // Write to Excel file
        try (FileOutputStream outputStream = new FileOutputStream(excelFileName)) {
            workbook.write(outputStream);
            System.out.println("Worklogs saved to " + excelFileName);
            System.out.println("Total number of worklogs: " + worklogs.size());
        } catch (IOException e) {
            System.err.println("Error occurred while writing worklogs to Excel file: " + e.getMessage());
        }
    } catch (IOException e) {
        System.err.println("Error occurred while creating Excel workbook: " + e.getMessage());
    }
}}



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