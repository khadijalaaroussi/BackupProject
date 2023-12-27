package com.tempo.worklogs.DomainTimesheetAproval;

import java.util.ArrayList;

public class Root {
	public Metadata metadata;
    public ArrayList<Result> results;
    public String self;
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public ArrayList<Result> getResults() {
		return results;
	}
	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	@Override
	public String toString() {
		return "Root [self=" + self + "]";
	}
    
    

}
