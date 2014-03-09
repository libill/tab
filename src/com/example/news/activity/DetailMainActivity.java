package com.example.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class DetailMainActivity extends Activity {
	
	ImageView iv_return,iv_pic_url,iv_click_url;
	TextView tv_Tile,tv_price,tv_seller_name;
	String TAG;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_main_activity);
		iv_return = (ImageView) findViewById(R.id.iv_return);
		iv_pic_url = (ImageView) findViewById(R.id.iv_pic_url);
		iv_click_url = (ImageView) findViewById(R.id.iv_click_url);
		
		tv_Tile = (TextView) findViewById(R.id.tv_Tile);
		tv_price = (TextView) findViewById(R.id.tv_price);
		tv_seller_name = (TextView) findViewById(R.id.tv_seller_name);
		
		Bundle bundle = this.getIntent().getExtras();
		String title=bundle.getString("title");
		String pic_url=bundle.getString("pic_url");
		String price=bundle.getString("price");
		String seller_name = bundle.getString("seller_name");
		TAG = bundle.getString("TAG");
		final String click_url = bundle.getString("click_url");
		
		tv_Tile.setText(title);
		tv_price.setText("价格：￥"+price +"元");
		tv_seller_name.setText("商家："+seller_name);
		iv_click_url.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if ("".equals(TAG)){
					
				}
				Intent intent = new Intent(DetailMainActivity.this, WebViewActivity.class);
				intent.putExtra("click_url", click_url);
				intent.putExtra("TAG", TAG);
				startActivity(intent);
				finish();
			}
			
		});
		
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.resetViewBeforeLoading()
			.cacheOnDisc()
			.imageScaleType(ImageScaleType.EXACTLY)
			.cacheInMemory()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.displayer(new FadeInBitmapDisplayer(300))
			.build();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		imageLoader.displayImage(pic_url, iv_pic_url, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				String message = null;
				switch (failReason.getType()) {
					case IO_ERROR:
						message = "Input/Output error";
						break;
					case DECODING_ERROR:
						message = "Image can't be decoded";
						break;
					case NETWORK_DENIED:
						message = "Downloads are denied";
						break;
					case OUT_OF_MEMORY:
						message = "Out Of Memory error";
						break;
					case UNKNOWN:
						message = "Unknown error";
						break;
				}

			}

			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			}
		});
		
		goToReturn();
	}
	
	public void goToReturn(){
		iv_return.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(DetailMainActivity.this, TabHostActivity.class);
				startActivity(intent2);
				finish();
			}
			
		});
	}

	public void onBackPressed() {
		Intent intent = new Intent(this, TabHostActivity.class);
		startActivity(intent);
		finish();
	}
}