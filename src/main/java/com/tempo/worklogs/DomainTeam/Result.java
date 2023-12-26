package com.tempo.worklogs.DomainTeam;

public class Result {
	
	public boolean administrative;
    public int id;
    public Lead lead;
    public Links links;
    public Members members;
    public String name;
    public Permissions permissions;
    public Program program;
    public String self;
    public String summary;
	public boolean isAdministrative() {
		return administrative;
	}
	public void setAdministrative(boolean administrative) {
		this.administrative = administrative;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Lead getLead() {
		return lead;
	}
	public void setLead(Lead lead) {
		this.lead = lead;
	}
	public Links getLinks() {
		return links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
	public Members getMembers() {
		return members;
	}
	public void setMembers(Members members) {
		this.members = members;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Permissions getPermissions() {
		return permissions;
	}
	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
   

}
