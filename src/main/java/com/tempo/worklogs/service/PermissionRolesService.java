package com.tempo.worklogs.service;


	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.http.*;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

import com.tempo.worklogs.DomainPermissionRoles.Result;
import com.tempo.worklogs.DomainPermissionRoles.Root;

import java.io.File;
import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.List;

	@Service
	public class PermissionRolesService {

	    @Value("${tempo.api.url3}")
	    private String TEMPO_API_URL;

	    @Value("${tempo.api.token}")
	    private String TEMPO_API_TOKEN;
	    @Value("${tempo.api.path}")
	    private String TEMPO_Path;

	    public void getAllPermissionRolesAndSaveToFile() {
	        String permissionRolesEndpoint = TEMPO_API_URL ; // Define the endpoint

	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(TEMPO_API_TOKEN); // Set the token in the request header

	        HttpEntity<String> request = new HttpEntity<>(headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Root> response = restTemplate.exchange(
	                permissionRolesEndpoint,
	                HttpMethod.GET,
	                request,
	                Root.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            Root root = response.getBody();
	            if (root != null && root.results != null) {
	            	 String PermissionRolesFilePath = TEMPO_Path + File.separator +  "PermissionRoles.xlsx";
		                
	                writeToExcel(root.results,PermissionRolesFilePath);
	            } else {
	                System.out.println("No data retrieved from Tempo API.");
	            }
	        } else {
	            System.out.println("Failed to fetch permission roles from Tempo API.");
	        }
	    }

	    private void writeToExcel(List<Result> results,String outputFile) {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Permission Roles");

	        int rowNum = 0;
	        Row headerRow = sheet.createRow(rowNum++);
	        headerRow.createCell(0).setCellValue("ID");
	        headerRow.createCell(1).setCellValue("Name");
	        headerRow.createCell(2).setCellValue("Access Type");
	        headerRow.createCell(3).setCellValue("Editable");
	        headerRow.createCell(4).setCellValue("Self");
	        headerRow.createCell(5).setCellValue("Access Entities");
	        headerRow.createCell(6).setCellValue("Permissions");
	        headerRow.createCell(7).setCellValue("Permitted Users");
	        // Add more headers as needed for other fields

	        for (Result result : results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(result.id);
	            row.createCell(1).setCellValue(result.name);
	            row.createCell(2).setCellValue(result.accessType);
	            row.createCell(3).setCellValue(result.editable);
	            row.createCell(4).setCellValue(result.self);
	            row.createCell(5).setCellValue(result.accessEntities.toString());
	            row.createCell(6).setCellValue(result.permissions.toString());
	            row.createCell(7).setCellValue(result.permittedUsers.toString());
	            // Add more cells for other fields
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
	
	
	
	
	
	


