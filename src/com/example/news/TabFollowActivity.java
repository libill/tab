package com.example.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.news.utils.DialogUtil;

public class TabFollowActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_follow);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onBackPressed() {
		DialogUtil.showExitDialog(TabFollowActivity.this);
	}
}