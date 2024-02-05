package com.tempo.worklogs.service;


	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.http.*;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

import com.tempo.worklogs.DomainPermissionRoles.AccessEntity;
import com.tempo.worklogs.DomainPermissionRoles.Permission;
import com.tempo.worklogs.DomainPermissionRoles.PermittedUser;
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
	        headerRow.createCell(5).setCellValue("Access Entity ID");
	        headerRow.createCell(6).setCellValue("Access Entity Self");
	        headerRow.createCell(7).setCellValue("Permission Key");
	        headerRow.createCell(8).setCellValue("Permitted User Account ID");
	        headerRow.createCell(9).setCellValue("Permitted User Self");
	        // Add more headers as needed for other fields

	        for (Result result : results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(result.id);
	            row.createCell(1).setCellValue(result.name);
	            row.createCell(2).setCellValue(result.accessType);
	            row.createCell(3).setCellValue(result.editable);
	            row.createCell(4).setCellValue(result.self);

	            // Access Entities
	            if (result.accessEntities != null && !result.accessEntities.isEmpty()) {
	                for (int i = 0; i < result.accessEntities.size(); i++) {
	                    AccessEntity accessEntity = result.accessEntities.get(i);
	                    row.createCell(5 + i * 2).setCellValue(accessEntity.getId()); // ID column
	                    row.createCell(6 + i * 2).setCellValue(accessEntity.getSelf()); // Self column
	                }
	            }

	            // Permissions
	            if (result.permissions != null && !result.permissions.isEmpty()) {
	                for (int i = 0; i < result.permissions.size(); i++) {
	                    Permission permission = result.permissions.get(i);
	                    row.createCell(5 + i).setCellValue(permission.getKey()); // Key column
	                }
	            }

	            // Permitted Users
	            if (result.permittedUsers != null && !result.permittedUsers.isEmpty()) {
	                for (int i = 0; i < result.permittedUsers.size(); i++) {
	                    PermittedUser permittedUser = result.permittedUsers.get(i);
	                    row.createCell(8 + i * 2).setCellValue(permittedUser.getAccountId()); // Account ID column
	                    row.createCell(9 + i * 2).setCellValue(permittedUser.getSelf()); // Self column
	                }
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
	}
	
	
	
	
	


