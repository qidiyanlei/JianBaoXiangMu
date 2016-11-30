package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.utils.SharedUtils;


/**
 * Created by Administrator on 2016/11/28.
 */

public class Activity_Guide extends Activity{
    private ViewPager mVp;
    private ImageView mlog_Img;
    private Button mlog_btn;
    private int [] arr={R.mipmap.w1,R.mipmap.w2,R.mipmap.w3,R.mipmap.w4};
    private List<View> list= new ArrayList<View>();
   private String tag="guide";
    private SharedUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
       utils=new SharedUtils();
        utils.saveShared("tag", tag, this);
        initList();
        initView();

    }
    /*
    * 初始化数据源
    * */
    private void initView() {
        mVp=(ViewPager) findViewById(R.id.mVp);

        mVp.setOnPageChangeListener(new OnPageChangeListener() {
            //正在显示的方法
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 3:
                        mlog_btn.setVisibility(View.VISIBLE);
                        mlog_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(Activity_Guide.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                }

            }
            //viewpager的滑动的监听的方法
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            // 监听状态改变的方法
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        mVp.setAdapter(new MyAdapter());
    }
    //初始化数据源
    private void initList(){
        for (int i = 0; i < arr.length; i++) {
            View v=View.inflate(Activity_Guide.this, R.layout.activity_loginview, null);
            mlog_Img=(ImageView)v.findViewById(R.id.mlog_Img);
            mlog_btn= (Button)v.findViewById(R.id.mlog_btn);
            mlog_Img.setImageResource(arr[i]);
            list.add(v);
        }
    }
    //内部类适配器
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }
        //判断正在显示的view是否在缓存中
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0==arg1;
        }
        //销毁view的方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(list.get(position));
        }
        //添加view的方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
