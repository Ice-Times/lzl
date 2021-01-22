package com.example.lzl;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class bAdapt extends BaseAdapter {
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    ArrayList<HashMap<String, Object>> listItem;

    public bAdapt(Context context, ArrayList<HashMap<String, Object>> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }//声明构造函数

    @Override
    public int getCount() {
        return listItem.size();
    }//这个方法返回了在适配器中所代表的数据集合的条目数

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }//这个方法返回了数据集合中与指定索引position对应的数据项

    @Override
    public long getItemId(int position) {
        return position;
    }//这个方法返回了在列表中与指定索引对应的行id

    static class CC
    {
        public ImageView img;
        public TextView bt1;
        public TextView bt2;
        public TextView bt3;

    }//声明一个外部静态类

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        bAdapt.CC zj ;
        if(convertView == null)
        {
            zj = new bAdapt.CC();
            convertView = mInflater.inflate(R.layout.blist, null);
            //convertView = mInflater.inflate(R.layout.blist, parent,false);
            zj.img = (ImageView)convertView.findViewById(R.id.image);
            zj.bt1 = (TextView)convertView.findViewById(R.id.bt1);
            zj.bt2 = (TextView)convertView.findViewById(R.id.bt2);
            zj.bt3 = (TextView)convertView.findViewById(R.id.bt3);
            convertView.setTag(zj);
        }
        else {
            zj = (bAdapt.CC)convertView.getTag();

        }

        //zj.img.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        zj.img.setImageBitmap((Bitmap) listItem.get(position).get("ItemImage"));
        zj.bt1.setText((String) listItem.get(position).get("bt1"));
        zj.bt2.setText((String) listItem.get(position).get("bt2"));
        zj.bt3.setText((String) listItem.get(position).get("bt3"));

        return convertView;
    }//这个方法返回了指定索引对应的数据项的视图


}
