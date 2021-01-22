package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"1"};
    ArrayAdapter<String> adapter;

    private ListView lv;

    ArrayList<String> type;
    ArrayList<String> name;
    ArrayList<String> tt;

    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type = new ArrayList<String>();
        name = new ArrayList<String>();
        tt = new ArrayList<String>();


//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
//        ListView listview=(ListView)findViewById(R.id.list_view);
//        listview.setAdapter(adapter);

//        data[0]="1";
//        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
//        ListView listview=(ListView)findViewById(R.id.list_view);
//        listview.setAdapter(adapter);

//        ArrayList<String> type=new ArrayList<String>();
//        ArrayList<String> name=new ArrayList<String>();
//        ArrayList<String> tt=new ArrayList<String>();
//
//        type.add("t1");
//        name.add("n1");
//        tt.add("tt1");
//
//
//        final List<List<String>> data=new ArrayList<List<String>>();
//        for(int k=type.size()-1;k>=0;k--)
//        {
//            List temp=new ArrayList<String>();
//            temp.add(type.get(k));
//            temp.add(name.get(k));
//            temp.add(tt.get(k));
//
//            data.add(temp);
//        }
//
//
//        ListView listview=(ListView)findViewById(R.id.list_view);
//        listview.setAdapter(new listAdapt(MainActivity.this,data));


        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("", "onClick: ");

                EditText et = (EditText) findViewById(R.id.textView);
                Log.d("et:", et.getText().toString());
//                String[] s = {et.getText().toString(), "23"};

//                List<String> list = new ArrayList(Arrays.asList(data));
//
//                list.add(et.getText().toString());
//                data= list.toArray(new String[0]);
//                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);;
//
////                adapter.notifyDataSetChanged();
//                ListView listview=(ListView)findViewById(R.id.list_view);
//                listview.setAdapter(adapter2);

//        ArrayList<String> type=new ArrayList<String>();
//        ArrayList<String> name=new ArrayList<String>();
//        ArrayList<String> tt=new ArrayList<String>();


                num++;

                type.add("t" + Integer.toString(num));
                name.add("n" + Integer.toString(num));
                tt.add("z" + Integer.toString(num));

//        if(num==1||num==3){
//            Log.d("", "999");
//            CircleImageView it=(CircleImageView) findViewById(R.id.image);
//            it.setImageResource(R.drawable.tx3);
//
//        }

//          List<Zujian> Lz=new ArrayList<Zujian>();
//
//          Zujian z1=new Zujian("t1","t1","t1",R.drawable.tx3);
//
//          Lz.add(z1);

//          ListView listview=(ListView)findViewById(R.id.list_view);
//          listview.setAdapter(new mAdapt(MainActivity.this, androidx.constraintlayout.widget.R.,Lz));


//        final List<List<String>> data=new ArrayList<List<String>>();
//        for(int k=type.size()-1;k>=0;k--)
//        {
//            List temp=new ArrayList<String>();
//            temp.add(type.get(k));
//            temp.add(name.get(k));
//            temp.add(tt.get(k));
//
//            data.add(temp);
//        }
//
//
//        ListView listview=(ListView)findViewById(R.id.list_view);
//        listview.setAdapter(new mAdapt(MainActivity.this,data));

//                lv = (ListView) findViewById(R.id.list_view);
//                /*定义一个以HashMap为内容的动态数组*/
//                ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
//                for (int i = 0; i < 100; i++) {
//                    HashMap<String, Object> map = new HashMap<String, Object>();
//                    map.put("ItemImage", R.mipmap.ic_launcher);//加入图片
//                    map.put("ItemTitle", "第" + i + "行");
//                    map.put("ItemText", "这是第" + i + "行");
//                    listItem.add(map);
//                }
//         //       aAdapt adapter = new aAdapt(this, listItem);
//                aAdapt adapter = new aAdapt(getApplicationContext(), listItem);
//                lv.setAdapter(adapter);//为ListView绑定适配器
//
//                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                        System.out.println("你点击了第" + arg2 + "行");//设置系统输出点击的行
//                    }
//                });
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Log.d("", "run: 11");
                            Socket sc = new Socket("172.26.50.243", 10000);
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
                                if(data==null)
                                    Log.d("data", "null");

                                final Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                              //  bmp.compress(Bitmap.CompressFormat.JPEG, 50, outPut);


                                Log.d("接收图片", "完成");




                                Handler mainHandler = new Handler(Looper.getMainLooper());
                                mainHandler.post(new Runnable() {

                                    @Override
                                    public void run() {

                                        //已在主线程中，可以更新UI

                                        //--

                                        lv = (ListView) findViewById(R.id.list_view);
                                        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
                                        for (int i = 0; i < num; i++) {
                                            HashMap<String, Object> map = new HashMap<String, Object>();

                                            if(bmp!=null)
                                                map.put("ItemImage", bmp);//加入图片


//                                            if (i == 3 || i == 6) {
//                                                map.put("ItemImage", R.drawable.tx3);//加入图片
//                                            }
//                                            else {
//                                                if(bmp!=null)
//                                                map.put("ItemImage", bmp);//加入图片
//                                                else {
//                                                    map.put("ItemImage", R.drawable.tx3);//加入图片
//                                                    Log.d("", "null null");
//                                                }}

                                            map.put("bt1", "bt1");
                                            map.put("bt2", "bt2");
                                            map.put("bt3", "bt3");
                                            listItem.add(map);
                                        }

                                        bAdapt adapter = new bAdapt(getApplicationContext(), listItem);
                                        lv.setAdapter(adapter);//为ListView绑定适配器
                                        //--

                                    }

                                });



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

                }).start();


            }
        });
    }
}
