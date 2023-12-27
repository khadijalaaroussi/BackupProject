package com.tempo.worklogs.DomainPermissionRoles;

public class Metadata {
	public int count;
    public int limit;
    public String next;
    public int offset;
    public String previous;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	@Override
	public String toString() {
		return "Metadata [count=" + count + ", limit=" + limit + ", next=" + next + ", offset=" + offset + ", previous="
				+ previous + "]";
	}
    
    

}
