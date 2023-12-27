package com.tempo.worklogs.DomainPermissionRoles;

public class AccessEntity {
	public int id;
    public String self;
	public int getId() {
		return id;
	}
	public void setId(int id) {
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	@Override
	public String toString() {
		return "AccessEntity [id=" + id + ", self=" + self + "]";
	}
    

}
