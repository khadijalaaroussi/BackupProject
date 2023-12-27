package com.tempo.worklogs.DomainTeamMemberships;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	public int commitmentPercent;
    public String from;
    public int id;
    public Member member;
    public Role role;
    public String self;
    public Team team;
    @JsonProperty("to") 
    public String myto;
	public int getCommitmentPercent() {
		return commitmentPercent;
	}
	public void setCommitmentPercent(int commitmentPercent) {
		this.commitmentPercent = commitmentPercent;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public String getMyto() {
		return myto;
	}
	public void setMyto(String myto) {
		this.myto = myto;
	}
	@Override
	public String toString() {
		return "Result [commitmentPercent=" + commitmentPercent + ", from=" + from + ", id=" + id + ", member=" + member
				+ ", role=" + role + ", self=" + self + ", team=" + team + ", myto=" + myto + "]";
	}
    
}
