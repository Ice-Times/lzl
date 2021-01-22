package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;





class ImageViewUtil {
    public static void matchAll(Context context, ImageView imageView) {
        int width, height;//ImageView调整后的宽高
        //获取屏幕宽高
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int sWidth = metrics.widthPixels;
        int sHeight = metrics.heightPixels;

        Log.d("sWidth:", String.valueOf(sWidth));
        Log.d("sHeight:", String.valueOf(sHeight));

        //获取图片宽高
        Drawable drawable = imageView.getDrawable();
        int dWidth = drawable.getIntrinsicWidth();
        int dHeight = drawable.getIntrinsicHeight();

        Log.d("dWidth:", String.valueOf(dWidth));
        Log.d("dHeight:", String.valueOf(dHeight));


        //屏幕宽高比,一定要先把其中一个转为float
        float sScale = (float) sWidth / sHeight;
        //图片宽高比
        float dScale = (float) dWidth / dHeight;
        /*
        缩放比
        如果sScale>dScale，表示在高相等的情况下，控屏幕比较宽，这时候要适应高度，缩放比就是两则的高之比，图片宽度用缩放比计算
        如果sScale<dScale，表示在高相等的情况下，图片比较宽，这时候要适应宽度，缩放比就是两则的宽之比，图片高度用缩放比计算
         */
        float scale = 1.0f;
//        if (sScale > dScale) {
//            if(dWidth>sWidth)//图片宽度大于屏幕宽度
//            {
//                //scale=dWidth/sWidth;
//
//                width=sWidth;
//                height=width*dHeight/dWidth;
//            }
//           else{
//             //  scale=sWidth/dWidth;
//                width=sWidth;
//                height=width*dHeight/dWidth;
//            }
//
//
//
//           // scale = (float) dHeight / sHeight;
//            //height = sHeight;//图片高度就是屏幕高度
////            height=1920;
////
////            width=960;
//            //width = (int) (dWidth * scale);//按照缩放比算出图片缩放后的宽度
//
//            Log.d("height", String.valueOf(height));
//            Log.d("width", String.valueOf(width));
//
//
//            Log.d(" ", "matchAll: >");
//        } else if (sScale < dScale) {
//
//                width=sWidth;
//                height=dHeight*width/dWidth;
//
//
////            scale = (float) dWidth / sWidth;
////            width = sWidth;
////            height = (int) (dHeight / scale);//这里用除
//            Log.d(" ", "matchAll: <");
//
//
//        } else {
//            //最后两者刚好比例相同，其实可以不用写，刚好充满
//            width = sWidth;
//            height = sHeight;
//            Log.d(" ", "matchAll: =");
//        }


        width=sWidth;
        height=width*dHeight/dWidth;

        //重设ImageView宽高
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        imageView.setLayoutParams(params);
        //这样就获得了一个既适应屏幕有适应内部图片的ImageView，不用再纠结该给ImageView设定什么尺寸合适了
    }
}




public class Main6Activity extends AppCompatActivity {
    private Context context;

    Bitmap bitmap;

    Bitmap bmp1;
    Bitmap bmp2;
    Bitmap bmp3;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);




        Thread web=new Thread(new Runnable() {
            public void run() {
                try {
                    Log.d("", "run: 11");
                    Socket sc = new Socket("172.26.85.31", 9992);
                    //DataInputStream inputStream = new DataInputStream(sc.getInputStream());

                    try {
                        Log.d("接收服务器的数据", "接收服务器的数据");


                        PrintWriter out = new PrintWriter(new BufferedOutputStream(sc.getOutputStream()));
                        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(sc.getInputStream()));
                        System.out.println("start client....");
                        out.println("sendImage");
                        out.flush();
                        System.out.println("sendImage....");
                        byte[] buffer = new byte[2 * 1024];
                        int len = -1;
                        String imageName = null;
                        FileOutputStream fos = null;
                        //DataInputStream dataInput;

                        long fileLength = -1;

                        int num=0;
                        while (true) {
                            imageName = inputStream.readUTF();



                            if (imageName != null && imageName.trim().equals("end")) {
                                break;
                            }
                            Log.d("NNN::", imageName);

                            fileLength = inputStream.readLong();


                            byte[] data = new byte[(int) fileLength];
                            int ttlen = 0;
                            while (ttlen < fileLength) {
                                ttlen += inputStream.read(data, ttlen, (int) (fileLength - ttlen));
                            }

                            if(num==0){
                                bmp1 = BitmapFactory.decodeByteArray(data, 0, data.length);
                                if(data==null || bmp1==null) {
                                    Log.d("bmp1", "NULL");
                                }
                            }
                            else if(num==1){
                                bmp2 = BitmapFactory.decodeByteArray(data, 0, data.length);
                                if(data==null || bmp2==null) {
                                    Log.d("bmp2", "NULL");
                                }
                            }
                            else if(num==2){
                                bmp3 = BitmapFactory.decodeByteArray(data, 0, data.length);
                                if(data==null || bmp3==null) {
                                    Log.d("bmp3", "NULL");
                                }
                            }


                            num++;
                            Log.d("NUm::", String.valueOf(num));



                            fos = null;
                            imageName = null;

                        }


                    } catch (Exception e) {
                        System.out.println("接收服务器数据异常");
                        e.printStackTrace();
                    }
                    sc.close();

                } catch (Exception e) {
                    System.out.println("wrong");
                    e.printStackTrace();


                }
            }

        });


        try {
            web.start();

            web.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        LinearLayout layout_Group = (LinearLayout) findViewById(R.id.ll1);
        layout_Group.removeAllViews();

        ImageView iv=new ImageView(this);
     //   iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
        iv.setImageBitmap(bmp1);

        iv.setAdjustViewBounds(true);//防止出现空白

       // ImageViewUtil.matchAll(this, iv);

        layout_Group.addView(iv);



        ImageView iv1=new ImageView(this);
     //   iv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));  //设置图片宽高
        iv1.setImageBitmap(bmp2);

        iv1.setBackgroundColor(R.color.colorPrimaryDark);
        iv1.setAdjustViewBounds(true);//防止出现空白

     //   ImageViewUtil.matchAll(this, iv1);

        layout_Group.addView(iv1);



        ImageView iv2=new ImageView(this);
     //   iv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));  //设置图片宽高
        iv2.setImageBitmap(bmp3);

        iv2.setAdjustViewBounds(true);//防止出现空白

        ImageViewUtil.matchAll(this, iv2);

        layout_Group.addView(iv2);

    }


}
