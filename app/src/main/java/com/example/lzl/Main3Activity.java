package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private ListView lv;

    private int num = 0;

    Bitmap bitmap;


    boolean gea=false;

    private PullToRefreshListView mPullToRefreshListView;

    private LinkedList<String> mItemList;
  //  private ArrayAdapter<String> adapter;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        context = this;

     //   mItemList = new LinkedList<String>();
     //   mItemList.addAll(Arrays.asList(data));

     //   adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItemList);



//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Log.d("", "run: 11");
//                    Socket sc = new Socket(" 172.26.109.196", 10000);
//                    DataInputStream inputStream = new DataInputStream(sc.getInputStream());
//
//                    try {
//                        Log.d("接收服务器的数据", "接收服务器的数据");
//
//                        DataInputStream dataInput = new DataInputStream(sc.getInputStream());
//                        int size = dataInput.readInt();
//                        byte[] data = new byte[size];
//                        int len = 0;
//                        while (len < size) {
//                            len += dataInput.read(data, len, size - len);
//                        }
//
//
//                        //ByteArrayOutputStream outPut = new ByteArrayOutputStream();
//                        if (data == null)
//                            Log.d("data", "null");
//
//                        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                        //  bmp.compress(Bitmap.CompressFormat.JPEG, 50, outPut);
//
//
//                        Log.d("接收图片", "完成");
//
//                    } catch (Exception e) {
//                        System.out.println("接收服务器数据异常");
//                        e.printStackTrace();
//                    }
//                    sc.close();
//
//                } catch (Exception e) {
//                    System.out.println("wrong");
//                    e.printStackTrace();
//
//
//                }
//            }
//
//        }).start();




        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        for (int i = 0; i < 153; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();


            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tx3);


//1
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tx3);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos);
//            InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
   //1


            Bitmap bitmap =readBitmap(context, R.drawable.tx3);



            Log.d("1223123", "onCreate: ");

            if(bitmap!=null)
            map.put("ItemImage", bitmap);//加入图片

            map.put("bt1", "bt1");
            map.put("bt2", "bt2");
            map.put("bt3", "bt3");
            listItem.add(map);
        }

       // bAdapt adapter = new bAdapt(getApplicationContext(), listItem);
        bAdapt adapter = new bAdapt(this, listItem);


//        List<String> datas = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            datas.add("item---------" + i);
//        }
//
//
//        ArrayAdapter<String> ta = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);

        //初始化控件
        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
        ListView mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setAdapter(adapter);


        //--
//        ArrayList<HashMap<String, Object>> tlistItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
//        for (int i = 0; i < 9; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//
//
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tx3);
//
//            map.put("ItemImage", bitmap);//加入图片
//
//            map.put("bt1", "t1");
//            map.put("bt2", "t2");
//            map.put("bt3", "t3");
//            tlistItem.add(map);
//        }
//
//        bAdapt tadapter = new bAdapt(context, tlistItem);
//
//
//        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
//        ListView tmListView = mPullToRefreshListView.getRefreshableView();
//        tmListView.setAdapter(tadapter);


        //--







        //设置pull-to-refresh模式为Mode.Both
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        //设置上拉下拉事件
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                if (refreshView.isHeaderShown()){
                    Toast.makeText(context, "下拉刷新",Toast.LENGTH_SHORT).show();
                    //下拉刷新 业务代码


                    ListView tmListView = mPullToRefreshListView.getRefreshableView();
                    tmListView.setAdapter(null);

                    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
                    for (int i = 0; i < 22; i++) {
                        HashMap<String, Object> map = new HashMap<String, Object>();


                        Bitmap bitmap = readBitmap(context,R.drawable.tx3);

                        //@SuppressLint("ResourceType") Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.tx1));


                        map.put("ItemImage", bitmap);//加入图片

                        map.put("bt1", "bt1");
                        map.put("bt2", "bt2");
                        map.put("bt3", "bt3");
                        listItem.add(map);



//                        if(bitmap.isRecycled()==false) //如果没有回收
//                        {
//                            bitmap.recycle();
//                            System.gc();
//
//                        }
                    }

                    bAdapt adapter = new bAdapt(context, listItem);


                    mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
                    ListView mListView = mPullToRefreshListView.getRefreshableView();
                    mListView.setAdapter(adapter);


//                    String[] data  = new String[]{"data1","data2","data3","data4","data5","data6"};
//                    mItemList = new LinkedList<String>();
//                    mItemList.addAll(Arrays.asList(data));
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mItemList);
//
//
//                    mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
//
//                    final ListView mListView = mPullToRefreshListView.getRefreshableView();
//                    mListView.setAdapter(adapter);





                    mPullToRefreshListView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            mPullToRefreshListView.onRefreshComplete();
                        }
                    }, 1000);



                }else {
                    Toast.makeText(context, "上拉加载更多",Toast.LENGTH_SHORT).show();
                    //上拉加载更多 业务代码








                    Thread web=new Thread(new Runnable() {
                        public void run() {
                            try {
                                Log.d("", "run: 11");
                                Socket sc = new Socket("172.26.109.196", 10000);
                                DataInputStream inputStream = new DataInputStream(sc.getInputStream());

                                try {
                                    Log.d("接收服务器的数据", "接收服务器的数据");

                                    DataInputStream dataInput = new DataInputStream(sc.getInputStream());
                                    int size = dataInput.readInt();
                                    byte[] data = new byte[size];
                                    int len = 0;
                                    while (len < size) {
                                        len += dataInput.read(data, len, size - len);
                                    }


                                    //ByteArrayOutputStream outPut = new ByteArrayOutputStream();
                                    if (data == null)
                                        Log.d("data", "null");

                                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    //  bmp.compress(Bitmap.CompressFormat.JPEG, 50, outPut);


                                    Log.d("接收图片", "完成");
                                    gea=true;

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
                    //--

                    ListView tmListView = mPullToRefreshListView.getRefreshableView();
                    tmListView.setAdapter(null);

                    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
                    for (int i = 0; i < 32; i++) {
                        HashMap<String, Object> map = new HashMap<String, Object>();


                        //Bitmap bitmap = readBitmap(context,R.drawable.tx1);

                        //@SuppressLint("ResourceType") Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.tx1));


                        map.put("ItemImage", bitmap);//加入图片

                        map.put("bt1", "bt1");
                        map.put("bt2", "bt2");
                        map.put("bt3", "bt3");
                        listItem.add(map);



//                        if(bitmap.isRecycled()==false) //如果没有回收
//                        {
//                            bitmap.recycle();
//                            System.gc();
//
//                        }
                    }

                    bAdapt adapter = new bAdapt(context, listItem);


                    mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
                    ListView mListView = mPullToRefreshListView.getRefreshableView();
                    mListView.setAdapter(adapter);


//---

                    Log.d("???", "onRefresh: ");
                    mPullToRefreshListView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            mPullToRefreshListView.onRefreshComplete();
                        }
                    }, 2000);

//                    while(gea!=true)
//                        Log.d("???", "laterlater");

                }

            }
        });

    }


    public static Bitmap decodeBitmapResource(Resources resources, int id) {


        Bitmap bitmap;
        InputStream is = resources.openRawResource(id);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmap = BitmapFactory.decodeStream(is, null, opts);
        return bitmap;
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
