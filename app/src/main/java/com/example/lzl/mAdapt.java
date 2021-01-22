package com.example.lzl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

final class Zujian {
    public String text1;
    public String text2;
    public String text3;

    public int imageId;


    public Zujian(String text1,String text2,String text3, int imageId){
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.imageId = imageId;
    }

    public String getText1(){
        return text1;
    }
    public String getText2(){
        return text2;
    }
    public String getText3(){
        return text3;
    }

    public int getImageId() {
        return imageId;
    }
}

public class mAdapt extends ArrayAdapter<Zujian> {
//    private List<Zujian> data;
//    private LayoutInflater layoutInflater;
//    private Context context;

    private int newResourceId;


    public mAdapt(Context context, int resourceId, List<Zujian> data) {
        super(context, resourceId, data);
        newResourceId = resourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zj = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);


        TextView t1 = view.findViewById (R.id.t1);
        TextView t2 = view.findViewById (R.id.t2);
        TextView t3 = view.findViewById (R.id.t3);

        ImageView img = view.findViewById (R.id.imageView);

        t1.setText (zj.getText1 ());
        t2.setText (zj.getText2 ());
        t3.setText (zj.getText3 ());
        img.setImageResource (zj.getImageId ());

        return convertView;
    }
}
