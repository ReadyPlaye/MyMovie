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
import com.bw.movie.activity.MovieCinemaActivity;
import com.bw.movie.bean.BeanHot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanHot.ResultBean> list;
    private final Context context;

    public MyHotAdapter(Context context, List<BeanHot.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_film_hot, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        double score = list.get(i).getScore();
        String movieId = list.get(i).getMovieId() + "";
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            holder.tvFilmHotName.setText(name+"");
            holder.tvFilmHotScore.setText(score+"分");
            Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivFilmHot);
            holder.btnFilmHot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieCinemaActivity.class);
                    intent.putExtra("movieId",movieId);
                    intent.putExtra("name",name);
                    context.startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId",movieId);
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
        @BindView(R.id.iv_film_hot)
        ImageView ivFilmHot;
        @BindView(R.id.tv_film_hot_score)
        TextView tvFilmHotScore;
        @BindView(R.id.tv_film_hot_name)
        TextView tvFilmHotName;
        @BindView(R.id.btn_film_hot)
        Button btnFilmHot;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
