package com.example.news.control;

/**
 * @author Bill 服务端响应监听
 */
public interface ResponseListener {
	public void dataResponse(ResponseObject responseObject);// 响应数据

	public void errorResponse(ResponseObject responseObject);// 响应错误
}
