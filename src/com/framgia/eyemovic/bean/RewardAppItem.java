package com.framgia.eyemovic.bean;

public class RewardAppItem {
	private int appLogoId;
	private int appDescriptionId;
	private String appGooglePlayLink;
	private String appPackageName;
	
	public RewardAppItem(int appId,int appDes,String playLink,String packageName){
		this.appLogoId = appId;
		this.appDescriptionId = appDes;
		this.appGooglePlayLink = playLink;
		this.appPackageName = packageName;
	}
	
	public void setAppLink(String link){
		this.appGooglePlayLink = link;
	}
	
	public String getAppLink(){
		return this.appGooglePlayLink;
	}
	
	public int getAppLogoId(){
		return this.appLogoId;
	}
	
	public int getAppDescriptionId(){
		return this.appDescriptionId;
	}
	
	public String getAppPackageName(){
		return this.appPackageName;
	}
}
