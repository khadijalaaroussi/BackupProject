package com.tempo.worklogs.DomainTimesheetAproval;

public class Actions {
	public Approve approve;
    public Reject reject;
    public Reopen reopen;
    public Submit submit;
	public Approve getApprove() {
		return approve;
	}
	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	public Reject getReject() {
		return reject;
	}
	public void setReject(Reject reject) {
		this.reject = reject;
	}
	public Reopen getReopen() {
		return reopen;
	}
	public void setReopen(Reopen reopen) {
		this.reopen = reopen;
	}
	public Submit getSubmit() {
		return submit;
	}
	public void setSubmit(Submit submit) {
		this.submit = submit;
	}
    
    
    
}
