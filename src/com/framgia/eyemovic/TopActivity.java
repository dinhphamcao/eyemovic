package com.framgia.eyemovic;

import java.util.ArrayList;

import com.framgia.eyemovic.adapter.ListRewardAppAdapter;
import com.framgia.eyemovic.bean.RewardAppItem;
import com.framgia.eyemovic.common.AppConst;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class TopActivity extends Activity{
	
	private RelativeLayout mLayoutReward;
	private ListView mListRewardApp;
	private ImageButton mBtnCloseReward;
	private ListRewardAppAdapter mListAdapter;
	private ArrayList<RewardAppItem> mListRewardData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_layout);
		mListRewardData = new ArrayList<RewardAppItem>();
		initView();
	}
	
	private void initView(){
		mLayoutReward = (RelativeLayout)findViewById(R.id.layout_reward);
		mListRewardApp = (ListView)findViewById(R.id.list_reward_app);
		mBtnCloseReward = (ImageButton)findViewById(R.id.btn_reward_close);
		mBtnCloseReward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLayoutReward.setVisibility(View.GONE);
			}
		});
		
		initDataForListView();
	}
	
	private void initDataForListView(){
		RewardAppItem item = null;
		item = new RewardAppItem(R.drawable.re_play, R.drawable.re_play_txt, AppConst.MUSICJP_PLAYURL,AppConst.MUSICJP_PACKAGE);
		mListRewardData.add(item);
		
		item = new RewardAppItem(R.drawable.re_chaku, R.drawable.re_melo_txt,AppConst.MUSICJP_MELO_LITE_PLAYURL,AppConst.MUSICJP_MELO_LITE_PACKAGE);
		mListRewardData.add(item);
		
		item = new RewardAppItem(R.drawable.re_lr, R.drawable.re_lr_txt, AppConst.NET_RADIO_PLAYURL, AppConst.NET_RADIO_PACKAGE);
		mListRewardData.add(item);
		
		mListAdapter = new ListRewardAppAdapter(TopActivity.this, R.layout.list_reward_item, mListRewardData);
		mListRewardApp.setAdapter(mListAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkInstalledPackage();
	}
	
	private void checkInstalledPackage(){
		int size = mListRewardData.size();
		if(size <= 0){
			return;
		}
		
		for(int i=size-1;i>=0;i--){
			RewardAppItem item = mListRewardData.get(i);
			if(isAppInstalled(item.getAppPackageName())){
				mListRewardData.remove(i);
			}
		}
		
		if(mListRewardData.size() == size){
			mListAdapter.refreshList(mListRewardData);
		}
	}
	
	private boolean isAppInstalled(String packageName){
		PackageManager pm = getPackageManager();
		try{
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
		}catch(Exception e){
			Log.e("GetPackageInfo", "" + e.getMessage());
			return false;
		}
		
		return true;
	}
	
}
