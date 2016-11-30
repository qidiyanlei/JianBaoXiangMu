package bgs.com.jianbao11.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.bean.Info_Bean;
import bgs.com.jianbao11.jianbao.MyAppalication;
import bgs.com.jianbao11.utils.ImageLoader;

import static bgs.com.jianbao11.R.id.fragment_price;


public class Adapter_fragment extends BaseAdapter {
	private List<Info_Bean> list;
	private Context context;
	private ImageLoader imageLoader;
	public Adapter_fragment(List<Info_Bean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=((MyAppalication)this.context.getApplicationContext()).imageLoader;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	public void refrush(List<Info_Bean> list){
		this.list=list;
		notifyDataSetChanged();
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vHolder;
		if (convertView==null) {
			vHolder=new ViewHolder();
			convertView=View.inflate(context, R.layout.adapter_fragment, null);
			vHolder.fragment_icon=(ImageView) convertView.findViewById(R.id.fragment_icon);
			vHolder.fragment_title=(TextView) convertView.findViewById(R.id.fragment_title);
			vHolder.fragment_id= (TextView) convertView.findViewById(R.id.fragment_id);
			vHolder.fragment_price= (TextView) convertView.findViewById(fragment_price);
			vHolder.fragment_issue_time= (TextView) convertView.findViewById(R.id.fragment_issue_time);
			vHolder.fragment_fianl_time= (TextView) convertView.findViewById(R.id.fragment_final_time);
			vHolder.fragment_follow= (TextView) convertView.findViewById(R.id.fragment_follow);
			convertView.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) convertView.getTag();
		}
		
		vHolder.fragment_title.setText(list.get(position).getTitle());
		vHolder.fragment_id.setText(list.get(position).getId());
		vHolder.fragment_price.setText(list.get(position).getPrice());
		vHolder.fragment_fianl_time.setText(list.get(position).getFinal_time());
		vHolder.fragment_issue_time.setText(list.get(position).getIssue_time());
		vHolder.fragment_follow.setText(list.get(position).getFollow());
		imageLoader.Load(list.get(position).getPicture_str(), vHolder.fragment_icon, context);
		return convertView;
	}
	private class ViewHolder{
		private ImageView fragment_icon;
		private TextView fragment_title,fragment_id,
				fragment_price,fragment_issue_time,fragment_fianl_time,fragment_follow;
	}
	
}
