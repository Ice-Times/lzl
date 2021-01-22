package com.example.lzl;


import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main8Activity extends AppCompatActivity {

    private int clickNum = 0;
    private AlertDialog.Builder builder;
    private int delete_Img_num=-1;

    LinearLayout img_ll;

    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cache/pics";
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Button btImg = (Button) findViewById(R.id.chooseImg);
        img_ll = (LinearLayout)findViewById(R.id.img_linearlayout);
        btImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);


            }
        });


        Log.d("FP::", FILE_PATH);





        try {
            File file = new File(FILE_PATH, "1.jpg");
            if (file.exists()) {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(
                        file));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        final LinearLayout layout_Group = (LinearLayout) findViewById(R.id.img_linearlayout);




        final ImageView iv=new ImageView(this);
        iv.setImageBitmap(bitmap);
        iv.setAdjustViewBounds(true);//防止出现空白

        layout_Group.addView(iv);
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
                Bitmap bitmap_Pic = BitmapFactory.decodeFile(getRealFilePath(this,uri),options);

                bitmap=bitmap_Pic;



                try {
                    // 创建文件流，指向该路径，文件名叫做fileName
                    File file = new File(FILE_PATH, "1.jpg");
                    // file其实是图片，它的父级File是文件夹，判断一下文件夹是否存在，如果不存在，创建文件夹
                    File fileParent = file.getParentFile();
                    if (!fileParent.exists()) {
                        // 文件夹不存在
                        fileParent.mkdirs();// 创建文件夹
                    }
                    // 将图片保存到本地
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                            new FileOutputStream(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }





                final LinearLayout layout_Group = (LinearLayout) findViewById(R.id.img_linearlayout);
              //  layout_Group.removeAllViews();



                final ImageView iv=new ImageView(this);
                //   iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
                iv.setImageBitmap(bitmap_Pic);
                iv.setAdjustViewBounds(true);//防止出现空白

                iv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        clickNum++;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (clickNum == 1) {//单击
                                    Log.d("", "单击");
                                }else if(clickNum==2){//双击
                                    Log.d("", "双击");

                                    for(int i=0;i<layout_Group.getChildCount();i++)
                                    {
                                        if(iv==layout_Group.getChildAt(i))
                                        {
                                            Log.d("Num of img", String.valueOf(i));
                                            delete_Img_num=i;

                                        }
                                    }


                                    showOne();

                                }
                                //防止handler引起的内存泄漏
                                handler.removeCallbacksAndMessages(null);
                                clickNum = 0;
                            }
                        },300);
                    }
                });





                layout_Group.addView(iv);








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




    private void showOne() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("11")
                .setMessage("是否删除图片？").setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情

                        img_ll.removeViewAt(delete_Img_num);


                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }



}
