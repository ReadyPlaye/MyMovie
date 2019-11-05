package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.base.EncryptUtil;
import com.bw.movie.bean.BeanEmail;
import com.bw.movie.bean.BeanRegister;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.et_rst_nic)
    EditText etRstNic;
    @BindView(R.id.et_rst_email)
    EditText etRstEmail;
    @BindView(R.id.et_rst_pwd)
    EditText etRstPwd;
    @BindView(R.id.et_rst_code)
    EditText etRstCode;
    @BindView(R.id.btn_rst_gain_code)
    Button btnRstGainCode;
    @BindView(R.id.tv_rst_login)
    TextView tvRstLogin;
    @BindView(R.id.btn_rst_register)
    Button btnRstRegister;
    @BindView(R.id.image_regist_eye)
    ImageView imageRegistEye;
    private Intent intent;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_register);
    //    }

    @Override
    protected void initLogic() {
        setEditTextInhibitInputSpaChat(etRstNic);

    }

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpaChat(EditText editText) {
        InputFilter filter_space = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" "))
                    return "";
                else
                    return null;
            }
        };
        //禁止输入特殊字符
        InputFilter filter_speChat = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                String speChat = "[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(charSequence.toString());
                if (matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter_space, filter_speChat});
    }

    @Override
    protected void initDate() {
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanRegister) {
                BeanRegister bean = (BeanRegister) object;
                if (bean.getStatus().equals("0000")) {
                    startActivity(intent);
                    finish();
                }
                ToastUtils.showShort(bean.getMessage());
            } else if (object instanceof BeanEmail) {
                BeanEmail bean = (BeanEmail) object;
                ToastUtils.showShort(bean.getMessage());
            }
        }

    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_rst_gain_code, R.id.tv_rst_login, R.id.btn_rst_register, R.id.image_regist_eye})
    public void onViewClicked(View view) {
        String nic = etRstNic.getText().toString().trim();
        String rstemail = etRstEmail.getText().toString().trim();
        String pwd = etRstPwd.getText().toString().trim();
        String code = etRstCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_rst_gain_code:
                //                String email = etRstEmail.getText().toString().trim();
                int index = rstemail.indexOf("@");
                if (index == -1) {
                    ToastUtils.showShort("请输入正确的邮箱！");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("email", rstemail);
                basePresenter.post(UserUrl.EMAIL_CODE_URL, map, BeanEmail.class);
                break;
            case R.id.tv_rst_login:
                //进入登录页面
                startActivity(intent);
                finish();
                break;
            case R.id.image_regist_eye:
                //按下看见密码松开密码隐藏
                imageRegistEye.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                etRstPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                etRstPwd.setSelection(etRstPwd.getText().length());
                                break;
                            case MotionEvent.ACTION_UP:
                                etRstPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                etRstPwd.setSelection(etRstPwd.getText().length());
                                break;
                        }
                        return true;
                    }
                });
                break;
            case R.id.btn_rst_register:
                //对注册信息进行处理
                if (!nic.isEmpty() && !pwd.isEmpty() && !rstemail.isEmpty() && !code.isEmpty()) {
                    int index2 = rstemail.indexOf("@");
                    if (index2 == -1) {
                        ToastUtils.showShort("请输入正确的邮箱！");
                        return;
                    }
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("nickName", nic);
                    String encrypt = EncryptUtil.encrypt(pwd);
                    map1.put("pwd", encrypt);
                    map1.put("email", rstemail);
                    map1.put("code", code);
                    basePresenter.post(UserUrl.REGISTER_URL, map1, BeanRegister.class);
                } else {
                    ToastUtils.showShort("信息不全");
                }

                break;
        }
    }
}
