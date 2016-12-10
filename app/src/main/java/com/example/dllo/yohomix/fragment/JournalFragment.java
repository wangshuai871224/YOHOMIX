package com.example.dllo.yohomix.fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.SearchItemBean;
import com.example.dllo.yohomix.bean.YohoBoyBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.DBTool;
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
                      boyImageTwo,girlImageTwo,specialImageTwo, popImage, downLoadClose;
    private TextView boyTvZero,girlTvZero,specialTvZero,
                     boyTvOne,girlTvOne,specialTvOne,
                     boyTvTwo,girlTvTwo,specialTvTwo, picContent, downLoad;
    private TextView boyMore, girlMore, specialMore;
    private RequestQueue mRequestQueue;
    private StringRequest request;
    private PopupWindow mPopupWindow;
    private RelativeLayout popMiss;
    YohoBoyBean boyBean, girlBean, specialBean;
    private String imgUrl , content;
    private SearchItemBean mBean;

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

        setClick(this, boyMore, girlMore, specialMore, boyImageZero,boyImageOne,boyImageTwo
                   , girlImageZero,girlImageOne,girlImageTwo
                   , specialImageZero,specialImageOne,specialImageTwo);

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
                specialBean = response;
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
                girlBean = response;
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
                boyBean = response;
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
            case R.id.boy_image_zero:
                imgUrl = boyBean.getData().get(0).getCover();
                content = boyBean.getData().get(0).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.boy_image_one:
                imgUrl = boyBean.getData().get(1).getCover();
                content = boyBean.getData().get(1).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.boy_image_two:
                imgUrl = boyBean.getData().get(2).getCover();
                content = boyBean.getData().get(2).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.girl_image_zero:
                imgUrl = girlBean.getData().get(0).getCover();
                content = girlBean.getData().get(0).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.girl_image_one:
                imgUrl = girlBean.getData().get(1).getCover();
                content = girlBean.getData().get(1).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.girl_image_two:
                imgUrl = girlBean.getData().get(2).getCover();
                content = girlBean.getData().get(2).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.special_image_zero:
                imgUrl = specialBean.getData().get(0).getCover();
                content = specialBean.getData().get(0).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.special_image_one:
                imgUrl = specialBean.getData().get(1).getCover();
                content = specialBean.getData().get(1).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.special_image_two:
                imgUrl = specialBean.getData().get(2).getCover();
                content = specialBean.getData().get(2).getJournal();
                popWindow(imgUrl, content);
                break;
            case R.id.download_close:
                if (mPopupWindow!= null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.down_load:
                DBTool.getInstance().insert(mBean);
                break;
            case R.id.pop_wind:
                if (mPopupWindow!= null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                break;
        }
    }
    // 点击图片显示,并提示下载
    public void popWindow(String url, String text) {
        mPopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.magazine_pic, null);
        popImage = (ImageView) view.findViewById(R.id.pop_image);
        downLoad = (TextView) view.findViewById(R.id.down_load);
        downLoadClose = (ImageView) view.findViewById(R.id.download_close);
        picContent = (TextView) view.findViewById(R.id.pic_content);
        popMiss = (RelativeLayout) view.findViewById(R.id.pop_wind);

        Picasso.with(getActivity()).load(url).into(popImage);
        picContent.setText(text);

        downLoad.setOnClickListener(this);
        downLoadClose.setOnClickListener(this);
        popMiss.setOnClickListener(this);

        mBean = new SearchItemBean();
        mBean.setUrl(url);
        mBean.setBody(text);

        mPopupWindow.setContentView(view);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0 , 0);
    }


}
