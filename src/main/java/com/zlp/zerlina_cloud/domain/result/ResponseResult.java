package com.zlp.zerlina_cloud.domain.result;

public class ResponseResult {
	/** 返回信息码*/
	private String rspCode="000000";
	/** 返回信息内容*/
	private String rspMsg="操作成功";

	public ResponseResult() {
	}
	
	public ResponseResult(ExceptionMsg msg){
		this.rspCode=msg.getCode();
		this.rspMsg=msg.getMsg();
	}
	
	public ResponseResult(String rspCode) {
		this.rspCode = rspCode;
		this.rspMsg = "";
	}

	public ResponseResult(String rspCode, String rspMsg) {
		this.rspCode = rspCode;
		this.rspMsg = rspMsg;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	@Override
	public String toString() {
		return "ResponseResult{" +
				"rspCode='" + rspCode + '\'' +
				", rspMsg='" + rspMsg + '\'' +
				'}';
	}
}
