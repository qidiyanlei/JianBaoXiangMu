package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import bgs.com.jianbao11.R;


/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class Activity_ForgetPassword extends Activity implements View.OnClickListener {

    private TextView forgetpassword_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        initView();
    }

    private void initView() {
        forgetpassword_call = (TextView) findViewById(R.id.forgetpassword_call);
        forgetpassword_call.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+"18201189193"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}
