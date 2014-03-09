package com.example.news.control;

/**
 * @author Bill
 *	封装解析完的数据
 */
public class ResponseObject {
	private int type;
	private int responseCode;
	private String responseMessage;
	private Object obj;
	private String arg1;
	private String arg2;
	private String arg3;
	private String arg4;
	private String arg5;

	public String getArg4() {
		return arg4;
	}

	public void setArg4(String arg4) {
		this.arg4 = arg4;
	}

	public String getArg5() {
		return arg5;
	}

	public void setArg5(String arg5) {
		this.arg5 = arg5;
	}

	public ResponseObject() {
		super();
	}
	
	public ResponseObject(int type){
		this.type = type;
	}
	
	public ResponseObject(int type,int responseCode, String responseMessage) {
		super();
		this.type = type;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getArg3() {
		return arg3;
	}

	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}
	
}
