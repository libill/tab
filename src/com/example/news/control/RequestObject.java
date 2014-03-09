package com.example.news.control;


/**
 * @author Bill
 *	封装请求数据
 */
public class RequestObject {
	/**
	 * http路径
	 */
	public String url = "";
	/**
	 * 具体的xml/json
	 */
	public String data = "";
	/**
	 * 标记具体请求
	 */
	public int type = -1;
	
	public RequestObject(String url, String data, int type){
		this.url = url;
		this.data = data;
		this.type = type;
	}
	
}

