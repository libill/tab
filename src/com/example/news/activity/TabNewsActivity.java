package com.example.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.news.R;
import com.example.news.R.layout;
import com.example.news.R.menu;
import com.example.news.utils.DialogUtil;

public class TabNewsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
	}

	public void onBackPressed() {
		DialogUtil.showExitDialog(TabNewsActivity.this);
	}
}