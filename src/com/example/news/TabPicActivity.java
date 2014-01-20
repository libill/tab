package com.example.news;

import com.example.news.utils.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class TabPicActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_pic);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onBackPressed() {
		DialogUtil.showExitDialog(TabPicActivity.this);
	}
}