package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.util.App;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI wxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        App.mWxApi.handleIntent(getIntent(), this);
        EventBus.getDefault().register(this);
        wxapi = WXAPIFactory.createWXAPI(this, App.APP_ID);
        wxapi.handleIntent(getIntent(),this);


    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                ToastUtils.showShort("支付成功",this);
//                EventBus.getDefault().postSticky(new WXPayEvent("支付成功"));
                finish();
                break;
            case -1:
                ToastUtils.showShort("支付失败",this);
//                EventBus.getDefault().postSticky(new WXPayEvent("支付失败"));
                finish();
                break;
            case -2:
                ToastUtils.showShort("支付取消",this);
//                EventBus.getDefault().postSticky(new WXPayEvent("支付取消"));
                finish();
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onlentEvent(String event){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
