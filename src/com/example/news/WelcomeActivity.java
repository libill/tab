package com.example.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WelcomeActivity extends Activity {
	private final int MSG_TO_MAIN = 1;
	
	Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			if (msg.what == MSG_TO_MAIN) {
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		mHandler.sendEmptyMessageDelayed(MSG_TO_MAIN, 2000);
	}
}
