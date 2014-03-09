package com.example.news.activity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.example.news.R;
import com.example.news.R.layout;
import com.example.news.R.menu;
import com.example.news.adapter.MainItemAdapter;
import com.example.news.control.JsonRequestManager;
import com.example.news.control.ResponseListener;
import com.example.news.control.ResponseObject;
import com.example.news.utils.Constants;
import com.example.news.utils.DialogUtil;
import com.example.news.vo.GoodsInfo;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HotsActivity extends Activity implements ResponseListener {
	public JsonRequestManager requestManager;
	DisplayImageOptions options;
	protected AbsListView listView;
	List<GoodsInfo> goodsInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_follow);
		requestManager = JsonRequestManager.getInstance(this);
		goodsInfo = new ArrayList<GoodsInfo>();
		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.resetViewBeforeLoading()
			.cacheOnDisc()
			.imageScaleType(ImageScaleType.EXACTLY)
			.cacheInMemory()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.displayer(new FadeInBitmapDisplayer(300))
			.build();
		
		listView = (ListView) findViewById(android.R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
		
		
		
		new Thread(runnable).start();
		
		
	}
	
	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, DetailMainActivity.class);
		intent.putExtra("title", goodsInfo.get(position).getTitle());
		intent.putExtra("modified", goodsInfo.get(position).getModified());
		intent.putExtra("pic_url", goodsInfo.get(position).getPic_url());
		intent.putExtra("price", goodsInfo.get(position).getPrice());
		intent.putExtra("seller_name", goodsInfo.get(position).getSeller_name());
		intent.putExtra("click_url", goodsInfo.get(position).getClick_url());
		intent.putExtra("TAG", Constants.recommend);
		startActivity(intent);
		finish();
	}
	
	Handler handler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
			if (msg.what ==100) {
				((ListView) listView).setAdapter(new MainItemAdapter(HotsActivity.this, goodsInfo, options));
			}
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        String val = data.getString("value");
	        
	    }
	};

	//请求网络
	Runnable runnable = new Runnable(){
	    @Override
	    public void run() {
	    	requestManager.getHotsList();
	        Message msg = new Message();
	        handler.sendMessage(msg);
	    }
	};

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onBackPressed() {
		DialogUtil.showExitDialog(HotsActivity.this);
	}

	@Override
	public void dataResponse(final ResponseObject responseObject) {
		String jsonString = responseObject.getResponseMessage();
		JSONArray jsArray = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject jsItem1 = jsonObject.optJSONObject("items_search");
			JSONObject jsItem2 = jsItem1.optJSONObject("items");
			jsArray = jsItem2.optJSONArray("item");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Gson gson = new Gson();
		 for(int i=0;i<jsArray.length();i++){
			 JSONObject jsItem = jsArray.optJSONObject(i);
			 GoodsInfo info = gson.fromJson(jsItem.toString(), GoodsInfo.class);
			 goodsInfo.add(info);
		 }
		 //显示列表
		 if (goodsInfo != null) {
			 handler.sendEmptyMessage(100);
		 }
		switch (responseObject.getType()) {
		case 1:
			break;
		default:
			break;
		}

	}

	@Override
	public void errorResponse(ResponseObject responseObject) {
		// TODO Auto-generated method stub
		
	}
}