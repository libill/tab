package com.example.news;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.news.view.CirclePageIndicator;

public class IntroductionActivity extends Activity {
	
	public class IntroductionPagerAdapter extends PagerAdapter{
		
		private ArrayList<Integer> pages;
		
		public IntroductionPagerAdapter(){
			pages = new ArrayList<Integer>();
			pages.add(R.drawable.introduction_1);
			pages.add(R.drawable.introduction_2);
			pages.add(R.drawable.introduction_3);
			pages.add(R.drawable.introduction_4);
		}

		public int getCount() {
			return pages.size();
		}

		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}
		
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(IntroductionActivity.this);
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
							IntroductionActivity.this.finish();
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
	
	private ViewPager mPager;
	private CirclePageIndicator mIndicator;
	private IntroductionPagerAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduction);

		mPager = (ViewPager) findViewById(R.id.pager);
		adapter = new IntroductionPagerAdapter();
		mPager.setAdapter(adapter);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		findViewById(R.id.skip_iv).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						IntroductionActivity.this.finish();
					}
				});
	}
	
}
