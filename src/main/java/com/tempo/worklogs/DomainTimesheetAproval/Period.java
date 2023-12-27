package com.tempo.worklogs.DomainTimesheetAproval;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Period {
	public String from;
    @JsonProperty("to") 
    public String myto;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMyto() {
		return myto;
	}
	public void setMyto(String myto) {
		this.myto = myto;
	}
	@Override
	public String toString() {
		return "Period [from=" + from + ", myto=" + myto + "]";
	}
    
    
    

}
