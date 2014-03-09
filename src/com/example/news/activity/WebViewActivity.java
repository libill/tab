package com.example.news.activity;

import com.example.news.R;
import com.example.news.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	WebView mWebView;
	ImageView iv_return;
	String tag;
	float xDown, yDown, xUp, yUp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		mWebView = (WebView) findViewById(R.id.webview);
		
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new SampleWebViewClient());
		mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);        
        mWebView.getSettings().setSupportZoom(true);                                                                    
        mWebView.getSettings().setBuiltInZoomControls(true); 
        mWebView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
		mWebView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//Log.i("ontouch", "12313131");
				return false;
			}
			
		});
		Bundle bundle = this.getIntent().getExtras();
		String click_url=bundle.getString("click_url");
		tag = bundle.getString("TAG");
		mWebView.loadUrl(click_url);
		goToReturn();
	}
	
	private static class SampleWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
	
	/**
	 * 点击返回物理键，返回上一个网页记录
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	
	public void goToReturn(){
		iv_return = (ImageView) findViewById(R.id.iv_return);
		iv_return.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = null;
				if (Constants.recommend.equals(tag)){
					intent2= new Intent(WebViewActivity.this, TabHostActivity.class);
				} else {
					intent2= new Intent(WebViewActivity.this, TabHostActivity.class);
				}
				
				startActivity(intent2);
				finish();
			}
			
		});
	}
	
}