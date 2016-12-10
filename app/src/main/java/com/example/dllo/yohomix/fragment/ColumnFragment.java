package com.example.dllo.yohomix.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.activity.ColumnActivity;
import com.example.dllo.yohomix.adapter.ColumnAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.ColumnBean;
import com.example.dllo.yohomix.bean.ColumnPicBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.MyApp;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.example.dllo.yohomix.widget.CircleDrawable;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


import java.util.HashMap;

/**
 * Created by dllo on 16/11/23.
 */

public class ColumnFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {

    private SwipeToLoadLayout mToLoadLayout;
    private ListView mListView;
    private ColumnAdapter mAdapter;
    private TextView title, description, answerNum, answer, question, nick;
    private ImageView banner, headPic, titlePic;
    private ColumnBean bean;

    private String createTime;
    private HashMap<String,String> map;
    private int q = 1;
    private String num = "0";
    private Gson gson;
    private String value;
    private HashMap<String,String> mapNew;


    @Override
    protected int setLayout() {
        return R.layout.fragment_column;
    }

    @Override

    protected void initView() {
        mToLoadLayout = bindView(R.id.swipe_layout);

        mListView = bindView(R.id.swipe_target);
        mAdapter = new ColumnAdapter();
        addHeader();

        mToLoadLayout.setOnLoadMoreListener(this);
        mToLoadLayout.setOnRefreshListener(this);

    }

    @Override
    protected void initData() {
        myMap();
        request();
        clickItem();
    }

    private void clickItem() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if ((position - 1) >= 0) {
                    Intent intent = new Intent(getActivity(), ColumnActivity.class);
                    ColumnBean.DataBean mBean = bean.getData().get(position - 1 );
                    intent.putExtra("title", mBean.getTitle());
                    intent.putExtra("sum", mBean.getSummary());
                    intent.putExtra("id", mBean.getId());
                    intent.putExtra("num", mBean.getTotal());
                    intent.putExtra("headUrl", mBean.getCover());
                    getActivity().startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "头布局", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // post map
    private void myMap() {
        map = new HashMap<>();
        map.put("limit", "12");
        map.put("lastTime", num);
        map.put("startTime", "0");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);

        gson = new Gson();
        value = gson.toJson(map).toString();

        mapNew = new HashMap<>();
        mapNew.put("parameters", value);
    }

    // 数据解析
    private void request() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.COLUMN_VALUE);
        VolleySingleton.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                mAdapter.setBean(response);
                // 判断数据
                if (q == 1) {
                    createTime = response.getData().get(response.getData().size() - 1).getCreate_time();
                }
                bean = response;
                mListView.setAdapter(mAdapter);
            }
            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    // 添加头布局
    public void addHeader() {

        View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.column_header, null);
        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        answerNum = (TextView) view.findViewById(R.id.answer_number);
        answer = (TextView) view.findViewById(R.id.answer);
        nick = (TextView) view.findViewById(R.id.nick);
        question = (TextView) view.findViewById(R.id.question);
        banner = (ImageView) view.findViewById(R.id.column_banner);
        headPic = (ImageView) view.findViewById(R.id.head_pick);
        titlePic = (ImageView) view.findViewById(R.id.title_pic);

        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.COLUMN_PICTURE_VALUE);

        VolleySingleton.MyRequest(URLValues.COLUMN_PICTURE_URL, ColumnPicBean.class, new NetListener<ColumnPicBean>() {
            @Override
            public void successListener(ColumnPicBean response) {
                description.setText(response.getData().getDescription());
                title.setText(response.getData().getTitle());
                answerNum.setText(String.valueOf(response.getData().getAnswers()));
                Picasso.with(getActivity()).load(response.getData().getBanner()).into(banner);
                question.setText(response.getData().getData().get(0).getQuestion());
                answer.setText(response.getData().getData().get(0).getAnswer());
                nick.setText(response.getData().getData().get(0).getNick());
                // 本地画圆
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.static_shoe);
                CircleDrawable circleDrawable = new CircleDrawable(bitmap);
                titlePic.setImageDrawable(circleDrawable);

                Picasso.with(getActivity()).load(response.getData().getData().get(0).getHeadpic()).transform(new CircleTransform()).into(headPic);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);

        mListView.addHeaderView(view);
    }

    // 下拉加载
    @Override
    public void onLoadMore() {

        // 让其第一个接口的数据只加载一遍
        if (q == 1) {
            num = createTime;
            map.put("lastTime", num);
            value = gson.toJson(map).toString();
            mapNew.put(URLValues.POST_KEY, value);
            q = -1;
        }

        mToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                mToLoadLayout.setLoadingMore(false);
                addMe();
            }
        },2000);
    }
    public  void addMe(){

        VolleySingleton.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                 mAdapter.addBean(response);
                 bean.getData().addAll(response.getData());
                //接口中是空的,让其重复加载
                if (response.getData().isEmpty()) {
                    num = "0";
                } else {
                    num = response.getData().get(response.getData().size() - 1).getCreate_time();
                }

                //更新map
                map.put("lastTime", num);
                value = gson.toJson(map).toString();
                mapNew.put("parameters", value);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapNew);

    }

    // 上啦刷新
    @Override
    public void onRefresh() {

        mToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mToLoadLayout.setRefreshing(false);
                request();
            }
        },2000);

    }

    // 网络画圆
    class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight()) / 2;

            int x = (source.getWidth() - size)/ 2;
            int y = (source.getHeight() - size ) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }


}
