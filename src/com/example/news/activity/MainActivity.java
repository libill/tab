package com.example.news.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.news.IntroductionActivity;
import com.example.news.R;
import com.example.news.R.drawable;
import com.example.news.R.id;
import com.example.news.R.layout;
import com.example.news.utils.MoveBg;

public class MainActivity extends TabActivity {
	TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout bottom_layout;
	ImageView img;
	int startLeft;

	TextView tvTitle;
	SharedPreferences preferences;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_bottom);
		bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);
		
		tvTitle = (TextView) findViewById(R.id.textTile);
		showHelp();

		tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News")
				.setContent(new Intent(this, TabNewsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("topic").setIndicator("Topic")
				.setContent(new Intent(this, TabTopicActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("picture").setIndicator("Picture")
				.setContent(new Intent(this, TabPicActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("follow").setIndicator("Follow")
				.setContent(new Intent(this, TabFollowActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("vote").setIndicator("Vote")
				.setContent(new Intent(this, TabVoteActivity.class)));

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);

		img = new ImageView(this);
		img.setImageResource(R.drawable.tab_front_bg);
		bottom_layout.addView(img);
	}

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tvTitle.setText("news");
				tabHost.setCurrentTabByTag("news");
				// moveFrontBg(img, startLeft, 0, 0, 0);
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_topic:
				tvTitle.setText("topic");
				tabHost.setCurrentTabByTag("topic");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth(), 0, 0);
				startLeft = img.getWidth();
				break;
			case R.id.radio_pic:
				tvTitle.setText("picture");
				tabHost.setCurrentTabByTag("picture");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 2, 0, 0);
				startLeft = img.getWidth() * 2;
				break;
			case R.id.radio_follow:
				tvTitle.setText("follow");
				tabHost.setCurrentTabByTag("follow");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 3, 0, 0);
				startLeft = img.getWidth() * 3;
				break;
			case R.id.radio_vote:
				tvTitle.setText("vote");
				tabHost.setCurrentTabByTag("vote");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 4, 0, 0);
				startLeft = img.getWidth() * 4;
				break;

			default:
				break;
			}
		}
	};

	public void showHelp() {
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		int count = preferences.getInt("count", 0);
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导页面
		if (count == 0) {
			Intent intent = new Intent(MainActivity.this,
					IntroductionActivity.class);
			startActivity(intent);
		}

		Editor editor = preferences.edit();
		// 存入数据
		editor.putInt("count", ++count);
		// 提交修改
		editor.commit();
	}

}