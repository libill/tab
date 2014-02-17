package com.example.news.activity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.news.R;
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
			"http://www.soso.com/soso/images/logo_index.png" };
	private static final String STATE_POSITION = "STATE_POSITION";
	DisplayImageOptions options;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

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

	public void onBackPressed() {
		DialogUtil.showExitDialog(TabNewsActivity.this);
	}
}