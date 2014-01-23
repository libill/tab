package com.example.news.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

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
	private ArrayList<Integer> pages;

	private static final String STATE_POSITION = "STATE_POSITION";
	DisplayImageOptions options;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		Bundle bundle = getIntent().getExtras();
		String[] imageUrls = bundle.getStringArray(Extra.IMAGES);
		// String[] imageUrls = { "http://www.baidu.com/img/bdlogo.gif",
		// "http://www.google.com.hk/images/srpr/logo11w.png" };

		int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		pages = new ArrayList<Integer>();
		pages.add(R.drawable.introduction_1);
		pages.add(R.drawable.introduction_2);
		showPager = (ViewPager) findViewById(R.id.showPager);
		newsAdapter = new TabNewsAdapter(TabNewsActivity.this, imageUrls);
		showPager.setAdapter(newsAdapter);
		showPager.setCurrentItem(pagerPosition);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(showPager);

	}

	public void onBackPressed() {
		DialogUtil.showExitDialog(TabNewsActivity.this);
	}
}