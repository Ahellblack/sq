package com.siti.wisdomhydrologic.util.enumbean;

import com.siti.wisdomhydrologic.util.ExceptionUtil;

/**
 * @author DC
 */
public enum EquimentError  {

	CAL_ERROR("wh001","数据计算服务器故障"),
	SENSOR_ERROR("wh002","数据上传故障故障"),
	ELE_ERROR("wh003","端电故障");
	private String errorCode;
	private String errorMsg;

	EquimentError(String errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[").append(errorCode);
		buffer.append("]").append(errorMsg);
		return buffer.toString();
	}

}
