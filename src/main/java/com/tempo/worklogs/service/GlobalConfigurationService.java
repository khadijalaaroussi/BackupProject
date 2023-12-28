package com.tempo.worklogs.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tempo.worklogs.DomainGlobalConfiguration.Root;

import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.http.*;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;

	@Service
	public class GlobalConfigurationService {

	    @Value("${tempo.api.url4}")
	    private String TEMPO_GLOBAL_CONFIG_URL;

	    @Value("${tempo.api.token}")
	    private String TEMPO_API_TOKEN;

	    @Value("${tempo.api.path}")
	    private String TEMPO_Path;

	    public void fetchAndStoreGlobalConfig() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(TEMPO_API_TOKEN);
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        try {
	            ResponseEntity<Root> response = restTemplate.exchange(
	                    TEMPO_GLOBAL_CONFIG_URL,
	                    HttpMethod.GET,
	                    request,
	                    Root.class);

	            if (response.getStatusCode() == HttpStatus.OK) {
	                Root globalConfig = response.getBody();
	                if (globalConfig != null) {
	                    String outputFile = TEMPO_Path + File.separator + "GlobalConfig.xlsx";
	                    writeToExcel(globalConfig, outputFile);
	                } else {
	                    System.out.println("No global configuration data retrieved from Tempo API.");
	                }
	            } else {
	                System.out.println("Failed to fetch global configuration from Tempo API.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error occurred: " + e.getMessage());
	        }
	    }

	    private static void writeToExcel(Root globalConfig, String outputFile) {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Global Configuration");

	        int rowNum = 0;
	        Row headerRow = sheet.createRow(rowNum++);

	        headerRow.createCell(0).setCellValue("Allow Logging On Closed Account");
	        headerRow.createCell(1).setCellValue("Approval Period");
	        headerRow.createCell(2).setCellValue("Approval Week Start");
	        headerRow.createCell(3).setCellValue("Max Hours Per Day Per User");
	        headerRow.createCell(4).setCellValue("Number of Days Allowed Into Future");
	        headerRow.createCell(5).setCellValue("Plan Approval Enabled");
	        headerRow.createCell(6).setCellValue("Remaining Estimate Optional");
	        headerRow.createCell(7).setCellValue("Start and End Times Enabled");
	        headerRow.createCell(8).setCellValue("Start and End Times For Planning Enabled");
	        headerRow.createCell(9).setCellValue("Week Start");
	        headerRow.createCell(10).setCellValue("Worklog Description Optional");
	        // Add more headers for other fields as needed

	        Row dataRow = sheet.createRow(rowNum++);
	        dataRow.createCell(0).setCellValue(globalConfig.allowLoggingOnClosedAccount);
	        dataRow.createCell(1).setCellValue(globalConfig.approvalPeriod);
	        dataRow.createCell(2).setCellValue(globalConfig.approvalWeekStart);
	        dataRow.createCell(3).setCellValue(globalConfig.maxHoursPerDayPerUser);
	        dataRow.createCell(4).setCellValue(globalConfig.numberOfDaysAllowedIntoFuture);
	        dataRow.createCell(5).setCellValue(globalConfig.planApprovalEnabled);
	        dataRow.createCell(6).setCellValue(globalConfig.remainingEstimateOptional);
	        dataRow.createCell(7).setCellValue(globalConfig.startAndEndTimesEnabled);
	        dataRow.createCell(8).setCellValue(globalConfig.startAndEndTimesForPlanningEnabled);
	        dataRow.createCell(9).setCellValue(globalConfig.weekStart);
	        dataRow.createCell(10).setCellValue(globalConfig.worklogDescriptionOptional);
	        // Set more cell values for other fields as needed

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
