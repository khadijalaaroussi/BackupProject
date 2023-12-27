package com.tempo.worklogs.DomainTeamMemberships;

public class Team {
	 public int id;
	    public String name;
	    public String self;
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
		@Override
		public String toString() {
			return "Team [id=" + id + ", name=" + name + ", self=" + self + "]";
		}
	    
	    

}
