package com.tempo.worklogs.domain;

import java.util.ArrayList;

public class WorklogResponse {
	
	
	public String self;
    public Metadata metadata;
    public ArrayList<Result> results;
    @Override
    public String toString() {
        return "WorklogResponse [self=" + self + ", metadata=" + metadata + ", results=" + results + "]";
    }
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
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
    


}
