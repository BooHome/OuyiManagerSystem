package org.ouyikonggu.moudel;

import java.util.Date;

public class Slides {
	
	private int id;
	private int scid;
	private String pcName;
	private ProductClass productClass;
	private String STitle;
	private String STitleImg;
	private String SSort;
	private Date SAddTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public ProductClass getProductClass() {
		return productClass;
	}
	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}
	public String getSTitle() {
		return STitle;
	}
	public void setSTitle(String sTitle) {
		STitle = sTitle;
	}
	public String getSTitleImg() {
		return STitleImg;
	}
	public void setSTitleImg(String sTitleImg) {
		STitleImg = sTitleImg;
	}
	public String getSSort() {
		return SSort;
	}
	public void setSSort(String sSort) {
		SSort = sSort;
	}
	public Date getSAddTime() {
		return SAddTime;
	}
	public void setSAddTime(Date sAddTime) {
		SAddTime = sAddTime;
	}
	@Override
	public String toString() {
		return "Slides [id=" + id + ", scid=" + scid + ", pcName=" + pcName + ", productClass=" + productClass
				+ ", STitle=" + STitle + ", STitleImg=" + STitleImg + ", SSort=" + SSort + ", SAddTime=" + SAddTime
				+ "]";
	}
}
