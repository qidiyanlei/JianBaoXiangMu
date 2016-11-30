package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import bgs.com.jianbao11.R;


public class Activity_FirstStart extends Activity{

    private ImageView mFirst_circle;
    private TextView mFirst_time;
    private boolean flag = true;
    private int index = 3;
    private RotateAnimation rotate;
    private AnimationUtils utils;//动画工具类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firststart);
        initView();
    }

    private void initView() {
        mFirst_circle = (ImageView) findViewById(R.id.mFirst_circle);
        mFirst_time = (TextView) findViewById(R.id.mFirst_time);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotata_anim);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        mFirst_circle.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 3000);
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (index > 1) {
                    try {
                        Thread.sleep(1000);
                        index--;

                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        msg.obj = index;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 0) {
                Intent intent = new Intent(Activity_FirstStart.this, MainActivity.class);
                startActivity(intent);
               // mTv1.setVisibility(View.GONE);
            }
            if (msg.what == 1) {
                int shuzi = (Integer) msg.obj;
                mFirst_time.setText(index + "");
            }
        }
    };

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        flag = false;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        finish();
    }

}
