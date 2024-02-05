package com.tempo.worklogs.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tempo.worklogs.DomainTeam.Result;
import com.tempo.worklogs.DomainTeam.Root;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamsService {
	
	    @Value("${tempo.api.url1}")
	    private String TEMPO_TEAMS_URL;

	    @Value("${tempo.api.token}")
	    private String token;
	    
	    @Value("${tempo.api.path}")
        private String TEMPO_Path;

	    public Root retrieveRootFromTempoAPI() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + token);
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Root> response = restTemplate.exchange(
	                TEMPO_TEAMS_URL,
	                HttpMethod.GET,
	                request,
	                Root.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            return response.getBody();
	        } else {
	            // Handle error cases or return null in case of failure
	            return null;
	        }
	    }
	    
	    

	        public void generateExcelFromTempoAPI() {
	            // Retrieve Root items from Tempo API
	            Root root = retrieveRootFromTempoAPI();

	            if (root != null && root.results != null && !root.results.isEmpty()) {
	                // Create Excel workbook and sheet
	                try (Workbook workbook = new XSSFWorkbook()) {
	                	 String outputFile = TEMPO_Path + File.separator + "TempoTeams.xlsx";
	                    Sheet sheet = workbook.createSheet("Tempo Teams");

	                    // Create header row
	                    Row headerRow = sheet.createRow(0);
	                    String[] headers = {"ID", "Name", "Summary", "Lead AccountId","Lead Self","links self","members self","permissions","program ID","program self","program name","self","administrative"};
	                    for (int i = 0; i < headers.length; i++) {
	                        Cell cell = headerRow.createCell(i);
	                        cell.setCellValue(headers[i]);
	                    }

	                    // Populate data rows
	                    int rowNum = 1;
	                    for (Result result : root.results) {
	                        Row row = sheet.createRow(rowNum++);
	                        row.createCell(0).setCellValue(result.id);
	                        row.createCell(1).setCellValue(result.name);
	                        row.createCell(2).setCellValue(result.summary);
	                        if (result.lead != null) {
	                            row.createCell(3).setCellValue(result.lead.accountId);
	                            row.createCell(4).setCellValue(result.lead.self);
	                            
	                        }
	                        if(result.links != null) {
	                        	row.createCell(5).setCellValue(result.links.self);
	                        	
	                  	
	                        }
	                        if(result.members != null) {
	                        row.createCell(6).setCellValue(result.members.self);
	                       
	                        }
	                        
	                        if(result.program != null) {
		                        row.createCell(7).setCellValue(result.program.id);
		                        row.createCell(8).setCellValue(result.program.self);
		                        row.createCell(9).setCellValue(result.program.name);
		                        
	                        }
	                        row.createCell(7).setCellValue(result.self);
	                        row.createCell(8).setCellValue(result.administrative);
	                        
	                    }

	                    // Write to file
	                    try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
	                        workbook.write(fileOut);
	                        System.out.println("Fichier Excel créé avec succès à : " + outputFile);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        
	    }


	    // Other methods like generateExcelFromTempoAPI() will use retrieveRootFromTempoAPI() to get data
	



