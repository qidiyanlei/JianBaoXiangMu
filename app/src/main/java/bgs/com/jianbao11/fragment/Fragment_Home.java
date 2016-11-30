package bgs.com.jianbao11.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.activity.Activity_GoodsDetail;
import bgs.com.jianbao11.adapter.Adapter_fragment;
import bgs.com.jianbao11.adapter.Adapter_gridview;
import bgs.com.jianbao11.bean.Info_Adver;
import bgs.com.jianbao11.bean.Info_Bean;
import bgs.com.jianbao11.bean.Info_Grid;
import bgs.com.jianbao11.view.PullToRefrushView;
import bgs.com.jianbao11.view.View_Advers;

/**
 * Created by 醇色 on 2016/11/25.
 */

public class Fragment_Home extends Fragment implements PullToRefrushView.Pull_To_Load {
    private View v;
    private PullToRefrushView mPullToRefrushView;
    private View centerLinear;
    private LinearLayout myViewPager;
    private View_Advers viewAdvers;
    private List<Info_Bean> list=new ArrayList<Info_Bean>();
    private List<Info_Grid> infoGrid_List =new ArrayList<Info_Grid>();
    private List<Info_Adver> infoAdverList =new ArrayList<Info_Adver>();
    private ListView mFrag_home_lv;
    private Adapter_fragment adapter_fragment;
    private GridView mGridView;

    private boolean firstGrid=false;
    private int index_grid=0;
    private Adapter_gridview adapter_grid;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        v = View.inflate(getActivity(), R.layout.fragment_home,null);
        initList();
        initView();
        return v;
    }

    private void initList() {
        for (int i=0;i<7;i++){
            Info_Bean infoBean =new Info_Bean("标题"+i,""+169,""+i,"http://p4.so.qhmsg.com/sdr/574_768_/t0196e715f3442552a4.jpg","2016.11."+i,"2016.12."+i,i+"");
            list.add(infoBean);
        }
        //gridview
        for (int i=0;i<5;i++){
            Info_Grid infoGrid_ =new Info_Grid("商品名称"+i,"http://p4.so.qhmsg.com/sdr/574_768_/t0196e715f3442552a4.jpg","180");
            infoGrid_List.add(infoGrid_);
        }
        //viewpager
        for (int i=0;i<4;i++){
            Info_Adver infoAdver =new Info_Adver();
            infoAdver.setImgpath("http://p4.so.qhmsg.com/sdr/574_768_/t0196e715f3442552a4.jpg");
            infoAdverList.add(infoAdver);
        }
    }

    private void initView() {
        mPullToRefrushView= (PullToRefrushView) v.findViewById(R.id.mPullToRefrushView);
        mPullToRefrushView.setCall(this);
        //viewpager的主布局
        centerLinear=View.inflate(getActivity(),R.layout.centerlinear,null);
        mPullToRefrushView.setCenterView(centerLinear);
        //顶部vp
        myViewPager= (LinearLayout) centerLinear.findViewById(R.id.mAdver);
        viewAdvers =new View_Advers(getActivity(), infoAdverList);
        myViewPager.addView(viewAdvers.getView());
        //listview
        mFrag_home_lv= (ListView) centerLinear.findViewById(R.id.mFrag_home_lv);
        adapter_fragment=new Adapter_fragment(list,getActivity());
        mFrag_home_lv.setAdapter(adapter_fragment);
        mPullToRefrushView.MesureListHeight(mFrag_home_lv);
        mFrag_home_lv.setFocusable(false);
        mFrag_home_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info_Bean item = (Info_Bean) adapter_fragment.getItem(i);
                Intent intent=new Intent(getActivity(), Activity_GoodsDetail.class);
                startActivity(intent);
            }
        });
        //gridview
        mGridView= (GridView) centerLinear.findViewById(R.id.mGrid);
        mGridView.setNumColumns(2);
        mGridView.setColumnWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mGridView.setVerticalSpacing(6);
        mGridView.setHorizontalSpacing(6);
        adapter_grid=new Adapter_gridview(infoGrid_List,getActivity());
        mGridView.setAdapter(adapter_grid);
        mPullToRefrushView.calGridViewWidthAndHeigh(2,mGridView);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), Activity_GoodsDetail.class);
                startActivity(intent);
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                //加载更多
                mPullToRefrushView.complate();
               if (index_grid==0){
                  mGridView.setVisibility(View.VISIBLE);
                  index_grid=index_grid+1;
                  handler.sendEmptyMessage(6);
               }else{
                   mGridView.setVisibility(View.VISIBLE);
                   LoadMore();
                   adapter_grid.notifyDataSetChanged();
                   mPullToRefrushView.calGridViewWidthAndHeigh(2,mGridView);
               }
            }
            if (msg.what==1){
                //刷新
                mPullToRefrushView.complate();
                Toast.makeText(getActivity(), "***********", Toast.LENGTH_SHORT).show();
            }
            if (msg.what==6){
                firstGrid=true;
            }

        }
    };
    @Override
    public void Load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void Refrash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void LoadMore(){
        int count = adapter_grid.getCount();
        for (int i=count;i<count+11;i++){
            Info_Grid infoGrid_ =new Info_Grid("商品名称"+i,"http://p4.so.qhmsg.com/sdr/574_768_/t0196e715f3442552a4.jpg","190");
            infoGrid_List.add(infoGrid_);
        }
    }

}
