package org.ouyikonggu.moudel;

public class UserProduct {
	private int id;
	private String uPName;
	private int uCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUPName() {
		return uPName;
	}
	public void setUPName(String uPName) {
		this.uPName = uPName;
	}
	
	public int getUCount() {
		return uCount;
	}
	public void setUCount(int uCount) {
		this.uCount = uCount;
	}
	@Override
	public String toString() {
		return "UserProduct [id=" + id + ", uPName=" + uPName + ", uCount=" + uCount + "]";
	}
	
}
