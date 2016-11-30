package bgs.com.jianbao11.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.bean.Info_Grid;
import bgs.com.jianbao11.jianbao.MyAppalication;
import bgs.com.jianbao11.utils.ImageLoader;

/**
 * Created by 醇色 on 2016/11/28.
 */

public class Adapter_gridview extends BaseAdapter {
    private List<Info_Grid> list;
    private Context context;
    private ImageLoader imageLoader;

    public Adapter_gridview(List<Info_Grid> list, Context context) {
        this.list = list;
        this.context = context;
        imageLoader=((MyAppalication)this.context.getApplicationContext()).imageLoader;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.gridview,null);
            holder.mGrid_img= (ImageView) view.findViewById(R.id.mGrid_img);
            holder.mGrid_price= (TextView) view.findViewById(R.id.mGrid_price);
            holder.mGrid_title= (TextView) view.findViewById(R.id.mGrid_title);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        imageLoader.Load(list.get(i).getImg_url(), holder.mGrid_img, context);
        holder.mGrid_title.setText(list.get(i).getTitle());
        holder.mGrid_price.setText(list.get(i).getPrice());
        return view;
    }
    private class ViewHolder{
        private TextView mGrid_price,mGrid_title;
        private ImageView mGrid_img;
    }
}
