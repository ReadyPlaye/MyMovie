package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailActivity;
import com.bw.movie.activity.MovieCinemaActivity;
import com.bw.movie.bean.BeanMovieCinema;
import com.bw.movie.event.MoviceCinemaEvent;
import com.bw.movie.event.RegionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyMovieCinemaSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<BeanMovieCinema.ResultBean> list;
    private final int prices;

    public MyMovieCinemaSearchAdapter(Context context, List<BeanMovieCinema.ResultBean> list, int prices) {
        super();
        this.context = context;
        this.list = list;
        this.prices=prices;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cinema_search, viewGroup, false);
        return new MovierHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String logo = list.get(i).getLogo();
        String name = list.get(i).getName();
        String address = list.get(i).getAddress();
        double price = list.get(i).getPrice();
        String cinemaId = list.get(i).getCinemaId() + "";
        if (viewHolder instanceof MovierHolder) {
            MovierHolder holder = (MovierHolder) viewHolder;
            holder.tvCinemaName.setText(name);
            holder.tvCinemaAddress.setText(address);
            if (prices==1){
                holder.tvCinemaDistance.setVisibility(View.VISIBLE);
                holder.tvCinemaDistance.setText(price+"元");
            } else {
                holder.tvCinemaDistance.setVisibility(View.GONE);
            }
            Glide.with(context).load(logo).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivCinemaLogo);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MoviceCinemaEvent("3",cinemaId));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovierHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cinema_logo)
        ImageView ivCinemaLogo;
        @BindView(R.id.tv_cinema_name)
        TextView tvCinemaName;
        @BindView(R.id.tv_cinema_address)
        TextView tvCinemaAddress;
        @BindView(R.id.tv_cinema_distance)
        TextView tvCinemaDistance;
        public MovierHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
