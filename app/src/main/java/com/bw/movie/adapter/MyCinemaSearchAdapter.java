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
import com.bw.movie.bean.BeanCearchCinema;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyCinemaSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanCearchCinema.ResultBean> list;
    private final Context context;
    private final int stey;
    //附近1

    public MyCinemaSearchAdapter(Context context, List<BeanCearchCinema.ResultBean> list, int stey) {
        super();
        this.context = context;
        this.list = list;
        this.stey = stey;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cinema_search, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String logo = list.get(i).getLogo();
        String name = list.get(i).getName();
        String address = list.get(i).getAddress();
        int distance = list.get(i).getDistance();
        String id = list.get(i).getId()+"";
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            holder.tvCinemaName.setText(name);
            holder.tvCinemaAddress.setText(address);
            if (stey==1){
                holder.tvCinemaDistance.setVisibility(View.VISIBLE);
                holder.tvCinemaDistance.setText(distance+"米");
            } else {
                holder.tvCinemaDistance.setVisibility(View.GONE);
            }

            Glide.with(context).load(logo).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivCinemaLogo);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CinemaDetailActivity.class);
                    intent.putExtra("cinemaId",id);
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
        @BindView(R.id.iv_cinema_logo)
        ImageView ivCinemaLogo;
        @BindView(R.id.tv_cinema_name)
        TextView tvCinemaName;
        @BindView(R.id.tv_cinema_address)
        TextView tvCinemaAddress;
        @BindView(R.id.tv_cinema_distance)
        TextView tvCinemaDistance;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
