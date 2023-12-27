package com.tempo.worklogs.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tempo.worklogs.service.ExternalApiService;
import com.tempo.worklogs.service.PermissionRolesService;
import com.tempo.worklogs.service.TeamMemberships;
import com.tempo.worklogs.service.TeamsService;
import com.tempo.worklogs.service.TimesheetAprovalsService;

@Configuration

@EnableScheduling

public class BackupScheduler {
	 
	
	private final PermissionRolesService backupService4;
	private final ExternalApiService backupService1;
	private final  TeamMemberships backupService2;
	private final TeamsService backupService3;
	private final TimesheetAprovalsService backupService5;
    public BackupScheduler(ExternalApiService backupService1,TeamMemberships backupService2,TeamsService backupService3,PermissionRolesService backupService4,TimesheetAprovalsService backupService5) {
        this.backupService1 = backupService1;
        this.backupService2 = backupService2;
        this.backupService3 = backupService3;
        this.backupService4 = backupService4;
        this.backupService5 = backupService5;
    }

    @Scheduled(cron = "${tempo.api.cron}") // Cron expression for running at midnight on the 1st day of every month
    public void scheduleBackup() {
        backupService1.retrieveMonthlyWorklogs();
        backupService1.getAllWorklogsAsExcel();
        backupService2.retrieveRootFromTempoAPI("TeamMEMBERSHIPS.xlsx");
        backupService3.generateExcelFromTempoAPI();
        backupService4.getAllPermissionRolesAndSaveToFile();
        backupService5.fetchAndStoreTimesheetApprovals();
    }

}
