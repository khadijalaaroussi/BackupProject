package com.tempo.worklogs.DomainTimesheetAproval;

public class Result {
	
	public Actions actions;
    public Period period;
    public int requiredSeconds;
    public Reviewer reviewer;
    public String self;
    public Status status;
    public int timeSpentSeconds;
    public User user;
    public Worklogs worklogs;
	public Actions getActions() {
		return actions;
	}
	public void setActions(Actions actions) {
		this.actions = actions;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public int getRequiredSeconds() {
		return requiredSeconds;
	}
	public void setRequiredSeconds(int requiredSeconds) {
		this.requiredSeconds = requiredSeconds;
	}
	public Reviewer getReviewer() {
		return reviewer;
	}
	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getTimeSpentSeconds() {
		return timeSpentSeconds;
	}
	public void setTimeSpentSeconds(int timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Worklogs getWorklogs() {
		return worklogs;
	}
	public void setWorklogs(Worklogs worklogs) {
		this.worklogs = worklogs;
	}
    
    
    

}
