package org.ouyikonggu.moudel;

public class ProductClass {
	private int id;
	private String cName;
	private int cSort;
	private String cUrl;
	
	public String getCUrl() {
		return cUrl;
	}
	public void setCUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCName() {
		return cName;
	}
	public void setCName(String cName) {
		this.cName = cName;
	}
	public int getCSort() {
		return cSort;
	}
	public void setCSort(int cSort) {
		this.cSort = cSort;
	}
	@Override
	public String toString() {
		return "ProductClass [id=" + id + ", Classname=" + cName + ", ClassSort=" + cSort + "]";
	}
}
