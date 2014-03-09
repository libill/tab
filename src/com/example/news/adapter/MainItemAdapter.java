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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.vo.GoodsInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainItemAdapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	List<GoodsInfo> imageUrls;
	Context context;
	
	public MainItemAdapter(Context context, List<GoodsInfo> goodsInfo, DisplayImageOptions options){
		this.imageUrls = goodsInfo;
		this.context = context;
		this.options = options;
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}
	

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private class ViewHolder {
		public TextView text,tv_des,tv_price;
		public ImageView image;
		public LinearLayout  ll_item;
	}

	@Override
	public int getCount() {
		return imageUrls.size();
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
			holder.ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
			holder.text = (TextView) view.findViewById(R.id.tv_title);
			holder.tv_des = (TextView) view.findViewById(R.id.tv_des);
			holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
			holder.image = (ImageView) view.findViewById(R.id.im_pic);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.text.setText("["+imageUrls.get(position).getSeller_name() + "]" + "  "+imageUrls.get(position).getModified());
		holder.tv_des.setText(imageUrls.get(position).getTitle());
		holder.tv_price.setText("￥"+imageUrls.get(position).getPrice() +"元");

		imageLoader.displayImage(imageUrls.get(position).getPic_url(), holder.image, options, animateFirstListener);

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
