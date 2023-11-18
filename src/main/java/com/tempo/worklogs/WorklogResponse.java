package com.tempo.worklogs;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class WorklogResponse {
	public class Attributes{
	    public String self;
	    public ArrayList<Value> values;
	}

	public class Author{
	    public String accountId;
	    public String self;
	}

	public class Issue{
	    public int id;
	    public String self;
	}

	public class Metadata{
	    public int count;
	    public int limit;
	    public String next;
	    public int offset;
	    public String previous;
	}
	
	public static class Result{
		public Result() {
            // Initialize default values or leave them as null/0
        }
		
	    public Attributes attributes;
	    public Author author;
	    public int billableSeconds;
	    public Date createdAt;
	    public String description;
	    public Issue issue;
	    public String self;
	    public String startDate;
	    public String startTime;
	    public int tempoWorklogId;
	    public int timeSpentSeconds;
	    public Date updatedAt;
	    @Override
	    public String toString() {
	        return "Result{" +
	                "attributes=" + attributes +
	                ", author=" + author +
	                ", billableSeconds=" + billableSeconds +
	                ", createdAt=" + createdAt +
	                ", description='" + description + '\'' +
	                ", issue=" + issue +
	                ", self='" + self + '\'' +
	                ", startDate='" + startDate + '\'' +
	                ", startTime='" + startTime + '\'' +
	                ", tempoWorklogId=" + tempoWorklogId +
	                ", timeSpentSeconds=" + timeSpentSeconds +
	                ", updatedAt=" + updatedAt +
	                '}';
	    }
	    
	}

	public class Root{
	    public Metadata metadata;
	    public ArrayList<Result> results;
	    public String self;
	}

	public class Value{
	    public String key;
	    public String value;
	}



}
