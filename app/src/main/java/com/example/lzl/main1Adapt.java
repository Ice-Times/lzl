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

public class main1Adapt extends BaseAdapter{
    private LayoutInflater mInflater;//导入布局
    ArrayList<HashMap<String, Object>> listItem;

    public main1Adapt(Context context, ArrayList<HashMap<String, Object>> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }//构造函数

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class main_struct
    {
        public TextView type;

        public ImageView tx_img;

        public TextView name;
        public TextView phone;
        public TextView time;

        public TextView rep;

        public ImageView rep_img;

    }//声明一个外部静态类


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        main1Adapt.main_struct ms;

        if(convertView == null)
        {
            ms = new main1Adapt.main_struct();

            convertView = mInflater.inflate(R.layout.main1list, null);

            ms.type =(TextView)convertView.findViewById(R.id.type);
            ms.tx_img= (ImageView)convertView.findViewById(R.id.tx_image);
            ms.name=(TextView)convertView.findViewById(R.id.user_name);
            ms.phone=(TextView)convertView.findViewById(R.id.user_phone);
            ms.time=(TextView)convertView.findViewById(R.id.pub_times);
            ms.rep=(TextView)convertView.findViewById(R.id.represent_text);

            ms.rep_img= (ImageView)convertView.findViewById(R.id.rep_img);

            convertView.setTag(ms);
        }
        else {
            ms = (main1Adapt.main_struct)convertView.getTag();

        }

        ms.type.setText((String) listItem.get(position).get("type"));

        ms.tx_img.setImageBitmap((Bitmap) listItem.get(position).get("tx_img"));
        ms.name.setText((String) listItem.get(position).get("user_name"));
        ms.phone.setText((String) listItem.get(position).get("user_phone"));
        ms.time.setText((String) listItem.get(position).get("pub_times"));
        ms.rep.setText((String) listItem.get(position).get("represent_text"));
        ms.rep_img.setImageBitmap((Bitmap) listItem.get(position).get("rep_img"));



        return convertView;

    }

}
