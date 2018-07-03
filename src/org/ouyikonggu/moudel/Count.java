package org.ouyikonggu.moudel;

import java.util.Date;

public class Count {
	
	private int id;
	private int cpid;
	private Product cpro;
	private int pV;
	private int uV;
	private Date cAddTime;
	
	
	public int getCpid() {
		return cpid;
	}
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	public Product getCpro() {
		return cpro;
	}
	public void setCpro(Product cpro) {
		this.cpro = cpro;
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
	public Date getCAddTime() {
		return cAddTime;
	}
	public void setCAddTime(Date cAddTime) {
		this.cAddTime = cAddTime;
	}
	@Override
	public String toString() {
		return "Count [id=" + id + ", cpid=" + cpid + ", cpro=" + cpro + ", pV=" + pV + ", uV=" + uV + ", cAddTime="
				+ cAddTime + "]";
	}
}
