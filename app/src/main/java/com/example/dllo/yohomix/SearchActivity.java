package com.example.dllo.yohomix;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dllo.yohomix.base.BaseActivity;

/**
 * Created by dllo on 16/12/1.
 */
public class SearchActivity extends BaseActivity implements TextWatcher {

    private ImageView deleteEdit;
    private EditText mSearch;
    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mSearch = bindView(R.id.search_edit);
        deleteEdit = bindView(R.id.delete_edit);
        mSearch.addTextChangedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         boolean is = mSearch.getText().toString().isEmpty();
        if (!is) {
            deleteEdit.setVisibility(View.VISIBLE);
        }else {
            deleteEdit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
