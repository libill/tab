package com.example.news.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainItemAdapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	String[] imageUrls;
	Context context;
	
	public MainItemAdapter(Context context, String[] imagesurls, DisplayImageOptions options){
		this.imageUrls = imagesurls;
		this.context = context;
		this.options = options;
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}
	

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private class ViewHolder {
		public TextView text;
		public ImageView image;
	}

	@Override
	public int getCount() {
		return imageUrls.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context); 
			view = inflater.inflate(R.layout.item_list_image, parent, false);
			holder = new ViewHolder();
			holder.text = (TextView) view.findViewById(R.id.text);
			holder.image = (ImageView) view.findViewById(R.id.image);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.text.setText("Item " + (position + 1));

		imageLoader.displayImage(imageUrls[position], holder.image, options, animateFirstListener);

		return view;
	}
}
class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if (loadedImage != null) {
			ImageView imageView = (ImageView) view;
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}
	}
}
