package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpActivity;
import com.bw.movie.base.EncryptUtil;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.bean.BeanWXLogin;
import com.bw.movie.event.WXLoginEvent;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.App;
import com.bw.movie.util.UserUrl;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_forget_pwd)
    Button btnForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_loglin)
    Button btnLogle;
    @BindView(R.id.ib_logle_weixin)
    ImageButton ibLogleWeixin;
    @BindView(R.id.image_logle_eye)
    ImageView imageLogleEye;
    @BindView(R.id.check_pwd)
    CheckBox checkPwd;
    @BindView(R.id.tv_view)
    TextView tvView;
    private Intent intent;

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_logle);
    //    }

    @Override
    protected void initLogic() {

        boolean check = SPUtils.getInstance().getBoolean("check");
        if (check){
            checkPwd.setChecked(check);
            etEmail.setText(SPUtils.getInstance().getString("email"));
            etPwd.setText(SPUtils.getInstance().getString("pwd"));
        }


    }

    @Override
    protected void initDate() {
        intent = new Intent(LoginActivity.this, ShopActivity.class);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_logle;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanLogin) {
                    BeanLogin bean = (BeanLogin) object;
                    if (bean.getStatus().equals("0000")) {
                    int userId = bean.getResult().getUserId();
                    String sessionId = bean.getResult().getSessionId();
                    SPUtils.getInstance().put("userId",userId+"");
                    SPUtils.getInstance().put("sessionId",sessionId);
                    startActivity(intent);
                    finish();
                }
                ToastUtils.showShort(bean.getMessage());
            } else if (object instanceof BeanWXLogin) {
                BeanWXLogin bean = (BeanWXLogin) object;
                if (bean.getStatus().equals("0000")) {
                    //对登录信息进行处理
                    int userId = bean.getResult().getUserId();
                    String sessionId = bean.getResult().getSessionId();
                    SPUtils.getInstance().put("userId",userId+"");
                    SPUtils.getInstance().put("sessionId",sessionId);
                    startActivity(intent);
                    finish();
                }
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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //微信登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onloWXEvent(WXLoginEvent event) {
        String code = event.code;
        if (code != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", code);

            basePresenter.post(UserUrl.WX_LOGIN_URL, map, BeanWXLogin.class);
        }

    }
    //2737681841@qq.com
    //123456


    @OnClick({R.id.btn_forget_pwd, R.id.tv_register, R.id.btn_loglin, R.id.ib_logle_weixin, R.id.image_logle_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_pwd:
                //没有接口
                break;
            case R.id.tv_register:
                //进入注册页面
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btn_loglin:
                //对输入的信息进行处理
                SPUtils.getInstance().put("userId","");
                String email = etEmail.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if (checkPwd.isChecked()){
                    SPUtils.getInstance().put("check",true);
                    SPUtils.getInstance().put("email",email);
                    SPUtils.getInstance().put("pwd",pwd);
                } else {
                    SPUtils.getInstance().put("check",false);
                }

                if (!email.isEmpty() && !pwd.isEmpty()) {
                    int index = email.indexOf("@");
                    if (index == -1) {
                        ToastUtils.showShort("请输入正确的邮箱！");
                        return;
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("email", email);
                    String encrypt = EncryptUtil.encrypt(pwd);
                    map.put("pwd", encrypt);
                    basePresenter.post(UserUrl.LOGIN_URL, map, BeanLogin.class);
                } else {
                    ToastUtils.showShort("信息不全");
                }


                break;
            case R.id.ib_logle_weixin:
                //微信登录
                SPUtils.getInstance().put("userId","");
                wxLogin();
                break;
            case R.id.image_logle_eye:
                //按下看见密码松开密码隐藏
                imageLogleEye.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                etPwd.setSelection(etPwd.getText().length());
                                break;
                            case MotionEvent.ACTION_UP:
                                etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                etPwd.setSelection(etPwd.getText().length());
                                break;
                        }
                        return true;
                    }
                });
                break;
        }
    }

    private void wxLogin() {
        //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
        IWXAPI wxApi = WXAPIFactory.createWXAPI(this, null);
        wxApi.registerApp(App.APP_ID);
        if (!wxApi.isWXAppInstalled()) {
            ToastUtils.showShort("您还未安装微信客户端");
            return;
        }
        //微信登录
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        //像微信发送请求
        App.mWxApi.sendReq(req);

    }

}
