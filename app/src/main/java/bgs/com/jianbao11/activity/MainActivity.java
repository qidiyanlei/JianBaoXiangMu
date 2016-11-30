package bgs.com.jianbao11.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.fragment.Fragment_Collection;
import bgs.com.jianbao11.fragment.Fragment_Home;
import bgs.com.jianbao11.fragment.MineFragment;
import bgs.com.jianbao11.fragment.Fragment_Want;


/**
 * 主页面
 * */
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {
    private ImageView mFrag_home1,mFrag_want1,mFrag_push,mFrag_colection1,mFrag_mine1;
    private TextView mFrag_home2,mFrag_want2,mFrag_colection2,mFrag_mine2;
    private LinearLayout mFrag_home,mFrag_want,mFrag_collection,mFrag_mine,mLinear_frag;
    private Fragment_Home fragmentHome;
    private Fragment_Want fragmentWant;
    private Fragment_Collection collectionFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;
    private boolean isExit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=getSupportFragmentManager();
        //抽屉初始化
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //抽屉下层
        setBehindContentView(R.layout.slidingbehind);
        initBehind();
        //抽屉上层
        setContentView(R.layout.slidingtop);
        initTop();


    }

    private void initTop() {
        //按钮
        mFrag_home= (LinearLayout) findViewById(R.id.mFrag_home);
        mFrag_want= (LinearLayout) findViewById(R.id.mFrag_want);
        mFrag_collection= (LinearLayout) findViewById(R.id.mFrag_collection);
        mFrag_mine= (LinearLayout) findViewById(R.id.mFrag_mine);

        mFrag_home.setOnClickListener(this);
        mFrag_want.setOnClickListener(this);
        mFrag_collection.setOnClickListener(this);
        mFrag_mine.setOnClickListener(this);

        mFrag_home1= (ImageView) findViewById(R.id.mFrag_home1);
        mFrag_want1= (ImageView) findViewById(R.id.mFrag_want1);
        mFrag_push= (ImageView) findViewById(R.id.mFrag_push);
        mFrag_colection1= (ImageView) findViewById(R.id.mFrag_collection1);
        mFrag_mine1= (ImageView) findViewById(R.id.mFrag_mine1);
        mFrag_push.setOnClickListener(this);

        mFrag_home2= (TextView) findViewById(R.id.mFrag_home2);
        mFrag_home2.setTextColor(Color.RED);
        mFrag_want2= (TextView) findViewById(R.id.mFrag_want2);
        mFrag_colection2= (TextView) findViewById(R.id.mFrag_collection2);
        mFrag_mine2= (TextView) findViewById(R.id.mFrag_mine2);
        //放frag的Linear
        mLinear_frag= (LinearLayout) findViewById(R.id.mLinear_frag);
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentHome ==null){
            fragmentHome =new Fragment_Home();
            transaction.add(R.id.mLinear_frag, fragmentHome);
        }else{
            transaction.show(fragmentHome);
        }
        transaction.commit();
    }

    private void initBehind() {

    }
    /**
     * 清除所有的颜色
     * */
    private void cleanAll(){
        mFrag_home1.setImageResource(R.drawable.main_board_1);
        mFrag_want1.setImageResource(R.drawable.main_board_2);
        mFrag_colection1.setImageResource(R.drawable.main_board_3);
        mFrag_mine1.setImageResource(R.drawable.main_board_4);
        mFrag_home2.setTextColor(Color.GRAY);
        mFrag_want2.setTextColor(Color.GRAY);
        mFrag_colection2.setTextColor(Color.GRAY);
        mFrag_mine2.setTextColor(Color.GRAY);
    }
    /**
     * 清除所有fragment
     * */
    private void cleanFrag(){
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentHome !=null){
            transaction.hide(fragmentHome);
        }
        if (fragmentWant !=null){
            transaction.hide(fragmentWant);
        }
        if (collectionFragment!=null){
            transaction.hide(collectionFragment);
        }
        if (mineFragment!=null){
            transaction.hide(mineFragment);
        }
        transaction.commit();
    }
    /**
     * 点击事件 切换fragment
     * */
    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()){
            case R.id.mFrag_home:
                cleanAll();
                cleanFrag();
                mFrag_home1.setImageResource(R.drawable.main_board_1_active);
                mFrag_home2.setTextColor(Color.RED);
                if (fragmentHome ==null){
                    fragmentHome =new Fragment_Home();
                    transaction.add(R.id.mLinear_frag, fragmentHome);
                }else{
                    transaction.show(fragmentHome);
                }
                break;
            case R.id.mFrag_want:
                cleanAll();
                cleanFrag();
                mFrag_want1.setImageResource(R.drawable.main_board_2_active);
                mFrag_want2.setTextColor(Color.RED);
                if (fragmentWant ==null){
                    fragmentWant =new Fragment_Want();
                    transaction.add(R.id.mLinear_frag, fragmentWant);
                }else{
                    transaction.show(fragmentWant);
                }
                break;
            case R.id.mFrag_push:
                startActivity(new Intent(MainActivity.this,Activity_PushGoods.class));
                break;
            case R.id.mFrag_collection:
                cleanAll();
                cleanFrag();
                mFrag_colection1.setImageResource(R.drawable.main_board_3_active);
                mFrag_colection2.setTextColor(Color.RED);
                if (collectionFragment==null){
                    collectionFragment=new Fragment_Collection();
                    transaction.add(R.id.mLinear_frag,collectionFragment);
                }else{
                    transaction.show(collectionFragment);
                }
                break;
            case R.id.mFrag_mine:
                cleanAll();
                cleanFrag();
                mFrag_mine1.setImageResource(R.drawable.main_board_4_active);
                mFrag_mine2.setTextColor(Color.RED);
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                    transaction.add(R.id.mLinear_frag,mineFragment);
                }else{
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }
    /*
 * 手机按返回键的监听
 * */
    @Override
    public void onBackPressed() {
        if(isExit){
            finish();
            System.exit(0);
        }else{
            isExit=true;
            Toast.makeText(this, "再次点击退出",Toast.LENGTH_SHORT).show();
            //postDelayed延时调用
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit=false;
                }
            }, 2000);

        }
    }
}
