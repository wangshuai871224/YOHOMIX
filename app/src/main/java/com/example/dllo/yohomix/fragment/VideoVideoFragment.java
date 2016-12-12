package com.example.dllo.yohomix.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
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
    private VideoBean bean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_video;
    }

    @Override
    protected void initView() {
        mVideoList = bindView(R.id.video_list);
        mVideoVideoAdapter = new VideoVideoAdapter();

    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap();
        map.put(URLValues.POST_KEY, URLValues.VIDEO_VALUE);
        VolleySingleton.MyRequest(URLValues.VIDEO_URL, VideoBean.class, new NetListener<VideoBean>() {
            @Override
            public void successListener(VideoBean response) {
                bean = response;
                mVideoVideoAdapter.setVideoBean(response);
                mVideoList.setAdapter(mVideoVideoAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);

        mVideoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                intent.putExtra("video", bean.getData().getContent().get(i).getId());
                intent.putExtra("cidvideo", bean.getData().getContent().get(i).getCid());
                getActivity().startActivity(intent);
            }
        });
    }
}
