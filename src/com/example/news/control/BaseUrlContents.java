package com.example.news.control;

public class BaseUrlContents {

	public static String COMMON_URL_HEADER = "http://www.cstudents.net";
	//首页列表
	public static String PROMOS_LISTP = "/1/demo/59miao.items.search.php";
	//时尚
	public static String FLASH_LIST="/1/demo/flash.php";
	//热门
	public static String HOTS_LIST="/1/demo/hots.php";
	//推荐
	public static String RECOMMEND_LIST = "/1/demo/recommend.php";
	
	
	
	//京东
	public static String JD_LIST = "/1/demo/jd.php";
	
	public static String getUrl(String URL) {
		return COMMON_URL_HEADER + URL;
	}

}
