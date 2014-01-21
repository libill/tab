package com.example.news.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.news.R;
import com.example.news.adapter.TabNewsAdapter;
import com.example.news.utils.DialogUtil;
import com.example.news.view.CirclePageIndicator;

public class TabNewsActivity extends Activity {
	private ViewPager showPager;
	private TabNewsAdapter newsAdapter;
	private CirclePageIndicator mIndicator;
	private ArrayList<Integer> pages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		pages = new ArrayList<Integer>();
		pages.add(R.drawable.introduction_1);
		pages.add(R.drawable.introduction_2);
		showPager = (ViewPager) findViewById(R.id.showPager);
		newsAdapter = new TabNewsAdapter(TabNewsActivity.this, pages);
		showPager.setAdapter(newsAdapter);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(showPager);
		
	}

	public void onBackPressed() {
		DialogUtil.showExitDialog(TabNewsActivity.this);
	}
}