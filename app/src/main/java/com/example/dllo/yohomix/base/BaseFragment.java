package com.example.dllo.yohomix.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected  <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    protected void setClick(View.OnClickListener listener,View ... views){
        for (View view : views){
            view.setOnClickListener(listener);
        }
    }

}
