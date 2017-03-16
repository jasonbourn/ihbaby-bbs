package com.ihbaby.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegForm {
	
	
	/**
	 * 手机号
	 */
	@NotNull
	@Length(min = 1, max = 11)
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
