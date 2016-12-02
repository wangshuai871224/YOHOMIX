package com.example.dllo.yohomix;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;

/**
 * Created by dllo on 16/12/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private ImageView fIcon, qqIcon, clickIcon, upIcon, userDeleteImage,
                      passwordDeleteImage, eyeImage;
    private LinearLayout fIconL;
    private EditText mEditUser, mEditPassword;
    private Button loginNow;
    private TextView familyLogin;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        fIcon = bindView(R.id.f_icon);
        qqIcon = bindView(R.id.qq_icon);
        clickIcon = bindView(R.id.click_more);
        fIconL = bindView(R.id.f_icon_ll);
        upIcon = bindView(R.id.up_icon);
        mEditUser = bindView(R.id.edit_user);
        mEditPassword = bindView(R.id.edit_password);
        userDeleteImage = bindView(R.id.user_delete);
        passwordDeleteImage = bindView(R.id.password_delete_image);
        eyeImage = bindView(R.id.eye_image);
        familyLogin = bindView(R.id.family_login);
        loginNow = bindView(R.id.login_now);

        mEditUser.addTextChangedListener(this);
        mEditPassword.addTextChangedListener(this);

        setClick(this, qqIcon, clickIcon,fIcon, upIcon,mEditUser
                ,mEditPassword,userDeleteImage ,passwordDeleteImage, eyeImage
                ,familyLogin, loginNow);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.up_icon:

                fIconL.setVisibility(View.GONE);
                qqIcon.setVisibility(View.GONE);
                clickIcon.setVisibility(View.VISIBLE);
                break;
            case R.id.qq_icon:

                break;
            case R.id.click_more:
                qqIcon.setVisibility(View.VISIBLE);
                fIconL.setVisibility(View.VISIBLE);
                clickIcon.setVisibility(View.GONE);
                break;
            case R.id.user_delete:

                break;
            case R.id.password_delete_image:

                break;
            case R.id.eye_image:

                break;
            case R.id.family_login:

                break;
            case R.id.login_now:

                break;


        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        boolean s = mEditUser.getText().toString().isEmpty();
        boolean a = mEditPassword.getText().toString().isEmpty();


        if (!s){
            userDeleteImage.setVisibility(View.VISIBLE);
        }else {
            userDeleteImage.setVisibility(View.GONE);
        }

        if (!a) {
            passwordDeleteImage.setVisibility(View.VISIBLE);
        }else {
            passwordDeleteImage.setVisibility(View.GONE);
        }


        if (!s && !a){
            loginNow.setBackgroundColor(Color.GREEN);
        }else {
            loginNow.setBackgroundColor(Color.GRAY);
        }


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
