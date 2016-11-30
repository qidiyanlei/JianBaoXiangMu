package bgs.com.jianbao11.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件处理类：本地存储    ：  1.本地存储图片（内部存储）    读取本地的图片
 * 					  2.本地存储.txt文件（方便取出json字符串）  本地读取.txt文件
 * */
public class FileUtils {
	private Context ctx;

	public FileUtils(Context ctx) {
		this.ctx=ctx;
	}
	/*
	 * 判断SD卡是否存在的方法 
	 * */
	private boolean IsSD(){
		//判断sdk是否存在
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}

	/*
	 * 获取存储图片的路径
	 * */
	private String getFile(){
		String str=null;
		//获取内部存储的路径
		str=ctx.getExternalCacheDir().getAbsolutePath();
		return str;
	}
	/*
	 * 本地存储图片的方法
	 * */
	public void SaveBitmap(String name,Bitmap bitmap){
		//如果SD卡不存在，直接return
		if(!IsSD()){
			return;
		}
		String path=getFile()+"/"+name;

		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		File file=new File(path);
		
		//图片压缩:指的是图片在本地存储时的压缩，不会影响图片的大小，宽高
		bitmap.compress(CompressFormat.PNG, 100, bos);
		try {
			FileOutputStream os=new FileOutputStream(file);
			os.write(bos.toByteArray());
			os.flush();
			os.close();
			bos.close();
			Log.e("", "aaaaaaaa"+path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 从本地SD卡中取出图片的方法
	 * */
	public Bitmap getBitmap(String name){
		Bitmap bitmap=null;
		if(!IsSD()){
			return bitmap;
		}
		String path=getFile()+"/"+name;
		bitmap=BitmapFactory.decodeFile(path);
		Log.e("", "bbbbbbbbbbb"+path);
		return bitmap;
	}
}
