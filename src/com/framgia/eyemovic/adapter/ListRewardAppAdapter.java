package com.framgia.eyemovic.adapter;

import java.util.ArrayList;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.framgia.eyemovic.R;
import com.framgia.eyemovic.bean.RewardAppItem;
import com.framgia.eyemovic.common.AppConst;

public class ListRewardAppAdapter extends ArrayAdapter<RewardAppItem>{
	private Context mContext;
	private int layoutResourceId;
	private LayoutInflater layoutInflater;
	private ArrayList<RewardAppItem> mListItem;
	
	public ListRewardAppAdapter(Context context,int layoutId,ArrayList<RewardAppItem> listItem){
		super(context, layoutId, listItem);
		this.layoutResourceId = layoutId;
		this.mContext = context;
		this.mListItem = listItem;
		layoutInflater = LayoutInflater.from(context);
	}

	public void refreshList(ArrayList<RewardAppItem> listItem){
		this.mListItem = listItem;
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		final RewardAppItem item = mListItem.get(position);
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(layoutResourceId, parent, false);
			viewHolder.imgViewLogo = (ImageView)convertView.findViewById(R.id.img_reward_app_logo);
			viewHolder.imgViewDescription = (ImageView)convertView.findViewById(R.id.img_description);
			viewHolder.btnDownload = (ImageButton)convertView.findViewById(R.id.btn_download_reward);
			viewHolder.appLink = "";
			viewHolder.appPackage = "";
			
			viewHolder.btnDownload.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
						mContext.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(AppConst.GOOGLE_PLAY_INTENT_PREFIX + item.getAppPackageName())));
					}catch(ActivityNotFoundException e){
						mContext.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(AppConst.GOOGLE_PLAY_HTTP_PREFIX + item.getAppPackageName())));
					}catch(Exception e){
						return;
					}
					
				}
			});
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), item.getAppLogoId());
		if(android.os.Build.VERSION.SDK_INT >= 16){
			viewHolder.imgViewLogo.setBackground(new BitmapDrawable(mContext.getResources(), bmp));
		}else{
			viewHolder.imgViewLogo.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), bmp));
		}
		
		viewHolder.imgViewDescription.setImageResource(item.getAppDescriptionId());
		viewHolder.appLink = item.getAppLink();
		viewHolder.appPackage = item.getAppPackageName();
		return convertView;
	}
	
	class ViewHolder {
		private ImageView imgViewLogo;
		private ImageView imgViewDescription;
		private ImageButton btnDownload;
		private String appLink;
		private String appPackage;
	}
	
}
