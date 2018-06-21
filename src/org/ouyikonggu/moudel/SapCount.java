package org.ouyikonggu.moudel;

import java.util.Date;

public class SapCount {
	private int id;
	private int sapId;
	private StandAloneProduct sap;
	private int PV;
	private int UV;
	private Date CAddTime;
	
	private String spaName;
	private String url;
	
	
	public String getSpaName() {
		return spaName;
	}
	public void setSpaName(String spaName) {
		this.spaName = spaName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSapId() {
		return sapId;
	}
	public void setSapId(int sapId) {
		this.sapId = sapId;
	}
	public StandAloneProduct getSap() {
		return sap;
	}
	public void setSap(StandAloneProduct sap) {
		this.sap = sap;
	}
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}
	public int getUV() {
		return UV;
	}
	public void setUV(int uV) {
		UV = uV;
	}
	public Date getCAddTime() {
		return CAddTime;
	}
	public void setCAddTime(Date cAddTime) {
		CAddTime = cAddTime;
	}
	
	@Override
	public String toString() {
		return "SapCount [id=" + id + ", sapId=" + sapId + ", sap=" + sap + ", PV=" + PV + ", UV=" + UV + ", CAddTime="
				+ CAddTime + "]";
	}
	
}
