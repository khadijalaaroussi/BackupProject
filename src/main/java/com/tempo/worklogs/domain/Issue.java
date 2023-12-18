package com.tempo.worklogs.domain;

public class Issue {
	public String self;
    public int id;
    @Override
    public String toString() {
        return "Issue [self=" + self + ", id=" + id + "]";
    }
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
