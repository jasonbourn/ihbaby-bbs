package com.ihbaby.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;

public class ApiResult<T> {
	
	
	public static final int SUCCESS = 1;
	
	public static final int ERROR = -1;
	
	public static final String SUCCESS_MSG = "success";
	
	public static final String ERROR_MSG = "fail";
	
	/** 返回结果状态码 */
	private int status;
	
	/** 返回结果状态信息 */
	private String msg;
	
	/** 返回结果的数据 */
	private T data;
	
	public static <T> ApiResult<T> createSuccess() {
		return new ApiResult<>();
	}
	
	public static <T> ApiResult<T> createSuccess(T data) {
		return new ApiResult<>(SUCCESS, SUCCESS_MSG, data);
	}
	
	public static <T> ApiResult<T> createSuccess(String msg, T data) {
		return new ApiResult<>(SUCCESS, msg, data);
	}
	
	public static <T> ApiResult<T> createError(String msg) {
		return new ApiResult<>(ERROR, msg, null);
	}
	
	public static <T> ApiResult<T> createError(T data) {
		return new ApiResult<>(ERROR, ERROR_MSG, data);
	}
	
	@JsonIgnore
	@Transient
	public boolean isSuccess() {
		return status == SUCCESS;
	}
	
	public ApiResult() {
		this.status = 1;
		this.msg = SUCCESS_MSG;
	}
	
	public ApiResult(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ApiResult{" + "status=" + status + ", msg='" + msg + '\'' + ", data=" + data + "}";
	}
	
}
