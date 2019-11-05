package com.bw.movie.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.my.MyCommentActivity;
import com.bw.movie.activity.my.MyFollowActivity;
import com.bw.movie.activity.my.MyMessageActivity;
import com.bw.movie.activity.my.MyMovietActivity;
import com.bw.movie.activity.my.MyRecrdActivity;
import com.bw.movie.activity.my.MyReserveActivity;
import com.bw.movie.activity.my.MySeenActivity;
import com.bw.movie.activity.my.MySettingActivity;
import com.bw.movie.activity.my.MyTicketrecordActivity;
import com.bw.movie.activity.my.MyUserActivity;
import com.bw.movie.base.BaseContrat;
import com.bw.movie.base.BaseMvpFragment;
import com.bw.movie.bean.BeanDetails;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.bean.BeanUser;
import com.bw.movie.bean.BeanVersion;
import com.bw.movie.mvp.presenter.IContrat;
import com.bw.movie.mvp.presenter.Presenter;
import com.bw.movie.util.ToolUrl;
import com.bw.movie.util.UserUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseMvpFragment<IContrat.IModel, IContrat.IPresenter> implements IContrat.IView {


    @BindView(R.id.iv_my_message)
    ImageView ivMyMessage;
    @BindView(R.id.iv_my_user)
    ImageView ivMyUser;
    @BindView(R.id.iv_my_movieticket)
    ImageView ivMyMovieticket;
    @BindView(R.id.llt_my_follow)
    LinearLayout lltMyFollow;
    @BindView(R.id.llt_my_reserve)
    LinearLayout lltMyReserve;
    @BindView(R.id.llt_my_ticketrecord)
    LinearLayout lltMyTicketrecord;
    @BindView(R.id.llt_my_seen)
    LinearLayout lltMySeen;
    @BindView(R.id.llt_my_comment)
    LinearLayout lltMyComment;
    @BindView(R.id.llt_my_recordfeedback)
    LinearLayout lltMyRecordfeedback;
    @BindView(R.id.llt_my_setting)
    LinearLayout lltMySetting;
    Unbinder unbinder;
    @BindView(R.id.iv_my_pic)
    ImageView ivMyPic;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    private AlertDialog.Builder builder;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initData() {
        Map<String, Object> map = new HashMap<>();
        basePresenter.get(UserUrl.USER_INFOBY_URL, map, BeanUser.class);
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("版本更新");
        builder.setMessage("是否进行版本更新！");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showShort("更新版本");
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showShort("取消");
            }
        });
    }

    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                             Bundle savedInstanceState) {
    //        // Inflate the layout for this fragment
    //        return inflater.inflate(R.layout.fragment_my, container, false);
    //    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public BaseContrat.BasePresenter getPresenter() {
        return new Presenter();
    }

    @Override
    public void successful(Object object) {
        if (object != null) {
            if (object instanceof BeanUser) {
                BeanUser bean = (BeanUser) object;
                if (bean.getStatus().equals("0000")) {
                    String headPic = bean.getResult().getHeadPic();
                    String nickName = bean.getResult().getNickName();
                    tvMyName.setText(nickName);
                    Glide.with(getContext()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivMyPic);

                }
            } else if (object instanceof BeanVersion){
                BeanVersion version = (BeanVersion) object;
                int flag = version.getFlag();
                if (flag==1){
                    builder.show();

                } else {
                    ToastUtils.showShort("已经是最新版本了！");
                }


            }
        }
    }

    @Override
    public void failure(String error) {
        Log.e("------", error);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_my_message, R.id.iv_my_user, R.id.iv_my_movieticket, R.id.llt_my_follow, R.id.llt_my_reserve, R.id.llt_my_ticketrecord, R.id.llt_my_seen, R.id.llt_my_comment, R.id.llt_my_recordfeedback, R.id.llt_my_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_message:

                startActivity(new Intent(getContext(), MyMessageActivity.class));
                break;
            case R.id.iv_my_user:

                startActivity(new Intent(getContext(), MyUserActivity.class));
                break;
            case R.id.iv_my_movieticket:

                startActivity(new Intent(getContext(), MyMovietActivity.class));
                break;
            case R.id.llt_my_follow:

                startActivity(new Intent(getContext(), MyFollowActivity.class));
                break;
            case R.id.llt_my_reserve:

                startActivity(new Intent(getContext(), MyReserveActivity.class));
                break;
            case R.id.llt_my_ticketrecord:

                startActivity(new Intent(getContext(), MyTicketrecordActivity.class));
                break;
            case R.id.llt_my_seen:

                startActivity(new Intent(getContext(), MySeenActivity.class));
                break;
            case R.id.llt_my_comment:

                startActivity(new Intent(getContext(), MyCommentActivity.class));
                break;
            case R.id.llt_my_recordfeedback:

                startActivity(new Intent(getContext(), MyRecrdActivity.class));
                break;
            case R.id.llt_my_setting:
                Map<String,Object> map = new HashMap<>();
                basePresenter.get(ToolUrl.FNEW_VERSION_URL,map, BeanVersion.class);

//                startActivity(new Intent(getContext(), MySettingActivity.class));
                break;
        }
    }
}
