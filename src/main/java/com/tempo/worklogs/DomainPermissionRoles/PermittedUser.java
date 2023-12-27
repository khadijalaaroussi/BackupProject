package com.tempo.worklogs.DomainPermissionRoles;

public class PermittedUser {
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
		return "PermittedUser [accountId=" + accountId + ", self=" + self + "]";
	}
    
    
    
}
