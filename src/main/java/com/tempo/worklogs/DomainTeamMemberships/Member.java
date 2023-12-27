package com.tempo.worklogs.DomainTeamMemberships;

public class Member {
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
		return "Member [accountId=" + accountId + ", self=" + self + "]";
	}
    

}
