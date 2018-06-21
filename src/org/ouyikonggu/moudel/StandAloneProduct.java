package org.ouyikonggu.moudel;

public class StandAloneProduct {

	private int id;
	private int sapActivate;
	private String sapName;
	private String url;
	private String viewUrl;
	
	
	public int getSapActivate() {
		return sapActivate;
	}
	public void setSapActivate(int sapActivate) {
		this.sapActivate = sapActivate;
	}
	public String getViewUrl() {
		return viewUrl;
	}
	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSapName() {
		return sapName;
	}
	public void setSapName(String sapName) {
		this.sapName = sapName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "StandAloneProduct [id=" + id + ", sapActivate=" + sapActivate + ", sapName=" + sapName + ", url=" + url
				+ ", viewUrl=" + viewUrl + "]";
	}
}
