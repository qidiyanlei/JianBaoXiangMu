package bgs.com.jianbao11.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bgs.com.jianbao11.R;


/**
 * Created by 醇色 on 2016/11/25.
 */

public class Fragment_Want extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.fragment_want,null);
        return v;
    }
}
