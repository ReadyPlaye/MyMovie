package com.bw.movie.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.FragmentAdapter;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.FilmFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.util.CustomViewPager;
import com.bw.movie.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_shop)
    CustomViewPager viewPagerShop;
    @BindView(R.id.btn_film)
    RadioButton btnFilm;
    @BindView(R.id.btn_cinema)
    RadioButton btnCinema;
    @BindView(R.id.btn_my)
    RadioButton btnMy;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private Unbinder bind;
    private int dip2px20;
    private int dip2px40;
    private int dip2px10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        // 沉浸效果
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        //dp和px的转换
        dip2px10 = DensityUtil.dip2px(this, 10);
        dip2px20 = DensityUtil.dip2px(this, 20);
        dip2px40 = DensityUtil.dip2px(this, 40);
        bind = ButterKnife.bind(this);
        viewPagerShop.setOffscreenPageLimit(2);

        List<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new FilmFragment());
        arrayList.add(new CinemaFragment());
        arrayList.add(new MyFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), arrayList);
        viewPagerShop.setAdapter(adapter);
        viewPagerShop.setOffscreenPageLimit(2);
        btnFilm.setChecked(true);
        getButtonJudge();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_film:
                        getButtonJudge();
                        viewPagerShop.setCurrentItem(0);
                        break;
                    case R.id.btn_cinema:
                        viewPagerShop.setCurrentItem(1);
                        getButtonJudge();
                        break;
                    case R.id.btn_my:
                        viewPagerShop.setCurrentItem(2);
                        getButtonJudge();
                        break;
                }
            }
        });
    }

    //对RadioButton进行代码修改属性
    private void getButtonJudge() {

        if (btnFilm.isChecked()){
            btnFilm.setText("电影");
            btnFilm.setBackgroundResource(R.drawable.shape_film_true);
            btnFilm.setPadding(dip2px20,dip2px10,dip2px20,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.yingpian);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnFilm.setCompoundDrawables(img_off, null, null, null); //设置左图标

        } else {
            btnFilm.setText("");
            btnFilm.setBackgroundResource(R.drawable.shape_film_false);
            btnFilm.setPadding(dip2px40,dip2px10,dip2px40,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.movie_yingpian);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnFilm.setCompoundDrawables(img_off, null, null, null); //设置左图标

        }
        if (btnCinema.isChecked()){
            btnCinema.setText("影院");
            btnCinema.setBackgroundResource(R.drawable.shape_film_true);
            btnCinema.setPadding(dip2px20,dip2px10,dip2px20,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.shape);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnCinema.setCompoundDrawables(img_off, null, null, null); //设置左图标

        } else {
            btnCinema.setText("");
            btnCinema.setBackgroundResource(R.drawable.shape_film_false);
            btnCinema.setPadding(dip2px40,dip2px10,dip2px40,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.cinema);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnCinema.setCompoundDrawables(img_off, null, null, null); //设置左图标

        }
        if (btnMy.isChecked()){
            btnMy.setText("我的");
            btnMy.setBackgroundResource(R.drawable.shape_film_true);
            btnMy.setPadding(dip2px20,dip2px10,dip2px20,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.my_1);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnMy.setCompoundDrawables(img_off, null, null, null); //设置左图标

        } else {
            btnMy.setText("");
            btnMy.setBackgroundResource(R.drawable.shape_film_false);
            btnMy.setPadding(dip2px40,dip2px10,dip2px40,dip2px10);

            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.my);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            btnMy.setCompoundDrawables(img_off, null, null, null); //设置左图标

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    //false
//    android:padding="40dp"
//    android:drawableLeft="@drawable/cinema"
    //true
    //    android:text="电影"
    //    android:padding="15dp"
    //    android:drawableLeft="@drawable/yingpian"
    //    android:background="@drawable/shape_film_true"
//    public void getAndroiodScreenProperty() {
//        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;         // 屏幕宽度（像素）
//        int height = dm.heightPixels;       // 屏幕高度（像素）
//        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
//        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
//        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
//        int screenHeight = (int) (height / density);// 屏幕高度(dp)



//        Log.d("h_bl", "屏幕宽度（像素）：" + width);
//        Log.d("h_bl", "屏幕高度（像素）：" + height);
//        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
//        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
//        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
//        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
//    }
}
