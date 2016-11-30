package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bgs.com.jianbao11.R;


/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class Activity_LoginRegister extends Activity implements View.OnClickListener {
    private EditText logreg_et_invite,logreg_et_phone;
    private Button logreg_bt_login;
    private TextView logreg_tv_forgetpassword,logreg_tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logregister);
        initView();
    }

    private void initView() {
        logreg_et_invite= (EditText) findViewById(R.id.logreg_et_invite);
        logreg_et_phone= (EditText) findViewById(R.id.logreg_et_phone);
        logreg_bt_login= (Button) findViewById(R.id.logreg_bt_login);
        logreg_tv_forgetpassword= (TextView) findViewById(R.id.logreg_tv_forgetpassword);
        logreg_tv_register= (TextView) findViewById(R.id.logreg_tv_register);

        logreg_bt_login.setOnClickListener(this);
        logreg_tv_forgetpassword.setOnClickListener(this);
        logreg_tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.logreg_bt_login:
                String invitenumber = logreg_et_invite.getText().toString();
                String phonenumber = logreg_et_phone.getText().toString();
                if (invitenumber==null){
                    Toast.makeText(this,"您输入的邀请码为空", Toast.LENGTH_SHORT).show();
                }
                if (phonenumber==null){
                    Toast.makeText(this,"您输入的手机号码为空", Toast.LENGTH_SHORT).show();
                }
                if (invitenumber==null&&phonenumber==null){
                    Toast.makeText(this,"请输入您的邀请码和手机号码", Toast.LENGTH_SHORT).show();
                }else{

                }
                break;
            case R.id.logreg_tv_forgetpassword:
                Intent intent = new Intent(Activity_LoginRegister.this,Activity_ForgetPassword.class);
                startActivity(intent);
                break;
            case R.id.logreg_tv_register:

                Intent intent1 = new Intent(Activity_LoginRegister.this,Activity_ZhuCe.class);

                startActivity(intent1);
                break;

        }

    }
}
