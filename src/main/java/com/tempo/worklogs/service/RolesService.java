package com.tempo.worklogs.service;

	
	import com.fasterxml.jackson.annotation.JsonProperty;
import com.tempo.worklogs.RolesDomain.Result;
import com.tempo.worklogs.RolesDomain.Root;

import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.http.*;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.ArrayList;

	@Service
	public class RolesService {

	    @Value("${tempo.api.url5}")
	    private String TEMPO_API_URL;

	    @Value("${tempo.api.token}")
	    private String TEMPO_API_TOKEN;

	    @Value("${tempo.api.path}")
	    private String TEMPO_Path;

	    public void fetchAndStoreRoles() {
	        String rolesEndpoint = TEMPO_API_URL; // Define the endpoint for roles

	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(TEMPO_API_TOKEN); // Set the token in the request header

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        try {
	            ResponseEntity<Root> response = restTemplate.exchange(
	                    rolesEndpoint,
	                    HttpMethod.GET,
	                    request,
	                    Root.class);

	            if (response.getStatusCode() == HttpStatus.OK) {
	                Root root = response.getBody();
	                if (root != null && root.results != null) {
	                    String outputFile = TEMPO_Path + File.separator + "Roles.xlsx";
	                    writeToExcel(root.results, outputFile);
	                } else {
	                    System.out.println("No data retrieved from Tempo API.");
	                }
	            } else {
	                System.out.println("Failed to fetch roles from Tempo API.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error occurred: " + e.getMessage());
	        }
	    }

	    private void writeToExcel(ArrayList<Result> results, String outputFile) {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Roles");

	        int rowNum = 0;
	        Row headerRow = sheet.createRow(rowNum++);
	        headerRow.createCell(0).setCellValue("ID");
	        headerRow.createCell(1).setCellValue("Name");
	        headerRow.createCell(2).setCellValue("Default");
	        headerRow.createCell(3).setCellValue("Self");

	        for (Result result : results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(result.id);
	            row.createCell(1).setCellValue(result.name);
	            row.createCell(2).setCellValue(result.mydefault);
	            row.createCell(3).setCellValue(result.self);
	        }

	        try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
	            workbook.write(fileOut);
	            System.out.println("Excel file created successfully at: " + outputFile);
	        } catch (IOException e) {
	            System.out.println("Error occurred while writing to Excel: " + e.getMessage());
	        } finally {
	            try {
	                workbook.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



