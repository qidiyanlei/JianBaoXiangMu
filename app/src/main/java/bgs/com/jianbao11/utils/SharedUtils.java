package bgs.com.jianbao11.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import bgs.com.jianbao11.activity.Activity_FirstStart;
import bgs.com.jianbao11.activity.Activity_Guide;


/**
 *本类为sharedpreferences保存类    xml本地保存类
 *	 sharedpreferences：android 五大存储方式之一，存储数据类型为： K  V  文件已xml形式保存
 *					项目应用：1.导航页是否第一次进入
 *						    2.用户信息，登陆信息
 * 好处：只要不卸载软件，或者不手动清除，基本上不会被清除
 * */
public class SharedUtils extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String str=new SharedUtils().getShared("tag", SharedUtils.this);
		if(str.equals("")){
			Intent intent=new Intent(SharedUtils.this, Activity_Guide.class);
			startActivity(intent);
		}else {
			Intent intent=new Intent(SharedUtils.this, Activity_FirstStart.class);
			startActivity(intent);
		}
		finish();
	}

	private String name="jianbao";
	/*
	 * 保存数据的方法
	 * */
	public void saveShared(String key,String value,Context ctx){
		SharedPreferences shared=ctx.getSharedPreferences(name,0);
		Editor edit = shared.edit();
		edit.putString(key, value);
		edit.commit();
	}
	
	/*
	 * 从本地获取数据
	 * */
	public String getShared(String key,Context ctx){
		String str=null;
		SharedPreferences shared = ctx.getSharedPreferences(name, 0);
		str = shared.getString(key, "");
		return str;
	}
	
}
