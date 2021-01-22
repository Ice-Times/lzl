package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class Main9Activity extends AppCompatActivity {
    private Button bt;
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private ImageView photo,photo2;
    private File tempFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        photo= (ImageView) findViewById(R.id.image);
        photo2= (ImageView) findViewById(R.id.img2);

        bt = (Button) findViewById(R.id.aaa);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,PHOTO_REQUEST_GALLERY);
                // 激活系统图库，选择一张图片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
    //看到有人问裁剪的路径
        // 获取系统时间 然后将裁剪后的图片保存至指定的文件夹

        //SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        //String address = sDateFormat.format(new java.util.Date());
        //imagePath = address + ".JPEG";
        //　Uri imageUri = Uri.parse("保存的文件夹的名称/" + address
        // + ".JPEG");


        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, "");//输出路径
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);



        //Cursor cursor = LocationApplication.getContext().getContentResolver().query(uri, null, null, null, null);
        //if (cursor != null && cursor.moveToFirst()) {
        //photopath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        //Log.e("photopath", "photopath:------------" + photopath);
        //}

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                photo.setImageBitmap(bitmap);
                photo2.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
