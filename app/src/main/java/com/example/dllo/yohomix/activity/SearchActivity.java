package com.example.dllo.yohomix.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.SearchAdapter;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.bean.ReuseBean;
import com.example.dllo.yohomix.fragment.HistoryFragment;
import com.example.dllo.yohomix.fragment.SearchResultFragment;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;

/**
 * Created by dllo on 16/12/1.
 */
public class SearchActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    private ImageView deleteEdit, searchBody, searchReturn;
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
        searchBody = bindView(R.id.search_body);
        searchReturn = bindView(R.id.search_return);
        mSearch = bindView(R.id.search_edit);
        deleteEdit = bindView(R.id.delete_edit);

        searchTb = bindView(R.id.search_tb);
        searchVp = bindView(R.id.search_vp);

        mAdapter = new SearchAdapter(getSupportFragmentManager());

        // 添加edit的时时监听事件
        mSearch.addTextChangedListener(this);
        // 获取edit的焦点进行操作
        mSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
               fragmentReplace();
            }
        });

        setClick(this, searchBody, mSearch, deleteEdit, searchReturn);

    }

    // 替换搜索下面的fragment
    private void fragmentReplace() {
        FragmentManager mManager = getSupportFragmentManager();
        if (mSearch.getText().toString().isEmpty()) {
            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.replace(R.id.search_replace, new HistoryFragment());
            transaction.commit();
        }else {
            FragmentTransaction transaction = mManager.beginTransaction();
            transaction.replace(R.id.search_replace, new SearchResultFragment());
            transaction.commit();
        }


    }

    @Override
    protected void initData() {
        // 请求网络数据
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

        // 清空搜索内容
        deleteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch.setText("");
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    // 改变
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean is = mSearch.getText().toString().isEmpty();
        if (!is) {
            fragmentReplace();
            deleteEdit.setVisibility(View.VISIBLE);
        } else {
            fragmentReplace();
            deleteEdit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.search_body:
                fragmentReplace();
                break;
            case R.id.delete_edit:
                mSearch.getText().clear();
                break;
            case R.id.search_return:
                finish();
                break;
        }

    }
}
