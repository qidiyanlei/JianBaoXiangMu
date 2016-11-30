package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.utils.Img_SuoFangUtils;


/**
 * Created by 醇色 on 2016/11/28.
 */

public class Activity_Scal extends Activity {
    private Img_SuoFangUtils img_suoFangUtils;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scal);
        initView();
    }

    private void initView() {
        img_suoFangUtils = (Img_SuoFangUtils) findViewById(R.id.mSCImg);
        int i = getIntent().getIntExtra("i", 0);
        bitmap= BitmapFactory.decodeResource(getResources(),i);
        img_suoFangUtils.setBitmap(bitmap);
    }
}
