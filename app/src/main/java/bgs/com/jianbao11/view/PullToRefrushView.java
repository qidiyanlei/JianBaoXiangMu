package bgs.com.jianbao11.view;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import bgs.com.jianbao11.R;


/*
 * 带上下拉刷新功能的scrollview:---》一般加载在fragment中，首页
 * 
 * */
public class PullToRefrushView extends ScrollView{
	private LinearLayout mLinear;//scrollview中添加的linear
	private final static int DONE=0;//完成状态
	private final static int UP=1;
	private final static int RELASE_REFRASH=2;//释放刷新状态
	private final static int REFRASHING=3;//刷新状态
	private final static int DOWN=4;
	private final static int RELASE_LOAD=5;//释放加载状态
	private final static int LOADING=6;//加载状态
	private int mode=DONE;//当前所处的状态
	private View headView;//头部的布局
	private View footView;//底部的布局
	private ImageView headImg;//头部的图片
	private ImageView footImg;//底部的图片
	private AnimationDrawable draw_P;
	private AnimationDrawable draw_U;
	private int height=0;//头部的高度
	int height1;
	private PointF startPF=new PointF();
	private int scal=3;
	private Pull_To_Load call;
	private  GetHeight get;
	private LinearLayout centerView;//scrollview 里显示的布局：其中包括广告轮播  listview及其他
	private int []drawables={R.drawable.icon_loaing_frame_1,R.drawable.icon_loaing_frame_2,R.drawable.icon_loaing_frame_3,
			R.drawable.icon_loaing_frame_4,R.drawable.icon_loaing_frame_5,R.drawable.icon_loaing_frame_6,R.drawable.icon_loaing_frame_7,
			R.drawable.icon_loaing_frame_8,R.drawable.icon_loaing_frame_9,R.drawable.icon_loaing_frame_10,R.drawable.icon_loaing_frame_11,
			R.drawable.icon_loaing_frame_12,R.drawable.icon_loaing_frame_13};
	public PullToRefrushView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAnim();
		initView();
		
	}
	private void initView() {
		//设值scrollview的参数
		mLinear=new LinearLayout(getContext());
		mLinear.setOrientation(LinearLayout.VERTICAL);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mLinear.setLayoutParams(lp);
		//设值scrollview中显示的布局的参数
		centerView=new LinearLayout(getContext());
		centerView.setOrientation(LinearLayout.VERTICAL);
		centerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		
		//设值头尾布局的参数及动画
		headView=View.inflate(getContext(), R.layout.view_head, null);
		footView=View.inflate(getContext(), R.layout.view_head, null);
		
		headImg=(ImageView) headView.findViewById(R.id.mSCImg);
		footImg=(ImageView) footView.findViewById(R.id.mSCImg);
		
		headView.measure(0, 0);
		height=headView.getMeasuredHeight();
		
		headView.setPadding(0, -height, 0, 0);
		footView.setPadding(0, 0, 0, -height);
		
		headImg.setImageDrawable(draw_P);
		footImg.setImageDrawable(draw_U);
		//将子布局依次添加到scrollview的linear中：  scrollview是单布局，想要多布局使用必须添加linea
		mLinear.addView(headView);
		mLinear.addView(centerView);
		mLinear.addView(footView);
		//将linear添加到自定义中
		addView(mLinear);
		
		
	}
	//设值centerView的方法
	public void setCenterView(View v){
		v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		centerView.addView(v);
	}
	//初始化动画的方法
	private void initAnim() {
		draw_P=new AnimationDrawable();
		draw_U=new AnimationDrawable();
		for (int i : drawables) {
			draw_P.addFrame(getResources().getDrawable(i), 20);
			draw_U.addFrame(getResources().getDrawable(i), 20);
		}
		draw_P.setOneShot(false);
		draw_U.setOneShot(false);
		
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ev.getAction()==MotionEvent.ACTION_DOWN){
			startPF.y=ev.getY();
		}else if(ev.getAction()==MotionEvent.ACTION_MOVE){
			PointF pf=new PointF();
			pf.y=ev.getY();
			float disY=(pf.y-startPF.y)/scal;
			//进行判断：上啦或者下滑
			if(getScrollY()<=0&&disY>0){//下拉
				headView.setPadding(0, (int)(disY-height), 0, 0);
				if(disY>height){
					mode=RELASE_REFRASH;
				}else{
					mode=DONE;
				}
				return true;
			}
			//computeVerticalScrollRange:获取当前view的整体的垂直高度
			if((getScrollY()+getHeight())>=PullToRefrushView.this.computeVerticalScrollRange()&&disY<0){//上划--》判断是否滑到srcollView的底部
				footView.setPadding(0, 0, 0, (int)(Math.abs(disY)-height));
				if(Math.abs(disY)>height){
					mode=RELASE_LOAD;
				}else{
					mode=DONE;
				}
			}
		}else if(ev.getAction()==MotionEvent.ACTION_UP){
			select();
			
		}
		return super.dispatchTouchEvent(ev);
	}
	//根据当前mode的状态进行判断是刷新还是加载
	private void select() {
		switch (mode) {
		case DONE:
			draw_P.stop();
			draw_U.stop();
			headView.setPadding(0, -height, 0, 0);
			footView.setPadding(0, 0, 0, -height);
			break;

		case RELASE_REFRASH:
			draw_P.start();
			headView.setPadding(0, 0, 0, 0);
			//刷新
			call.Refrash();
			break;
		case RELASE_LOAD:
			draw_U.start();
			footView.setPadding(0, 0, 0, 0);
			//加载
			call.Load();
			break;

		}
	}
	//结束的方法
	public void complate(){
		mode=DONE;
		select();
	}
	//回调接口
	public interface Pull_To_Load{
		public void Load();
		public void Refrash();
	}
	public interface GetHeight{
		public void getHeight(int index);
	}
	public void setGetHeight(GetHeight get){
		this.get=get;
	}
	public void setCall(Pull_To_Load call){
		this.call=call;
	}
	//测量listView高度的方法
	public void MesureListHeight(ListView lv){
		//1.获取到listView的适配器
		ListAdapter adapter = lv.getAdapter();
		int height=0;//当前listView的高度
		for(int i=0;i<adapter.getCount();i++){
			View view = adapter.getView(i, null, null);
			view.measure(0, 0);
			height=height+view.getMeasuredHeight();
		}
		height=height+lv.getDividerHeight()*(adapter.getCount()-1)+
				lv.getPaddingBottom()+
				lv.getPaddingTop();
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
		lv.setLayoutParams(lp);
		PullToRefrushView.this.invalidate();//刷新高度
	}
	public void calGridViewWidthAndHeigh(int numColumns ,GridView gridView) {

		// 获取GridView对应的Adapter
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, gridView);
			listItem.measure(0, 0); // 计算子项View 的宽高

			if ((i+1)%numColumns == 0) {
				totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			}

			if ((i+1) == len && (i+1)%numColumns != 0) {
				totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			}
		}

		totalHeight += 40;

		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight;
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		gridView.setLayoutParams(params);
	}
}
