package com.tempo.worklogs;

import java.util.ArrayList;
import java.util.Date;

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

	public class Result{
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
