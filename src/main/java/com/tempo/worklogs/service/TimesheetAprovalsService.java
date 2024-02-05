package com.tempo.worklogs.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tempo.worklogs.DomainTimesheetAproval.Result;
import com.tempo.worklogs.DomainTimesheetAproval.Root;

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
	public class TimesheetAprovalsService {

	    @Value("${tempo.api.url6}")
	    private String TEMPO_API_URL;

	    @Value("${tempo.api.token}")
	    private String TEMPO_API_TOKEN;

	    @Value("${tempo.api.path}")
	    private String TEMPO_Path;

	    public void fetchAndStoreTimesheetApprovals() {
	        String timesheetApprovalsEndpoint = TEMPO_API_URL; // Define the endpoint

	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(TEMPO_API_TOKEN); // Set the token in the request header

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        try {
	            ResponseEntity<Root> response = restTemplate.exchange(
	                    timesheetApprovalsEndpoint,
	                    HttpMethod.GET,
	                    request,
	                    Root.class);

	            if (response.getStatusCode() == HttpStatus.OK) {
	                Root root = response.getBody();
	                if (root != null && root.results != null) {
	                    String outputFile = TEMPO_Path + File.separator + "TimesheetApprovals.xlsx";
	                    writeToExcel(root.results, outputFile);
	                } else {
	                    System.out.println("No data retrieved from Tempo API.");
	                }
	            } else {
	                System.out.println("Failed to fetch timesheet approvals from Tempo API.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error occurred: " + e.getMessage());
	        }
	    }

	    private void writeToExcel(ArrayList<Result> results, String outputFile) {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Timesheet Approvals");

	        int rowNum = 0;
	        Row headerRow = sheet.createRow(rowNum++);
	        // Add headers for fields in the Result class
	        headerRow.createCell(0).setCellValue("Required Seconds");
	        headerRow.createCell(1).setCellValue("Time Spent Seconds");
	        headerRow.createCell(2).setCellValue("Self");
	        headerRow.createCell(3).setCellValue("Actions - Approve");
	        headerRow.createCell(4).setCellValue("Actions - Reject");
	        headerRow.createCell(5).setCellValue("Actions - Reopen");
	        headerRow.createCell(6).setCellValue("Actions - Submit");
	        headerRow.createCell(7).setCellValue("Period - From");
	        headerRow.createCell(8).setCellValue("Period - To");
	        headerRow.createCell(9).setCellValue("Reviewer - Account ID");
	        headerRow.createCell(10).setCellValue("Reviewer - Self");
	        headerRow.createCell(11).setCellValue("Status - Actor - Account ID");
	        headerRow.createCell(12).setCellValue("Status - Actor - Self");
	        headerRow.createCell(13).setCellValue("Status - Comment");
	        headerRow.createCell(14).setCellValue("Status - Key");
	        headerRow.createCell(15).setCellValue("Status - Required Seconds At Submit");
	        headerRow.createCell(16).setCellValue("Status - Time Spent Seconds At Submit");
	        headerRow.createCell(17).setCellValue("Status - Updated At");
	        headerRow.createCell(18).setCellValue("User - Account ID");
	        headerRow.createCell(19).setCellValue("User - Self");
	        headerRow.createCell(20).setCellValue("Worklogs - Self");

	        for (Result result : results) {
	            Row row = sheet.createRow(rowNum++);
	            // Set values for fields in the Result class
	            row.createCell(0).setCellValue(result.requiredSeconds);
	            row.createCell(1).setCellValue(result.timeSpentSeconds);
	            row.createCell(2).setCellValue(result.self);

	            // Set values for nested objects or deeper attributes
	            row.createCell(3).setCellValue(result.actions.approve.self);
	            row.createCell(4).setCellValue(result.actions.reject.self);
	            row.createCell(5).setCellValue(result.actions.reopen.self);
	            row.createCell(6).setCellValue(result.actions.submit.self);
	            row.createCell(7).setCellValue(result.period.from);
	            row.createCell(8).setCellValue(result.period.myto);
	            row.createCell(9).setCellValue(result.reviewer.accountId);
	            row.createCell(10).setCellValue(result.reviewer.self);
	            row.createCell(11).setCellValue(result.status.actor.accountId);
	            row.createCell(12).setCellValue(result.status.actor.self);
	            row.createCell(13).setCellValue(result.status.comment);
	            row.createCell(14).setCellValue(result.status.key);
	            row.createCell(15).setCellValue(result.status.requiredSecondsAtSubmit);
	            row.createCell(16).setCellValue(result.status.timeSpentSecondsAtSubmit);
	            row.createCell(17).setCellValue(result.status.updatedAt);
	            row.createCell(18).setCellValue(result.user.accountId);
	            row.createCell(19).setCellValue(result.user.self);
	            row.createCell(20).setCellValue(result.worklogs.self);
	            // Set more cell values for other fields as needed
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



