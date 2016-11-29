package com.example.dllo.yohomix.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.WallPaperAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.WallPaperBean;
import com.example.dllo.yohomix.tools.URLValues;
import com.google.gson.Gson;

import java.security.PrivateKey;

/**
 * Created by dllo on 16/11/26.
 */
public class WallPaperFragment extends BaseFragment {

    private ListView wallList;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private WallPaperAdapter mAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_wall_pager;
    }

    @Override
    protected void initView() {
        wallList = bindView(R.id.wall_paper_list);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mAdapter = new WallPaperAdapter();
    }

    @Override
    protected void initData() {
        mStringRequest = new StringRequest(URLValues.WALL_PAPER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                WallPaperBean bean = gson.fromJson(response, WallPaperBean.class);
                mAdapter.setBean(bean);
                wallList.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(mStringRequest);
    }
}
