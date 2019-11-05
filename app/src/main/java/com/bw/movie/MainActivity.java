package com.bw.movie;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.bw.movie.activity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Timer timer;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setTranslucentDiff(this);
        // 沉浸效果
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        intent = new Intent(MainActivity.this, LoginActivity.class);
        timer = new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
                startActivity(intent);
                finish();
            }
        };


        boolean box = SPUtils.getInstance().getBoolean("box");
        if (box){
            //跳转
            startActivity(intent);
            finish();
        } else {
            SPUtils.getInstance().put("box",true);
            timer.schedule(tast,3000);
        }



    }

//    public void logleAct(View view) {
//        timer.cancel();
//        edit.putBoolean("box",true);
//        startActivity(intent);
//        finish();
//    }
}
