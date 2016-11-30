package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.adapter.Adapter_viewpager;
import bgs.com.jianbao11.view.ViewPagerScroller;


/**
 * Created by 醇色 on 2016/11/28.
 */

public class Activity_GoodsDetail extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private List<View>list=new ArrayList<View>();
    private Adapter_viewpager adapter_vp;
    private int [] arr=new int[4];
    private int index_vp=0;
    private boolean autoChange=true;
    private boolean touchFlag=false;
    private ImageView mFrag_home_mImg1,mFrag_home_mImg2,mFrag_home_mImg3,mFrag_home_mImg4,Gshoucang_mimg;
    private TextView Gid,Gtitle,Gprice,Gdescription,Gmobile,Gwechat,Gqq,Gmail;
    private LinearLayout Gcollection,Gcall;
    private boolean isCollection=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        initList();
        initView();
        new Thread_vp().start();
    }

    private void initList() {
        for (int i=0;i<4;i++){

            ImageView img=new ImageView(this);
            img.setImageResource(R.drawable.a2);
            list.add(img);
            arr[i]=R.drawable.a2;
        }
    }

    private void initView() {
        mFrag_home_mImg1= (ImageView) findViewById(R.id.mFrag_home_mImg1);
        mFrag_home_mImg2= (ImageView) findViewById(R.id.mFrag_home_mImg2);
        mFrag_home_mImg3= (ImageView) findViewById(R.id.mFrag_home_mImg3);
        mFrag_home_mImg4= (ImageView) findViewById(R.id.mFrag_home_mImg4);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        adapter_vp=new Adapter_viewpager(list,this,arr);
        viewPager.setAdapter(adapter_vp);
        new ViewPagerScroller(this).initViewPagerScroll(viewPager);
        viewPager.setOnPageChangeListener(this);

        Gid= (TextView) findViewById(R.id.Gid);
        Gtitle= (TextView) findViewById(R.id.Gtitle);
        Gprice= (TextView) findViewById(R.id.Gprice);
        Gdescription= (TextView) findViewById(R.id.Gdiscription);
        Gmobile= (TextView) findViewById(R.id.Gmobile);
        Gwechat= (TextView) findViewById(R.id.Gwechat);
        Gqq= (TextView) findViewById(R.id.Gqq);
        Gmail= (TextView) findViewById(R.id.Gemail);
        Gcollection= (LinearLayout) findViewById(R.id.Gcollection);
        Gcall= (LinearLayout) findViewById(R.id.Gcall);
        Gshoucang_mimg= (ImageView) findViewById(R.id.Gshoucang_mImg);

        Gcollection.setOnClickListener(this);
        Gcall.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index_vp=position;

        cleanAllDoc();
        switch (position%list.size()){
            case 0:
                mFrag_home_mImg1.setImageResource(R.drawable.vote_n_can_y);
                break;
            case 1:
                mFrag_home_mImg2.setImageResource(R.drawable.vote_n_can_y);
                break;
            case 2:
                mFrag_home_mImg3.setImageResource(R.drawable.vote_n_can_y);
                break;
            case 3:
                mFrag_home_mImg4.setImageResource(R.drawable.vote_n_can_y);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state==0){
            touchFlag=false;
        }else{
            touchFlag=true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Gcollection:
               if (isCollection){
                   Gshoucang_mimg.setImageResource(R.drawable.yishoucang);
                   Toast.makeText(this,"已添加到收藏",Toast.LENGTH_SHORT).show();
                   //TODO 传值到收藏列表
                   isCollection=false;
               }else{
                   Gshoucang_mimg.setImageResource(R.drawable.shoucang);
                   Toast.makeText(this,"已取消收藏",Toast.LENGTH_SHORT).show();
                   isCollection=true;
               }
                break;
            case R.id.Gcall:
                String num="15711131735";
                //意图：想干什么事
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                //url:统一资源定位符
                //uri:统一资源标示符（更广）
                intent.setData(Uri.parse("tel:" + num));
                //开启系统拨号器
                startActivity(intent);
                break;
        }
    }

    private class Thread_vp extends Thread{
        @Override
        public void run() {
            super.run();
            while (autoChange){
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //vp自动轮播
            if (msg.what==1000){
                if (!autoChange)
                    return;
                if (touchFlag)
                    return;
                index_vp++;
                viewPager.setCurrentItem(index_vp);
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        autoChange=false;
    }
    /**
     * 清除所有小点的方法
     * */
    public void cleanAllDoc(){
        mFrag_home_mImg1.setImageResource(R.drawable.vote_n_can_n);
        mFrag_home_mImg2.setImageResource(R.drawable.vote_n_can_n);
        mFrag_home_mImg3.setImageResource(R.drawable.vote_n_can_n);
        mFrag_home_mImg4.setImageResource(R.drawable.vote_n_can_n);
    }
}
