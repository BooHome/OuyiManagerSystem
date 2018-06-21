package org.ouyikonggu.moudel;

import java.util.Date;

public class Product {
	private int id;
	private int pcid;
	private String  pcName;
	private ProductClass  productClass ;
	private String pTitle;//标题
	private String pTitleImg;//标题图片
	private String pRemark;//简介
	private String pED;//最高额度
	private String pFDSJ;//放贷时间
	private String pLX;//利息
	private String pJKQX;//借款期限
	private int pNum;//申请人数
	private String pUrl;//跳转地址
	private int pState;//状态：1.最新，2.最热
	private int pHitNum;//点击量
	private int pRegNum;//注册量
	private int pSortID;//排序
	private Date pAddTime;//添加时间
	private int pActivate;//激活标志
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPcid() {
		return pcid;
	}
	public void setPcid(int pcid) {
		this.pcid = pcid;
	}
	public ProductClass getProductClass() {
		return productClass;
	}
	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}
	public String getPTitle() {
		return pTitle;
	}
	public void setPTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getPTitleImg() {
		return pTitleImg;
	}
	public void setPTitleImg(String pTitleImg) {
		this.pTitleImg = pTitleImg;
	}
	public String getPRemark() {
		return pRemark;
	}
	public void setPRemark(String pRemark) {
		this.pRemark = pRemark;
	}
	public String getPED() {
		return pED;
	}
	public void setPED(String pED) {
		this.pED = pED;
	}
	public String getPFDSJ() {
		return pFDSJ;
	}
	public void setPFDSJ(String pFDSJ) {
		this.pFDSJ = pFDSJ;
	}
	public String getPLX() {
		return pLX;
	}
	public void setPLX(String pLX) {
		this.pLX = pLX;
	}
	public String getPJKQX() {
		return pJKQX;
	}
	public void setPJKQX(String pJKQX) {
		this.pJKQX = pJKQX;
	}
	public int getPNum() {
		return pNum;
	}
	public void setPNum(int pNum) {
		this.pNum = pNum;
	}
	public String getPUrl() {
		return pUrl;
	}
	public void setPUrl(String pUrl) {
		this.pUrl = pUrl;
	}
	public int getPState() {
		return pState;
	}
	public void setPState(int pState) {
		this.pState = pState;
	}
	public int getPHitNum() {
		return pHitNum;
	}
	public void setPHitNum(int pHitNum) {
		this.pHitNum = pHitNum;
	}
	public int getPRegNum() {
		return pRegNum;
	}
	public void setPRegNum(int pRegNum) {
		this.pRegNum = pRegNum;
	}
	public int getPSortID() {
		return pSortID;
	}
	public void setPSortID(int pSortID) {
		this.pSortID = pSortID;
	}
	public Date getPAddTime() {
		return pAddTime;
	}
	public void setPAddTime(Date pAddTime) {
		this.pAddTime = pAddTime;
	}
	public int getPActivate() {
		return pActivate;
	}
	public void setPActivate(int pActivate) {
		this.pActivate = pActivate;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", pcid=" + pcid + ", productClass=" + productClass + ", pTitle=" + pTitle
				+ ", pTitleImg=" + pTitleImg + ", pRemark=" + pRemark + ", pED=" + pED + ", pFDSJ=" + pFDSJ + ", pLX="
				+ pLX + ", pJKQX=" + pJKQX + ", pNum=" + pNum + ", pUrl=" + pUrl + ", pState=" + pState + ", pHitNum="
				+ pHitNum + ", pRegNum=" + pRegNum + ", pSortID=" + pSortID + ", pAddTime=" + pAddTime + ", pActivate="
				+ pActivate + "]";
	}

}
