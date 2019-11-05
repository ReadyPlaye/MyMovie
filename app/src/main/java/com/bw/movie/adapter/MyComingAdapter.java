package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.bean.BeanComing;
import com.bw.movie.event.ComingFilmEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyComingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanComing.ResultBean> list;
    private final Context context;

    public MyComingAdapter(Context context, List<BeanComing.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_film_coming, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        long releaseTime = list.get(i).getReleaseTime();
        int wantSeeNum = list.get(i).getWantSeeNum();
        int whetherReserve = list.get(i).getWhetherReserve();
        int movieId = list.get(i).getMovieId();
        String format = "MM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String format1 = dateFormat.format(releaseTime);
        String format2 = "dd";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(format2);
        String format12 = dateFormat2.format(releaseTime);
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivImageComing);
            holder.tvFilmComingName.setText(name);
            holder.tvFilmComingTime.setText(format1+"月"+format12+"日上映");
            holder.tvFilmComingNum.setText(wantSeeNum+"人想看");
            if (whetherReserve==1){
                holder.btnFilmComing.setText("已预约");
            } else {
                holder.btnFilmComing.setText("预约");
            }
            holder.btnFilmComing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (whetherReserve==1){
                        ToastUtils.showShort("已预约");
                    } else {
                        EventBus.getDefault().post(new ComingFilmEvent(i,movieId));
                        holder.btnFilmComing.setText("已预约");
                    }

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    String s = movieId + "";
                    intent.putExtra("movieId",s);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_film_coming_name)
        TextView tvFilmComingName;
        @BindView(R.id.tv_film_coming_time)
        TextView tvFilmComingTime;
        @BindView(R.id.tv_film_coming_num)
        TextView tvFilmComingNum;
        @BindView(R.id.iv_image_coming)
        ImageView ivImageComing;
        @BindView(R.id.btn_film_coming)
        Button btnFilmComing;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
