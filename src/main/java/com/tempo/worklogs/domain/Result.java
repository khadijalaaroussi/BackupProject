package com.tempo.worklogs.domain;

import java.util.Date;

public class Result {
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
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public int getTempoWorklogId() {
		return tempoWorklogId;
	}
	public void setTempoWorklogId(int tempoWorklogId) {
		this.tempoWorklogId = tempoWorklogId;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	public int getTimeSpentSeconds() {
		return timeSpentSeconds;
	}
	public void setTimeSpentSeconds(int timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
	public int getBillableSeconds() {
		return billableSeconds;
	}
	public void setBillableSeconds(int billableSeconds) {
		this.billableSeconds = billableSeconds;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

}
