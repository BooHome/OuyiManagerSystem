package org.ouyikonggu.moudel;

public class Admin {
	private int id;
	private String adName;
	private String adPwd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdPwd() {
		return adPwd;
	}
	public void setAdPwd(String adPwd) {
		this.adPwd = adPwd;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + adName + ", userpwd=" + adPwd + "]";
	}
}
