package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Context mContext=this;


        LinearLayout img_layout_Group = (LinearLayout) findViewById(R.id.img_layout);

        img_layout_Group.removeAllViews();  //clear linearlayout

        for(int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
            imageView.setImageResource(R.drawable.tx1); //图片资源

            imageView.setAdjustViewBounds(true);//防止出现空白

            img_layout_Group.addView(imageView); //动态添加图片

            }

        LinearLayout text_Group=(LinearLayout) findViewById(R.id.text_layout);

        text_Group.removeAllViews();
        for(int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
            imageView.setImageResource(R.drawable.tx1); //图片资源

            imageView.setAdjustViewBounds(true);//防止出现空白

            img_layout_Group.addView(imageView); //动态添加图片

        }



    }
}
