package com.ihbaby.model;

public class DoctorModel {
	
	
	private long userId;
	
	private long hospitalId;
	
	private String name;
	
	private String mobile;
	
	private Integer genter;
	
	/** 头像 */
	private String headPic;
	
	private String token;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getHospitalId() {
		return hospitalId;
	}
	
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getGenter() {
		return genter;
	}
	
	public void setGenter(Integer genter) {
		this.genter = genter;
	}
	
	public String getHeadPic() {
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
