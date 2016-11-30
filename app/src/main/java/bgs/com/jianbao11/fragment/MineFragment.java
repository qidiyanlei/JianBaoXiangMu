package bgs.com.jianbao11.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bgs.com.jianbao11.R;
import bgs.com.jianbao11.activity.Activity_KeFu;
import bgs.com.jianbao11.activity.Activity_XinXi;
import bgs.com.jianbao11.activity.Activity_YiJian;
import bgs.com.jianbao11.activity.Activity_LoginRegister;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by 醇色 on 2016/11/25.
 */

public class MineFragment extends Fragment {
    @InjectView(R.id.mImg_head)
    ImageView mImgHead;
    @InjectView(R.id.mTv_login_no)
    TextView mTvLoginNo;
    @InjectView(R.id.mImg_selling)
    ImageView mImgSelling;
    @InjectView(R.id.mTv_selling)
    TextView mTvSelling;
    @InjectView(R.id.mLin_selling)
    LinearLayout mLinSelling;
    @InjectView(R.id.mImg_sold)
    ImageView mImgSold;
    @InjectView(R.id.mTv_sold)
    TextView mTvSold;
    @InjectView(R.id.mLin_sold)
    LinearLayout mLinSold;
    @InjectView(R.id.mImg_bought)
    ImageView mImgBought;
    @InjectView(R.id.mTv_bought)
    TextView mTvBought;
    @InjectView(R.id.mLin_bought)
    LinearLayout mLinBought;
    @InjectView(R.id.mImg_buying)
    ImageView mImgBuying;
    @InjectView(R.id.mTv_buying)
    TextView mTvBuying;
    @InjectView(R.id.mLin_buying)
    LinearLayout mLinBuying;
    @InjectView(R.id.mImg_favorite)
    ImageView mImgFavorite;
    @InjectView(R.id.mTv_favorite)
    TextView mTvFavorite;
    @InjectView(R.id.mLin_favorite)
    LinearLayout mLinFavorite;
    @InjectView(R.id.mLin_info)
    LinearLayout mLinInfo;
    @InjectView(R.id.mLin_renzheng)
    LinearLayout mLinRenzheng;
    @InjectView(R.id.mLin_yijian)
    LinearLayout mLinYijian;
    @InjectView(R.id.mLin_kefu)
    LinearLayout mLinKefu;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.fragment_mine, null);
        ButterKnife.inject(this, v);

        return v;
    }

    private void clearImg() {
        mImgSelling.setImageResource(R.mipmap.userinfo_selling);
        mImgSold.setImageResource(R.mipmap.userinfo_sold);
        mImgBought.setImageResource(R.mipmap.userinfo_bought);
        mImgBuying.setImageResource(R.mipmap.userinfo_buying);
        mImgFavorite.setImageResource(R.mipmap.userinfo_favorite);
    }

    private void clearText() {
        mTvSelling.setTextColor(getResources().getColor(R.color.textcolor));
        mTvSold.setTextColor(getResources().getColor(R.color.textcolor));
        mTvBought.setTextColor(getResources().getColor(R.color.textcolor));
        mTvBuying.setTextColor(getResources().getColor(R.color.textcolor));
        mTvFavorite.setTextColor(getResources().getColor(R.color.textcolor));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.mImg_head, R.id.mLin_selling, R.id.mLin_sold, R.id.mLin_bought, R.id.mLin_buying, R.id.mLin_favorite, R.id.mLin_info, R.id.mLin_renzheng, R.id.mLin_yijian, R.id.mLin_kefu})
    public void onClick(View view) {
        clearText();
        clearImg();
        switch (view.getId()) {
            case R.id.mLin_selling:
                mImgSelling.setImageResource(R.mipmap.userinfo_selling_active);
                mTvSelling.setTextColor(Color.RED);
                break;
            case R.id.mLin_sold:
                mImgSold.setImageResource(R.mipmap.userinfo_sold_active);
                mTvSold.setTextColor(Color.RED);
                break;
            case R.id.mLin_bought:
                mImgBought.setImageResource(R.mipmap.userinfo_bought_active);
                mTvBought.setTextColor(Color.RED);
                break;
            case R.id.mLin_buying:
                mImgBuying.setImageResource(R.mipmap.userinfo_buying_active);
                mTvBuying.setTextColor(Color.RED);
                break;
            case R.id.mLin_favorite:
                mImgFavorite.setImageResource(R.mipmap.userinfo_favorite_active);
                mTvFavorite.setTextColor(Color.RED);
                break;
            case R.id.mLin_kefu:
                Intent intent = new Intent(getActivity(),Activity_KeFu.class);
                startActivity(intent);
                break;
            case R.id.mLin_yijian:
                Intent intent1 = new Intent(getActivity(),Activity_YiJian.class);
                startActivity(intent1);
                break;
            case R.id.mImg_head:
                Intent intent2 = new Intent(getActivity(), Activity_LoginRegister.class);
                startActivity(intent2);
                break;
            case  R.id.mLin_info:
                Intent intent3 = new Intent(getActivity(), Activity_XinXi.class);
                startActivity(intent3);
                break;
        }
    }

}
