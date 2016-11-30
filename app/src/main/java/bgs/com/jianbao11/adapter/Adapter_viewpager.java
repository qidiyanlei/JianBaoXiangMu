package bgs.com.jianbao11.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bgs.com.jianbao11.activity.Activity_Scal;


/**
 * Created by 醇色 on 2016/11/28.
 */

public class Adapter_viewpager extends PagerAdapter {
    private List<View> list;
    private Context context;
    private int [] arr;
    public Adapter_viewpager(List<View> list, Context context, int [] arr) {
        this.list = list;
        this.context = context;
        this.arr=arr;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        container.addView(list.get(position%list.size()));
        list.get(position%list.size()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Activity_Scal.class);
                int i=arr[position%list.size()];
                intent.putExtra("i",i);
                context.startActivity(intent);
            }
        });
        return list.get(position%list.size());
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position%list.size()));
    }
}
