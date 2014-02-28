package com.example.news.activity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.example.news.utils.Constants.Extra;
import com.example.news.utils.DialogUtil;
import com.example.news.view.CirclePageIndicator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class TabNewsActivity extends Activity {
	private ViewPager showPager;
	private TabNewsAdapter newsAdapter;
	private CirclePageIndicator mIndicator;
	int pagerPosition;
	Timer timerSchedule;
	private AtomicInteger what = new AtomicInteger(0);
	String[] imageUrls = { "http://www.baidu.com/img/bdlogo.gif",
			"http://www.google.com.hk/images/srpr/logo11w.png",
			"http://p0.qhimg.com/t012405121172f4352c.png",
			"http://www.sogou.com/images/logo_l2.gif",
			"http://www.google.com.hk/images/srpr/logo11w.png",
			"http://p0.qhimg.com/t012405121172f4352c.png",
			"http://www.sogou.com/images/logo_l2.gif",
			"http://www.soso.com/soso/images/logo_index.png" };
	private static final String STATE_POSITION = "STATE_POSITION";
	DisplayImageOptions options;
	
	protected AbsListView listView;
	RelativeLayout rlFocusPic;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		setFocusPicShow();

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
		newsAdapter = new TabNewsAdapter(TabNewsActivity.this, imageUrls, options);
		showPager.setAdapter(newsAdapter);
		showPager.setCurrentItem(pagerPosition);

		startDisplayTimer();

		showPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				stopDisplayTimer();
				return false;
			}

		});

		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(showPager);

		
		//MainItem
		listView = (ListView) findViewById(android.R.id.list);
		((ListView) listView).setAdapter(new MainItemAdapter(TabNewsActivity.this, imageUrls, options));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
	}
	
	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, TabNewsActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	private final Handler viewHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			showPager.setCurrentItem(msg.what);
			Log.i("msgg", msg.what + "");
			super.handleMessage(msg);
		}
	};
	
	public void startDisplayTimer(){
		timerSchedule = new Timer();
		
		timerSchedule.schedule(new TimerTask() {

			@Override
			public void run() {
				if (imageUrls.length - 1 < pagerPosition) {
					pagerPosition = 0;
				}
				viewHandler.sendEmptyMessage(pagerPosition);
				pagerPosition++;
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
		MainActivity.tvTitle.setOnClickListener(new OnClickListener(){

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
		DialogUtil.showExitDialog(TabNewsActivity.this);
	}
}