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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.picture.ImageTools;

import static bgs.com.jianbao11.R.id.mImg_xinxi_head;


/**
 * Created by 毛毛 on 2016/11/29.
 */

public class Activity_XinXi extends Activity implements View.OnClickListener {
    private TextView mTv_name,mTv_phone,mTv_code,mTv_QQ,mTv_wechat,mTv_email;
    private ImageView mImg_head;
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int SCALE = 5;//照片缩小比例


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_xinxi);

        initView();
        initClick();

    }

    private void initClick() {
        mImg_head.setOnClickListener(this);
    }

    private void initView() {
        mTv_code = (TextView) findViewById(R.id.mTv_xinxi_code);
        mTv_phone = (TextView) findViewById(R.id.mTv_xinxi_phone);
        mTv_QQ = (TextView) findViewById(R.id.mTv_xinxi_QQ);
        mTv_wechat = (TextView) findViewById(R.id.mTv_xinxi_wechat);
        mTv_email = (TextView) findViewById(R.id.mTv_xinxi_email);

        mImg_head = (ImageView) findViewById(mImg_xinxi_head);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case mImg_xinxi_head:
            showPicturePicker(Activity_XinXi.this);
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
                    mImg_head.setImageBitmap(newBitmap);
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

                            mImg_head.setImageBitmap(smallBitmap);
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
