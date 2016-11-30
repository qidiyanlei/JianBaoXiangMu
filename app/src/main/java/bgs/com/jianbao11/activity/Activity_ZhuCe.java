package bgs.com.jianbao11.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.bean.Info_zhuce;
import bgs.com.jianbao11.picture.ImageTools;


/**
 * Created by 毛毛 on 2016/11/28.
 */

public class Activity_ZhuCe extends Activity implements View.OnClickListener {
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int SCALE = 5;//照片缩小比例

    private EditText mEdit_name,mEdit_code,mEdit_phone,mEdit_password,mEdit_QQ,mEdit_wechat,mEdit_email;
    private Info_zhuce info = new Info_zhuce();
    private RadioButton mRbtn_man,mRbtn_woman;
    private TextView mTv_commit,mTv_more;
    private LinearLayout mLin_QQ,mLin_wechat,mLin_email;
    private boolean isMore = true;
    private ImageView mImg_idcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        initData();
        initClick();
        initBroad();
    }

    private void initBroad() {

    }

    private void initClick() {
        mTv_commit.setOnClickListener(this);
        mTv_more.setOnClickListener(this);
        mImg_idcard.setOnClickListener(this);
    }

    private void initData() {
       info.setStr_code(mEdit_code.getText().toString());
      info.setStr_phone(mEdit_phone.getText().toString());
        info.setStr_name(mEdit_name.getText().toString());
       info.setStr_password( mEdit_password.getText().toString());
        info.setStr_QQ(mEdit_QQ.getText().toString());
        info.setStr_wechat(mEdit_wechat.getText().toString());
        info.setStr_email(mEdit_email.getText().toString());

    }

    private void initView() {
        mTv_commit = (TextView) findViewById(R.id.mTv_commit);
        mTv_more = (TextView) findViewById(R.id.mTv_more);

        mRbtn_man = (RadioButton) findViewById(R.id.mRbtn_man);
        mRbtn_woman = (RadioButton) findViewById(R.id.mRbtn_woman);

        mLin_QQ = (LinearLayout) findViewById(R.id.mLin_QQ);
        mLin_wechat = (LinearLayout) findViewById(R.id.mLin_wechat);
        mLin_email = (LinearLayout) findViewById(R.id.mLin_email);

        mEdit_name = (EditText) findViewById(R.id.mEdit_name);
        mEdit_code = (EditText) findViewById(R.id.mEdit_code);
        mEdit_phone = (EditText) findViewById(R.id.mEdit_phone);
        mEdit_password = (EditText) findViewById(R.id.mEdit_password);
        mEdit_QQ = (EditText) findViewById(R.id.mEdit_QQ);
        mEdit_wechat = (EditText) findViewById(R.id.mEdit_wechat);
        mEdit_email = (EditText) findViewById(R.id.mEdit_email);

        mImg_idcard = (ImageView) findViewById(R.id.mImg_idcard);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mTv_more:
                if (isMore) {
                    mLin_QQ.setVisibility(View.VISIBLE);
                    mLin_wechat.setVisibility(View.VISIBLE);
                    mLin_email.setVisibility(View.VISIBLE);
                    isMore = !isMore;
                } else {
                    mLin_QQ.setVisibility(View.GONE);
                    mLin_wechat.setVisibility(View.GONE);
                    mLin_email.setVisibility(View.GONE);
                    isMore = !isMore;
                }
                break;
            case R.id.mTv_commit:
                if (mEdit_code == null) {
                    Toast.makeText(this,"邀请码不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEdit_phone == null) {
                    Toast.makeText(this,"手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEdit_password == null) {
                    Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEdit_name == null) {
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Activity_ZhuCe.this,Activity_LoginRegister.class);
                    startActivity(intent);

                    finish();
                }

                break;
            case R.id.mImg_idcard:
                    showPicturePicker(Activity_ZhuCe.this);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    //将保存在本地的图片取出并缩小后显示在界面上
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");
                    Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
                    //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
                    bitmap.recycle();

                    //将处理过的图片显示在界面上，并保存到本地
                    mImg_idcard.setImageBitmap(newBitmap);
                    ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
                    break;

                case CHOOSE_PICTURE:
                    ContentResolver resolver = getContentResolver();
                    //照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        //使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            //为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
                            Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
                            //释放原始图片占用的内存，防止out of memory异常发生
                            photo.recycle();

                            mImg_idcard.setImageBitmap(smallBitmap);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    }
    public void showPicturePicker(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("图片来源");
        builder.setNegativeButton("取消", null);
        builder.setItems(new String[]{"拍照","相册"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PICTURE:
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                        //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;

                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;

                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }

}
