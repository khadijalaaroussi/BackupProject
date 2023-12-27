package com.tempo.worklogs.DomainTimesheetAproval;

public class Status {
	public Actor actor;
    public String comment;
    public String key;
    public int requiredSecondsAtSubmit;
    public int timeSpentSecondsAtSubmit;
    public String updatedAt;
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getRequiredSecondsAtSubmit() {
		return requiredSecondsAtSubmit;
	}
	public void setRequiredSecondsAtSubmit(int requiredSecondsAtSubmit) {
		this.requiredSecondsAtSubmit = requiredSecondsAtSubmit;
	}
	public int getTimeSpentSecondsAtSubmit() {
		return timeSpentSecondsAtSubmit;
	}
	public void setTimeSpentSecondsAtSubmit(int timeSpentSecondsAtSubmit) {
		this.timeSpentSecondsAtSubmit = timeSpentSecondsAtSubmit;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Status [actor=" + actor + ", comment=" + comment + ", key=" + key + ", requiredSecondsAtSubmit="
				+ requiredSecondsAtSubmit + ", timeSpentSecondsAtSubmit=" + timeSpentSecondsAtSubmit + ", updatedAt="
				+ updatedAt + "]";
	}
    
    
    

}
