package com.ihbaby.model;

public class IhbUserModel {
	
	
	private long id;
	
	/** 姓名，加密后字符串 */
	private String name;
	
	/** 性别：1男，2女 */
	private int gender;
	
	/** 手机号，加密后字符串 */
	private String mobile;
	
	/** 头像 */
	private String headPic;
	
	/** 就诊卡号 */
	private String caseNumber;
	
	/** 是否已开通服务 0 未开通,1已开通 */
	private boolean hasService;
	
	/** 是否进行过高危评分 */
	private boolean hasRiskscore;
	
	/** 医院id */
	private long hospitalId;
	
	private String token;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getHeadPic() {
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	
	public String getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public boolean isHasService() {
		return hasService;
	}
	
	public void setHasService(boolean hasService) {
		this.hasService = hasService;
	}
	
	public boolean isHasRiskscore() {
		return hasRiskscore;
	}
	
	public void setHasRiskscore(boolean hasRiskscore) {
		this.hasRiskscore = hasRiskscore;
	}
	
	public long getHospitalId() {
		return hospitalId;
	}
	
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
