package com.example.dllo.yohomix.fragment;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yohomix.activity.LoginActivity;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;
import com.wevey.selector.dialog.NormalAlertDialog;
/**
 * Created by dllo on 16/12/6.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private ImageView fIcon, qqIcon, clickIcon, upIcon, userDeleteImage,
            passwordDeleteImage, eyeImage, loginHeadImage, headSmallImage;
    private LinearLayout fIconL, headImageLL;
    private EditText mEditUser, mEditPassword;
    private Button loginNow;
    private TextView familyLogin, registerLogin;
    private boolean show = false;
    private NormalAlertDialog mDialog;


    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        findView();
        mEditUser.addTextChangedListener(this);
        mEditPassword.addTextChangedListener(this);
        editTextFocus();
        setClick(this, qqIcon, clickIcon, fIcon, upIcon, mEditUser
                , mEditPassword, userDeleteImage, passwordDeleteImage, eyeImage
                , familyLogin, loginNow,registerLogin);
    }

    // 获取焦点, 操作其他动作
    private void editTextFocus() {
        mEditUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    headImageLL.setVisibility(View.GONE);
                    headSmallImage.setVisibility(View.VISIBLE);
                    // 此处为得到焦点时的处理内容
                } else {
                    headImageLL.setVisibility(View.VISIBLE);
                    // 此处为失去焦点时的处理内容
                    headSmallImage.setVisibility(View.GONE);
                }
            }
        });
        mEditPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    headImageLL.setVisibility(View.GONE);
                    headSmallImage.setVisibility(View.VISIBLE);
                    // 此处为得到焦点时的处理内容
                } else {
                    headImageLL.setVisibility(View.VISIBLE);
                    // 此处为失去焦点时的处理内容
                    headSmallImage.setVisibility(View.GONE);
                }
            }
        });
    }

    // 初始化组件
    private void findView() {
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
        loginHeadImage = bindView(R.id.login_head_image);
        headSmallImage = bindView(R.id.head_small_image);
        headImageLL = bindView(R.id.head_image_ll);
        registerLogin = bindView(R.id.register_login);

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
                mEditUser.setText("");
                break;
            case R.id.password_delete_image:
                mEditPassword.setText("");
                break;
            case R.id.eye_image:
                show = !show;
                if (show) {
                    eyeImage.setImageResource(R.mipmap.login_password_see_icon);
                    mEditPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// 显示密码
                    mEditPassword.setSelection(mEditPassword.length());
// 与上面效果一样  mEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    eyeImage.setImageResource(R.mipmap.login_password_unsee_icon);
                    mEditPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mEditPassword.setSelection(mEditPassword.length());
//  mEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.family_login:
                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
                showDialog();
                break;
            case R.id.login_now:

                break;
            case R.id.register_login:
                LoginActivity.loginVp.setCurrentItem(1);
                break;


        }
    }

    private void showDialog() {
        mDialog = new NormalAlertDialog.Builder(getActivity())
                .setHeight(0.3f)
                .setWidth(0.9f).setTitleVisible(true)
                .setTitleText("YoHo!Family")
                .setTitleTextColor(R.color.colorBlack)
                .setContentText("Yoho!Family账号可登录YohoBuy!有货、Yoho!Now及SHOW")
                .setContentTextColor(R.color.colorBlack)
                .setSingleMode(true)
                .setSingleButtonText("确定")
                .setSingleButtonTextColor(R.color.colorBlack)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                }).build();
        mDialog.show();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        // 不要写!= null,  要写isEmpty();
        boolean s = mEditUser.getText().toString().isEmpty();
        boolean a = mEditPassword.getText().toString().isEmpty();

        if (!s) {
            userDeleteImage.setVisibility(View.VISIBLE);
        } else {
            userDeleteImage.setVisibility(View.GONE);
        }

        if (!a) {
            passwordDeleteImage.setVisibility(View.VISIBLE);
        } else {
            passwordDeleteImage.setVisibility(View.GONE);
        }


        if (!s && !a) {
            loginNow.setBackgroundColor(Color.GREEN);
        } else {
            loginNow.setBackgroundColor(Color.GRAY);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
