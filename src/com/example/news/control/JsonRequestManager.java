package com.example.news.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.news.utils.Constants;
import com.google.gson.Gson;



public class JsonRequestManager {
	private static JsonRequestManager requestManager = null;
	private static HttpClient httpClient = null;
	ExecutorService service = null;//线程池
	ResponseListener listener = null;
	private String curTag = "";
	
	Gson gson = null;
	Context context;
	int THREAD_LIMIT = 5;//线程数量限制

	
	private static final String KEY_RESPONSE_CODE = "responseCode";
	private static final String KEY_RESPONSE_MESSAGE = "responseMessage";
	private static final String KEY_CARD_ID = "cardId";
	
	public static JsonRequestManager getInstance(ResponseListener listener){
		if(requestManager == null){
			requestManager = new JsonRequestManager();
		}
		requestManager.listener = listener;
		return requestManager;
	}
	
	public JsonRequestManager(){
		if(service == null){
			service = Executors.newFixedThreadPool(THREAD_LIMIT);
		}
		if(httpClient == null){
			//获取多线程安全的HttpClient
			HttpParams params = new BasicHttpParams(); 
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); 
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8); 
			HttpProtocolParams.setUseExpectContinue(params, true); 
			SchemeRegistry schReg = new SchemeRegistry(); 
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80)); 
			schReg.register(new Scheme("https",SSLSocketFactory.getSocketFactory(), 443));
			ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, schReg); 
			httpClient = new DefaultHttpClient(connMgr, params);
		}
		if(gson == null){
			gson = new Gson();
		}
	}
	
	/**
	 * 获取主页列表
	 */
	public void getPromosList(){
		RequestObject object = new RequestObject(BaseUrlContents.getUrl(BaseUrlContents.PROMOS_LISTP), 
				"", Constants.REQUEST_PROMOS_LISTP);
		httpGet(object);
	}
	
	/**
	 * 获取推荐列表
	 */
	public void getRecommendList(){
		RequestObject object = new RequestObject(BaseUrlContents.getUrl(BaseUrlContents.RECOMMEND_LIST), 
				"", Constants.REQUEST_PROMOS_LISTP);
		httpGet(object);
	}
	
	/**
	 * 获取时尚列表
	 */
	public void getFlashList(){
		RequestObject object = new RequestObject(BaseUrlContents.getUrl(BaseUrlContents.FLASH_LIST), 
				"", Constants.REQUEST_PROMOS_LISTP);
		httpGet(object);
	}
	
	/**
	 * 获取热门列表
	 */
	public void getHotsList(){
		RequestObject object = new RequestObject(BaseUrlContents.getUrl(BaseUrlContents.HOTS_LIST), 
				"", Constants.REQUEST_PROMOS_LISTP);
		httpGet(object);
	}
	
	/**
	 * Get方式http请求
	 * @param object
	 */
	private synchronized void httpGet(RequestObject object){
		final String url = object.url;
		Log.i("123:","123");
		Log.i("request:",url);
		final int type = object.type;
			HttpGet request = new HttpGet(url);
				try {
					HttpResponse response = httpClient.execute(request);
					String result = EntityUtils.toString(response.getEntity());
					
					String r = "";
			        String a[] = result.split("[{]"); 
			        for (int i = 1;i<a.length;i++) {
			        	r = r+"{"+a[i];
			        }
			        result = r;
			        
					Log.w(curTag+"Get:",result);
					listener.dataResponse(getResponseObject(type, result));
				} catch (Exception e) {
					e.printStackTrace();
					listener.errorResponse(new ResponseObject(type,Constants.REQUEST_IO_ERROR,null));
					Log.w(curTag+"Get:","------response exception---------");
				}
	}
	
	private ResponseObject getResponseObject(int type, String result){
		ResponseObject responseObject = null;
		JSONObject jsonObject = null;
		Gson gson = new Gson();
		try {
			jsonObject = new JSONObject(result);
			//每个response都包含code和message
			responseObject = new ResponseObject(type,1, 
					result);
			//下面根据每种类型分情况
			switch (type) {
			case 1:
				break;
			default:
				break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.w("","------Response 解析错误--------");
		}
		return responseObject;
	}

}
