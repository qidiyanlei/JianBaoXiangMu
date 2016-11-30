package bgs.com.jianbao11.jianbao;

import android.app.Application;

import bgs.com.jianbao11.utils.ImageLoader;


public class MyAppalication extends Application{
	public ImageLoader imageLoader;
	@Override
	public void onCreate() {
		super.onCreate();
		imageLoader=new ImageLoader();
	}
}
