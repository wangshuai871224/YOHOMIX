package com.example.dllo.yohomix;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.adapter.SearchAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.ReuseBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;

/**
 * Created by dllo on 16/12/1.
 */
public class SearchActivity extends BaseActivity implements TextWatcher {

    private ImageView deleteEdit;
    private EditText mSearch;
    private TabLayout searchTb;
    private ViewPager searchVp;
    private SearchAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mSearch = bindView(R.id.search_edit);
        deleteEdit = bindView(R.id.delete_edit);

        searchTb = bindView(R.id.search_tb);
        searchVp = bindView(R.id.search_vp);
        mAdapter = new SearchAdapter(getSupportFragmentManager());

        mSearch.addTextChangedListener(this);
    }

    @Override
    protected void initData() {
        VolleySingleton.MyRequest(URLValues.SEARCH_TAB, ReuseBean.class, new NetListener<ReuseBean>() {
            @Override
            public void successListener(ReuseBean response) {
                mAdapter.setBean(response);
                searchVp.setAdapter(mAdapter);
                searchTb.setupWithViewPager(searchVp);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean is = mSearch.getText().toString().isEmpty();
        if (!is) {
            deleteEdit.setVisibility(View.VISIBLE);
        } else {
            deleteEdit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
