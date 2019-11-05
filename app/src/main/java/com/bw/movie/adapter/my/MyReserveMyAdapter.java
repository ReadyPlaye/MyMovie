package com.bw.movie.adapter.my;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.bean.BeanMyReserve;
import com.bw.movie.event.ComingFilmEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyReserveMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<BeanMyReserve.ResultBean> list;

    public MyReserveMyAdapter(Context context, List<BeanMyReserve.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myreserve, viewGroup, false);
        return new ReserveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        long releaseTime = list.get(i).getReleaseTime();
        int wantSeeNum = list.get(i).getWantSeeNum();
        int movieId = list.get(i).getMovieId();
        String format = "MM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String format1 = dateFormat.format(releaseTime);
        String format2 = "dd";
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(format2);
        String format12 = dateFormat2.format(releaseTime);
        if (viewHolder instanceof ReserveHolder) {
            ReserveHolder holder = (ReserveHolder) viewHolder;
            Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivImageComingMy);
            holder.tvMyComingName.setText(name);
            holder.tvMyComingTime.setText(format1+"月"+format12+"日上映");
            holder.tvMyComingNum.setText(wantSeeNum+"人想看");

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

    public class ReserveHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_my_coming_name)
        TextView tvMyComingName;
        @BindView(R.id.tv_my_coming_time)
        TextView tvMyComingTime;
        @BindView(R.id.tv_my_coming_num)
        TextView tvMyComingNum;
        @BindView(R.id.iv_image_coming_my)
        ImageView ivImageComingMy;
        public ReserveHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
