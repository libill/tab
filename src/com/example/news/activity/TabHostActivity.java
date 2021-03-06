package com.example.news.activity;

import static com.example.news.utils.Constants.Extra.IMAGES;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.news.IntroductionActivity;
import com.example.news.R;
import com.example.news.utils.Constants.Extra;
import com.example.news.utils.MoveBg;

public class TabHostActivity extends TabActivity {
	TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout bottom_layout;
	ImageView img;
	int startLeft;

	public static TextView tvTitle;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tabhost);
		bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);
		
		tvTitle = (TextView) findViewById(R.id.textTile);
		showHelp();

		tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News")
				.setContent(new Intent(this, MainActivity.class).putExtra(Extra.IMAGES, IMAGES)));
		tabHost.addTab(tabHost.newTabSpec("topic").setIndicator("Topic")
				.setContent(new Intent(this, HotsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("picture").setIndicator("Picture")
				.setContent(new Intent(this, FlashActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("follow").setIndicator("Follow")
				.setContent(new Intent(this, RecommendActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("vote").setIndicator("Vote")
				.setContent(new Intent(this, MoreActivity.class)));

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
				setTopTitle(R.string.app_name);
				tabHost.setCurrentTabByTag("news");
				// moveFrontBg(img, startLeft, 0, 0, 0);
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_topic:
				setTopTitle(R.string.topic_top_left_text);
				tabHost.setCurrentTabByTag("topic");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth(), 0, 0);
				startLeft = img.getWidth();
				break;
			case R.id.radio_pic:
				setTopTitle(R.string.news_top_left_text);
				tabHost.setCurrentTabByTag("picture");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 2, 0, 0);
				startLeft = img.getWidth() * 2;
				break;
			case R.id.radio_follow:
				setTopTitle(R.string.follow_top_left_text);
				tabHost.setCurrentTabByTag("follow");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 3, 0, 0);
				startLeft = img.getWidth() * 3;
				break;
			case R.id.radio_vote:
				setTopTitle(R.string.vote_top_left_text);
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
			Intent intent = new Intent(TabHostActivity.this,
					IntroductionActivity.class);
			startActivity(intent);
		}

		Editor editor = preferences.edit();
		// 存入数据
		editor.putInt("count", ++count);
		// 提交修改
		editor.commit();
	}
	
	public static void setTopTitle(String s){
		tvTitle.setText(s);
	}
	
	public static void setTopTitle(int i){
		tvTitle.setText(i);
	}

}
