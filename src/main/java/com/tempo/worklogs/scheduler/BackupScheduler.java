package com.tempo.worklogs.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tempo.worklogs.service.ExternalApiService;

@Configuration

@EnableScheduling

public class BackupScheduler {
	 
	
	
	private final ExternalApiService backupService;

    public BackupScheduler(ExternalApiService backupService) {
        this.backupService = backupService;
    }

    @Scheduled(cron = "${tempo.api.cron}") // Cron expression for running at midnight on the 1st day of every month
    public void scheduleBackup() {
        backupService.retrieveMonthlyWorklogs();
        backupService.getAllWorklogsAsExcel();
        
        
    }

}
