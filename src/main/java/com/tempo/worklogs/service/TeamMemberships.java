package com.tempo.worklogs.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tempo.worklogs.DomainTeamMemberships.Result;
import com.tempo.worklogs.DomainTeamMemberships.Root;

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
public class TeamMemberships {
	
	    @Value("${tempo.api.path}")
        private String TEMPO_Path;
	    @Value("${tempo.api.url2}")
	    private String TEMPO_TEAMS_Memberships_URL;

	    @Value("${tempo.api.token}")
	    private String token;

	    public void retrieveRootFromTempoAPI(String outputFile) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + token);
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Root> response = restTemplate.exchange(
	        		TEMPO_TEAMS_Memberships_URL,
	                HttpMethod.GET,
	                request,
	                Root.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            Root root = response.getBody();
	            if (root != null && root.results != null) {
	                String membershipsFilePath = TEMPO_Path + File.separator +  "TeamMEMBERSHIPS.xlsx";
	                writeToExcel(root, membershipsFilePath);
	            } else {
	                System.out.println("No data retrieved from Tempo API.");
	            }
	        } else {
	            System.out.println("Failed to fetch team memberships.");
	        }
	    } 


	    private void writeToExcel(Root root, String outputFile) {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Team Memberships");
	        String membershipsFilePath = outputFile;

	        int rowNum = 0;
	        Row headerRow = sheet.createRow(rowNum++);
	        headerRow.createCell(0).setCellValue("Commitment Percent");
	        headerRow.createCell(1).setCellValue("From");
	        headerRow.createCell(2).setCellValue("ID");
	        headerRow.createCell(3).setCellValue("Member Account ID");
	        headerRow.createCell(4).setCellValue("Role ID");
	        headerRow.createCell(5).setCellValue("Role Name");
	        headerRow.createCell(6).setCellValue("Team ID");
	        headerRow.createCell(7).setCellValue("Team Name");

	        for (Result result : root.results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(result.commitmentPercent);
	            row.createCell(1).setCellValue(result.from);
	            row.createCell(2).setCellValue(result.id);
	            row.createCell(3).setCellValue(result.member.accountId);
	            row.createCell(4).setCellValue(result.role.id);
	            row.createCell(5).setCellValue(result.role.name);
	            row.createCell(6).setCellValue(result.team.id);
	            row.createCell(7).setCellValue(result.team.name);
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
	    }}