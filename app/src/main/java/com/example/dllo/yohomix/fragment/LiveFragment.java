package com.example.dllo.yohomix.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.LiveAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.LiveBean;
import com.example.dllo.yohomix.tools.URLValues;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/11/25.
 */

public class LiveFragment extends BaseFragment{


    private ListView mLiveList;
    private LiveAdapter mLiveAdapter;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_video;
    }

    @Override
    protected void initView() {
        mLiveList = bindView(R.id.video_list);
        mLiveAdapter = new LiveAdapter();
        mRequestQueue = Volley.newRequestQueue(getActivity());

    }

    @Override
    protected void initData() {

//        VolleySingleton.MyRequest(URLValues.LIVE_URL, LiveBean.class, new NetListener<LiveBean>() {
//            @Override
//            public void successListener(LiveBean response) {
//                mLiveAdapter.setLiveBean(response);
//                mLiveList.setAdapter(mLiveAdapter);
//            }
//
//            @Override
//            public void errorListener(VolleyError error) {
//
//            }
//        });
        mStringRequest = new StringRequest(URLValues.LIVE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LiveBean bean = gson.fromJson(response, LiveBean.class);
                mLiveAdapter.setLiveBean(bean);
                mLiveList.setAdapter(mLiveAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(mStringRequest);
    }

}
