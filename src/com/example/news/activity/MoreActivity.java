package com.example.news.activity;

import com.example.news.R;
import com.example.news.R.layout;
import com.example.news.R.menu;
import com.example.news.utils.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MoreActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onBackPressed() {
		DialogUtil.showExitDialog(MoreActivity.this);
	}
}