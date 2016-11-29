package com.example.dllo.yohomix.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.VideoVideoAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.VideoBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;

import java.util.HashMap;

/**
 * Created by dllo on 16/11/25.
 */

public class VideoVideoFragment extends BaseFragment{

    private ListView mVideoList;
    private VideoVideoAdapter mVideoVideoAdapter;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_video;
    }

    @Override
    protected void initView() {
        mVideoList = bindView(R.id.video_list);
        mVideoVideoAdapter = new VideoVideoAdapter();
        mRequestQueue = Volley.newRequestQueue(getActivity());

    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap();
        map.put(URLValues.POST_KEY, URLValues.VIDEO_VALUE);
        VolleySingleton.MyRequest(URLValues.VIDEO_URL, VideoBean.class, new NetListener<VideoBean>() {
            @Override
            public void successListener(VideoBean response) {
                mVideoVideoAdapter.setVideoBean(response);
                mVideoList.setAdapter(mVideoVideoAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }
}
