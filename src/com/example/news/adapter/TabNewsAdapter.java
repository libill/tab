package com.example.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class TabNewsAdapter extends PagerAdapter {
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String[] images;
	Context context;
	
	public TabNewsAdapter(Context context, String[] images){
		this.images = images;
		this.context = context;
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}

	public int getCount() {
		return images.length;
	}
	
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}
	
	public Object instantiateItem(ViewGroup container, int position){
		ImageView imageView = new ImageView(context);
		imageView.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
		
		imageView.setScaleType(ScaleType.FIT_XY);
		
		imageLoader.displayImage(images[position], imageView, options, new SimpleImageLoadingListener() {
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
		
		((ViewPager) container).addView(imageView);
		return imageView;

	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}
}
