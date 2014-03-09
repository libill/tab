package com.example.news.activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.news.R;
import com.example.news.adapter.MainItemAdapter;
import com.example.news.adapter.TabNewsAdapter;
import com.example.news.control.JsonRequestManager;
import com.example.news.control.ResponseListener;
import com.example.news.control.ResponseObject;
import com.example.news.utils.Constants.Extra;
import com.example.news.utils.DialogUtil;
import com.example.news.view.CirclePageIndicator;
import com.example.news.vo.GoodsInfo;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainActivity extends Activity implements ResponseListener{
	public JsonRequestManager requestManager;
	private ViewPager showPager;
	private TabNewsAdapter newsAdapter;
	private CirclePageIndicator mIndicator;
	int pagerPosition;
	Timer timerSchedule;
	List<GoodsInfo> goodsInfo;
	List<Map<String, Object>> goodsInfoList;
	private AtomicInteger what = new AtomicInteger(0);
	String[] imageUrls = { "http://img.59miao.com/sp/15001/20140304094341_750x300.jpg",
			"http://img.59miao.com/sp/1018/20140305100210_750x300.jpg",
			"http://img.59miao.com/sp/33304/20140305111803_750x300.jpg",
			"http://img.59miao.com/sp/1018/20140306093105_750x300.jpg",
			"http://img.59miao.com/sp/1151/20140306093218_750x300.jpg" };
	private static final String STATE_POSITION = "STATE_POSITION";
	DisplayImageOptions options;
	
	protected AbsListView listView;
	RelativeLayout rlFocusPic;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		goodsInfo = new ArrayList<GoodsInfo>();
//		setFocusPicShow();

		Bundle bundle = getIntent().getExtras();

		pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

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

		showPager = (ViewPager) findViewById(R.id.showPager);
		newsAdapter = new TabNewsAdapter(MainActivity.this, imageUrls, options);
		showPager.setAdapter(newsAdapter);
		showPager.setCurrentItem(pagerPosition);

		startDisplayTimer();

		showPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//stopDisplayTimer();
				return false;
			}

		});

		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(showPager);
		
		requestManager = JsonRequestManager.getInstance(this);
		new Thread(runnable).start();
		
		//MainItem
		listView = (ListView) findViewById(android.R.id.list);
		//((ListView) listView).setAdapter(new MainItemAdapter(TabNewsActivity.this, imageUrls, options));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
	}
	
	//请求网络
	Runnable runnable = new Runnable(){
	    @Override
	    public void run() {
	        //
	        // TODO: http request.
	        //
	    	requestManager.getPromosList();
	    }
	};
	
	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, DetailMainActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		
		intent.putExtra("title", goodsInfo.get(position).getTitle());
		intent.putExtra("modified", goodsInfo.get(position).getModified());
		intent.putExtra("pic_url", goodsInfo.get(position).getPic_url());
		intent.putExtra("price", goodsInfo.get(position).getPrice());
		intent.putExtra("seller_name", goodsInfo.get(position).getSeller_name());
		intent.putExtra("click_url", goodsInfo.get(position).getClick_url());
		
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
		finish();
	}

	private final Handler viewHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what ==100) {
				((ListView) listView).setAdapter(new MainItemAdapter(MainActivity.this, goodsInfo, options));
			} else {
				showPager.setCurrentItem(msg.what);
			}
			super.handleMessage(msg);
		}
	};
	
	public void startDisplayTimer(){
		timerSchedule = new Timer();
		
		timerSchedule.schedule(new TimerTask() {

			@Override
			public void run() {
				pagerPosition = showPager.getCurrentItem()+1;
				if (imageUrls.length - 1 < pagerPosition) {
					pagerPosition = 0;
				}
				viewHandler.sendEmptyMessage(pagerPosition);
				
			}
		}, 0, 5000);
	}
	
	public void stopDisplayTimer(){
		if (timerSchedule != null) {
			timerSchedule.cancel();
		}
	}
	
	/**
	 * 控制幻灯片是否显示
	 */
	public void setFocusPicShow(){
		rlFocusPic = (RelativeLayout) findViewById(R.id.focusPic);
		TabHostActivity.tvTitle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (i % 2 == 0) {
					rlFocusPic.setVisibility(View.GONE);
				} else {
					rlFocusPic.setVisibility(View.VISIBLE);
				}
				i++;
			}
			
		});
	}

	@Override
	public void onBackPressed() {
		DialogUtil.showExitDialog(MainActivity.this);
	}

	@Override
	public void dataResponse(ResponseObject responseObject) {
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
			 viewHandler.sendEmptyMessage(100);
		 }
		//((ListView) listView).setAdapter(new MainItemAdapter(TabNewsActivity.this, goodsInfo, options));
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