package com.tempo.worklogs.DomainPermissionRoles;

import java.util.ArrayList;

public class Result {
	
	public ArrayList<AccessEntity> accessEntities;
    public String accessType;
    public boolean editable;
    public int id;
    public String name;
    public ArrayList<Permission> permissions;
    public ArrayList<PermittedUser> permittedUsers;
    public String self;
	public ArrayList<AccessEntity> getAccessEntities() {
		return accessEntities;
	}
	public void setAccessEntities(ArrayList<AccessEntity> accessEntities) {
		this.accessEntities = accessEntities;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
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
	public ArrayList<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}
	public ArrayList<PermittedUser> getPermittedUsers() {
		return permittedUsers;
	}
	public void setPermittedUsers(ArrayList<PermittedUser> permittedUsers) {
		this.permittedUsers = permittedUsers;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	@Override
	public String toString() {
		return "Result [accessEntities=" + accessEntities + ", accessType=" + accessType + ", editable=" + editable
				+ ", id=" + id + ", name=" + name + ", permissions=" + permissions + ", permittedUsers="
				+ permittedUsers + ", self=" + self + "]";
	}

}
