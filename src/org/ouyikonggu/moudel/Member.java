package org.ouyikonggu.moudel;

import java.util.Date;

public class Member {

	private int id;
	private int mProductID;
	private String mTel;
	private Date mAddTime;
	private int pCount;
	
	
	public int getPCount() {
		return pCount;
	}
	public void setPCount(int pCount) {
		this.pCount = pCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMProductID() {
		return mProductID;
	}
	public void setMProductID(int mProductID) {
		this.mProductID = mProductID;
	}
	public String getMTel() {
		return mTel;
	}
	public void setMTel(String mTel) {
		this.mTel = mTel;
	}
	public Date getMAddTime() {
		return mAddTime;
	}
	public void setMAddTime(Date mAddTime) {
		this.mAddTime = mAddTime;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", mProductID=" + mProductID + ", mTel=" + mTel + ", mAddTime=" + mAddTime
				+ ", pCount=" + pCount + "]";
	}

}
