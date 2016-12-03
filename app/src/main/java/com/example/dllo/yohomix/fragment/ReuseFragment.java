package com.example.dllo.yohomix.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.ReuseAdapter;
import com.example.dllo.yohomix.adapter.SearchAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.PostBody;
import com.example.dllo.yohomix.bean.SearchBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/3.
 */

public class ReuseFragment extends BaseFragment{

    private ListView reuseList;
    private ReuseAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView() {
        reuseList = bindView(R.id.reuse_list);
        mAdapter = new ReuseAdapter();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String message = bundle.get("key").toString();

        PostBody body = new PostBody();
        body.setChannelId(message);

        Gson gson = new Gson();
        String value = gson.toJson(body).toString();
        HashMap<String, String> map = new HashMap<>();

        map.put(URLValues.POST_KEY, value);
        Log.d("ReuseFragment", value);
        VolleySingleton.MyRequest(URLValues.SEARCH_ITEM, SearchBean.class, new NetListener<SearchBean>() {
            @Override
            public void successListener(SearchBean response) {
                mAdapter.setBean(response);
                reuseList.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

    }

    public static Fragment getInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("key", id);
        ReuseFragment fragment = new ReuseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
