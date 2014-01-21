package com.example.news.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TabNewsAdapter extends PagerAdapter {
	
	private ArrayList<Integer> pages;
	Context context;
	
	public TabNewsAdapter(Context context, ArrayList<Integer> pages){
		this.pages = pages;
		this.context = context;
	}

	public int getCount() {
		return pages.size();
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
		imageView.setImageResource(pages.get(position));
		
		if (position == pages.size() - 1) {
			imageView.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (MotionEvent.ACTION_DOWN == event.getAction()) {
						return true;
					}
					return false;
				}
			});
		}

		((ViewPager) container).addView(imageView);
		return imageView;

	}

	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

}
