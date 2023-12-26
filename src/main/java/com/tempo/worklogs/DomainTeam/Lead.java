package com.tempo.worklogs.DomainTeam;

public class Lead {
	public String accountId;
    public String self;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	@Override
	public String toString() {
		return "Lead [accountId=" + accountId + ", self=" + self + "]";
	}
    

}
