package com.tempo.worklogs.domain;

import java.util.Date;

public class Result{
    public String self;
    public int tempoWorklogId;
    public Issue issue;
    public int timeSpentSeconds;
    public int billableSeconds;
    public String startDate;
    public String startTime;
    public String description;
    public Date createdAt;
    public Date updatedAt;
    public Author author;
    public Attributes attributes;
    @Override
    public String toString() {
        return "Result [self=" + self + ", tempoWorklogId=" + tempoWorklogId + ", issue=" + issue
                + ", timeSpentSeconds=" + timeSpentSeconds + ", billableSeconds=" + billableSeconds + ", startDate="
                + startDate + ", startTime=" + startTime + ", description=" + description + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", author=" + author + ", attributes=" + attributes + "]";
    }

    
}
