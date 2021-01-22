package com.example.lzl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Arrays;
import java.util.LinkedList;

public class Main2Activity extends ListActivity {



        private PullToRefreshListView mPullToRefreshListView;

        private LinkedList<String> mItemList;
        private ArrayAdapter<String> adapter;

        private Context context;


        private String[] data  = new String[]{"data1","data2","data3","data4","data5","data6",
                "data1","data2","data3","data4","data5","data6"};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            context = this;
            initData();

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItemList);

            //初始化控件
            mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);
            ListView mListView = mPullToRefreshListView.getRefreshableView();
            mListView.setAdapter(adapter);

            //设置pull-to-refresh模式为Mode.Both
            mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

            //设置上拉下拉事件
            mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                    if (refreshView.isHeaderShown()){
                        Toast.makeText(context, "下拉刷新",Toast.LENGTH_SHORT).show();
                        //下拉刷新 业务代码

                        String[] data  = new String[]{"data1","data2","data3","data4","data5","data6"};
                        mItemList = new LinkedList<String>();
                        mItemList.addAll(Arrays.asList(data));
                        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mItemList);
                        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);

                        final ListView mListView = mPullToRefreshListView.getRefreshableView();
                        mListView.setAdapter(adapter);


                        mPullToRefreshListView.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                mPullToRefreshListView.onRefreshComplete();
                            }
                        }, 1000);



                    }else {
                        Toast.makeText(context, "上拉加载更多",Toast.LENGTH_SHORT).show();
                        //上拉加载更多 业务代码


                        String[] data  = new String[]{"sadsad","dsadasd"};
                        mItemList = new LinkedList<String>();
                        mItemList.addAll(Arrays.asList(data));
                        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mItemList);
                        mPullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pull_refresh_list);

                        final ListView mListView = mPullToRefreshListView.getRefreshableView();
                        mListView.setAdapter(adapter);


                        mPullToRefreshListView.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                mPullToRefreshListView.onRefreshComplete();
                            }
                        }, 1000);



                    }

                }
            });

        }


        private void initData(){
            //初始化数据
            mItemList = new LinkedList<String>();
            mItemList.addAll(Arrays.asList(data));

        }




}
