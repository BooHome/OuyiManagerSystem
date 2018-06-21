package org.ouyikonggu.moudel;

import java.util.Date;

public class User {
	private int id;
	private String uTel;
	private int pCount;
	private Date uAddTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUTel() {
		return uTel;
	}
	public void setUTel(String uTel) {
		this.uTel = uTel;
	}
	public int getPCount() {
		return pCount;
	}
	public void setPCount(int pCount) {
		this.pCount = pCount;
	}
	public Date getUAddTime() {
		return uAddTime;
	}
	public void setUAddTime(Date uAddTime) {
		this.uAddTime = uAddTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uTel=" + uTel + ", pCount=" + pCount + ", uAddTime=" + uAddTime + "]";
	}
	
}
