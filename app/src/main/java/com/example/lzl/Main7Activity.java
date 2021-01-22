package com.example.lzl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main7Activity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    private int Pic_Num=0;

    Bitmap bitmap_Pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);


        Button btImg = (Button) findViewById(R.id.chooseImg);
        imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView2 = (ImageView) findViewById(R.id.imageView3);
        imageView3 = (ImageView) findViewById(R.id.imageView4);
        btImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);


            }
        });



    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();

                Log.e("uri", uri.toString());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 32;
                bitmap_Pic = BitmapFactory.decodeFile(getRealFilePath(this,uri),options);
                Log.d("", "水涨船高");



                if(Pic_Num==0) {
                    imageView1.setImageBitmap(bitmap_Pic);
                }
                else if(Pic_Num==1){
                    imageView2.setImageBitmap(bitmap_Pic);
                }
                else if(Pic_Num==2){
                    imageView3.setImageBitmap(bitmap_Pic);
                }else{
                    Log.d("", "onActivityResult: ");
                }

                Pic_Num++;
                Pic_Num=Pic_Num%3;
                //imageView1.setImageBitmap(bitmap);

                //imageView1.setDrawingCacheEnabled(true);
                Bitmap bitmap=((BitmapDrawable) imageView1.getDrawable()).getBitmap();


               imageView2.setImageBitmap(bitmap);
              //  imageView1.setDrawingCacheEnabled(false);

            }
        }
    }
    //url
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);

            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }




}
