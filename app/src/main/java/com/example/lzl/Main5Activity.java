package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Main5Activity extends AppCompatActivity {

    private PullToRefreshListView mPullToRefreshListView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        context = this;

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < 50; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            Bitmap bitmap1 = readBitmap(context,R.drawable.tx3);

            map.put("tx_img", bitmap1);

            map.put("type", "二手交易");
            map.put("user_name", "User");
            map.put("user_phone", "联系方式：12345678");
            map.put("pub_times", "2020-12-27 12:12:12");
            map.put("represent_text", "二手的、全新的、闲置的全都来吧！ \n" +
                    "这里是交易、买卖、转让的大市场！ \n" +
                    "\n" +
                    "1.卖东西的建议贴上图片 \n" +
                    "2.求购东西的要注明 \n" +
                    "3.交易地点自定 ");

            Bitmap bitmap2 = readBitmap(context,R.drawable.tx1);
            map.put("rep_img", bitmap2);

            listItem.add(map);
        }

        main1Adapt adapter = new main1Adapt(this, listItem);


//        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
//        for (int i = 0; i < 153; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//
//            Bitmap bitmap =readBitmap(context, R.drawable.tx3);
//
//            if(bitmap!=null)
//                map.put("ItemImage", bitmap);//加入图片
//
//            map.put("bt1", "bt1");
//            map.put("bt2", "bt2");
//            map.put("bt3", "bt3");
//            listItem.add(map);
//        }

//        bAdapt adapter = new bAdapt(this, listItem);

        //初始化控件
        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list2);
        ListView mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setAdapter(adapter);


    }

    public Bitmap readBitmap(Context context, int id){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig=Bitmap.Config.RGB_565;//表示16位位图 565代表对应三原色占的位数
        //opt.inPreferredConfig=Bitmap.Config.ALPHA_8;

        opt.inInputShareable=true;
        opt.inPurgeable=true;//设置图片可以被回收
        InputStream is = context.getResources().openRawResource(id);
        return BitmapFactory.decodeStream(is, null, opt);
    }


}
