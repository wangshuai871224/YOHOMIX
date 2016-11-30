package com.example.dllo.yohomix.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.YohoBoyBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/26.
 */
public class JournalFragment extends BaseFragment implements View.OnClickListener{

    private ImageView boyImageZero,girlImageZero,specialImageZero,
                      boyImageOne,girlImageOne,specialImageOne,
                      boyImageTwo,girlImageTwo,specialImageTwo;
    private TextView boyTvZero,girlTvZero,specialTvZero,
                     boyTvOne,girlTvOne,specialTvOne,
                     boyTvTwo,girlTvTwo,specialTvTwo;
    private TextView boyMore, girlMore, specialMore;
    private RequestQueue mRequestQueue;
    private StringRequest request;

    @Override
    protected int setLayout() {
        return R.layout.fragment_journal;
    }

    // 初始化组件(好多)
    @Override
    protected void initView() {
        boyImageZero= bindView(R.id.boy_image_zero);
        boyImageOne = bindView(R.id.boy_image_one);
        boyImageTwo = bindView(R.id.boy_image_two);

        girlImageZero= bindView(R.id.girl_image_zero);
        girlImageOne = bindView(R.id.girl_image_one);
        girlImageTwo = bindView(R.id.girl_image_two);

        specialImageZero = bindView(R.id.special_image_zero);
        specialImageOne = bindView(R.id.special_image_one);
        specialImageTwo = bindView(R.id.special_image_two);

        boyTvZero = bindView(R.id.boy_tv_zero);
        boyTvOne = bindView(R.id.boy_tv_one);
        boyTvTwo = bindView(R.id.boy_tv_two);

        girlTvZero = bindView(R.id.girl_tv_zero);
        girlTvOne = bindView(R.id.girl_tv_one);
        girlTvTwo = bindView(R.id.girl_tv_two);

        specialTvZero = bindView(R.id.special_tv_zero);
        specialTvOne = bindView(R.id.special_tv_one);
        specialTvTwo = bindView(R.id.special_tv_two);

        boyMore = bindView(R.id.boy_more);
        girlMore = bindView(R.id.girl_more);
        specialMore = bindView(R.id.special_more);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        setClick(this, boyMore, girlMore, specialMore);

    }

    @Override
    protected void initData() {
        getBoyPicture(URLValues.MAGAZINE_BOY_URL);
        getGirlPicture(URLValues.MAGAZINE_GIRL_URL);
        getSpecialPicture(URLValues.MAGAZINE_SPECIAL_URL);

    }

    private void getSpecialPicture(String url) {

        VolleySingleton.MyRequest(url, YohoBoyBean.class, new NetListener<YohoBoyBean>() {
            @Override
            public void successListener(YohoBoyBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(specialImageZero);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(specialImageOne);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(specialImageTwo);
                specialTvZero.setText(response.getData().get(0).getJournal());
                specialTvOne.setText(response.getData().get(1).getJournal());
                specialTvTwo.setText(response.getData().get(2).getJournal());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void getGirlPicture(String url) {

        VolleySingleton.MyRequest(url, YohoBoyBean.class, new NetListener<YohoBoyBean>() {
            @Override
            public void successListener(YohoBoyBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(girlImageZero);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(girlImageOne);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(girlImageTwo);
                girlTvZero.setText(response.getData().get(0).getJournal());
                girlTvOne.setText(response.getData().get(1).getJournal());
                girlTvTwo.setText(response.getData().get(2).getJournal());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void getBoyPicture(String url) {

        VolleySingleton.MyRequest(url, YohoBoyBean.class, new NetListener<YohoBoyBean>() {
            @Override
            public void successListener(YohoBoyBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(boyImageZero);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(boyImageOne);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(boyImageTwo);
                boyTvZero.setText(response.getData().get(0).getJournal());
                boyTvOne.setText(response.getData().get(1).getJournal());
                boyTvTwo.setText(response.getData().get(2).getJournal());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
//        request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                YohoBoyBean boyBean = gson.fromJson(response, YohoBoyBean.class);
//                Picasso.with(getActivity()).load(boyBean.getData().get(0).getCover()).into(boyImageZero);
//                Picasso.with(getActivity()).load(boyBean.getData().get(1).getCover()).into(boyImageOne);
//                Picasso.with(getActivity()).load(boyBean.getData().get(2).getCover()).into(boyImageTwo);
//                boyTvZero.setText(boyBean.getData().get(0).getJournal());
//                boyTvOne.setText(boyBean.getData().get(1).getJournal());
//                boyTvTwo.setText(boyBean.getData().get(2).getJournal());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        mRequestQueue.add(request);
    }

    // 点击进行二级页面请求
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boy_more:
                break;
            case R.id.girl_more:
                break;
            case R.id.special_more:
                break;
        }
    }


}
