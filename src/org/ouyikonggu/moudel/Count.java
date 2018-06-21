package org.ouyikonggu.moudel;

import java.util.Date;

public class Count {
	
	private int id;
	private String countName;
	private String countUrl;
	private int pV;
	private int uV;
	private int CActivate;
	private Date cAddTime;
	
	
	public int getCActivate() {
		return CActivate;
	}
	public void setCActivate(int cActivate) {
		CActivate = cActivate;
	}
	public String getCountUrl() {
		return countUrl;
	}
	public void setCountUrl(String countUrl) {
		this.countUrl = countUrl;
	}
	public int getPV() {
		return pV;
	}
	public void setPV(int pV) {
		this.pV = pV;
	}
	public int getUV() {
		return uV;
	}
	public void setUV(int uV) {
		this.uV = uV;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		this.countName = countName;
	}
	public Date getCAddTime() {
		return cAddTime;
	}
	public void setCAddTime(Date cAddTime) {
		this.cAddTime = cAddTime;
	}
	@Override
	public String toString() {
		return "Count [id=" + id + ", countName=" + countName + ", pV=" + pV + ", uV=" + uV + ", cAddTime=" + cAddTime
				+ "]";
	}
	
}
