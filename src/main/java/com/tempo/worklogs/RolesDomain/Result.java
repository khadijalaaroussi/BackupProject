package com.tempo.worklogs.RolesDomain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	
	@JsonProperty("default") 
    public boolean mydefault;
    public int id;
    public String name;
    public String self;
	public boolean isMydefault() {
		return mydefault;
	}
	public void setMydefault(boolean mydefault) {
		this.mydefault = mydefault;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
    
    

}
