package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.event.WXLoginEvent;
import com.bw.movie.util.App;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mContext = this;
        //这句没有写,是不能执行回调的方法的
        App.mWxApi.handleIntent(getIntent(), this);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onlentEvent(String event){

        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        WXEntryActivity.TAG.re
        EventBus.getDefault().unregister(this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
//        if(baseResp.getType()==ConstantsAPI.COMMAND_PAY_BY_WX){
//                            Log.d(TAG,"onPayFinish,errCode="+baseResp.errCode);
//            String errCode = baseResp.errCode + "";
//            EventBus.getDefault().postSticky(new WXPayEvent(errCode));
//            finish();
////                            AlertDialog.Builder builder=new AlertDialog.Builder(this);
////                            builder.setTitle(R.string.app_tip);
//         } else if (baseResp.getType()==RETURN_MSG_TYPE_LOGIN){
//            finish();
//        }
        Log.i(TAG, "onResp:------>");
        Log.i(TAG, "error_code:---->" + baseResp.errCode);
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                ToastUtils.showShort("拒绝授权微信登录", mContext);
                finish();
                break;
//            public void onResp(BaseResp resp){
//            if(resp.getType()==ConstantsAPI.COMMAND_PAY_BY_WX){
//                Log.d(TAG,"onPayFinish,errCode="+resp.errCode);
//                AlertDialog.Builder builder=newAlertDialog.Builder(this);
//                builder.setTitle(R.string.app_tip);
//            }
//    }
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                }
                ToastUtils.showShort(message, mContext);
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    Log.i(TAG, "code:------>" + code);

                    //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
//                    WXLoginUtils().getWXLoginResult(code, this);
                    EventBus.getDefault().post(new WXLoginEvent(code));

                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    ToastUtils.showShort("微信分享成功", mContext);
                }
                finish();
                break;
        }
    }
    /**
     * 获取access_token：
     *
     * @param code 用户或取access_token的code，仅在ErrCode为0时有效
     */
//    private void getAccessToken(final String code) {
//        Map<String, Object> params = new HashMap();
//        params.put("appid", MainConstant.WX.WEIXIN_APP_ID);
//        params.put("secret", MainConstant.WX.WEIXIN_APP_SECRET);
//        params.put("code", code);
//        params.put("grant_type", "authorization_code");
//        HttpUtils.getWXAccessTokenBean(URLConstant.URL_WX_BASE, params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WXAccessTokenBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted:-------->");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.i(TAG, "onError:-------->" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(WXAccessTokenBean wxAccessTokenBean) {
//                        Log.i(TAG, "onNext: ----->");
//                        String access_token = wxAccessTokenBean.getAccess_token(); //接口调用凭证
//                        String openid = wxAccessTokenBean.getOpenid(); //授权用户唯一标识
//                        //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
//                        String unionid = wxAccessTokenBean.getUnionid();
//                        Log.i(TAG, "access_token:----->" + access_token);
//                        Log.i(TAG, "openid:----->" + openid);
//                        Log.i(TAG, "unionid:----->" + unionid);
//                        getWXUserInfo(access_token, openid, unionid);
//                    }
//                });
//    }
    /**
     * 获取微信登录，用户授权后的个人信息
     *
     * @param access_token
     * @param openid
     * @param unionid
     */
//    private void getWXUserInfo(final String access_token, final String openid, final String unionid) {
//        Map<String, Object> params = new HashMap();
//        params.put("access_token", access_token);
//        params.put("openid", openid);
//        HttpUtils.getWXUserInfoBean(URLConstant.URL_WX_BASE, params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WXUserInfoBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "getWXUserInfo:--------> onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.i(TAG, "getWXUserInfo:--------> onError" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(WXUserInfoBean wxUserInfoBean) {
//                        Log.i(TAG, "getWXUserInfo:--------> onNext");
//                        String country = wxUserInfoBean.getCountry(); //国家
//                        String province = wxUserInfoBean.getProvince(); //省
//                        String city = wxUserInfoBean.getCity(); //市
//                        String nickname = wxUserInfoBean.getNickname(); //用户名
//                        int sex = wxUserInfoBean.getSex(); //性别
//                        String headimgurl = wxUserInfoBean.getHeadimgurl(); //头像url
//                        Log.i(TAG, "country:-------->" + country);
//                        Log.i(TAG, "province:-------->" + province);
//                        Log.i(TAG, "city:-------->" + city);
//                        Log.i(TAG, "nickname:-------->" + nickname);
//                        Log.i(TAG, "sex:-------->" + sex);
//                        Log.i(TAG, "headimgurl:-------->" + headimgurl);
//                    }
//                });
//    }
}
