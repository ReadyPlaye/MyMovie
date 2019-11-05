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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.activity.MovieCinemaActivity;
import com.bw.movie.bean.BeanCearch;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanCearch.ResultBean> list;
    private final Context context;

    public MySearchAdapter(Context context, List<BeanCearch.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        double score = list.get(i).getScore();
        String director = list.get(i).getDirector();
        String starring = list.get(i).getStarring();
        String movieId = list.get(i).getMovieId() + "";
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            holder.tvSearchName.setText(name);
            holder.tvSearchDirector.setText("导演："+director);
            holder.tvSearchScore.setText("评分："+score+"分");
            holder.tvSearchStarring.setText("主演："+starring);
            Glide.with(context).load(imageUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(5))).into(holder.ivSearchPic);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieId",movieId);
                    context.startActivity(intent);
                }
            });
            holder.btnFilmSearchFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieCinemaActivity.class);
                    intent.putExtra("movieId",movieId);
                    intent.putExtra("name",name);
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
        @BindView(R.id.iv_search_pic)
        ImageView ivSearchPic;
        @BindView(R.id.tv_search_name)
        TextView tvSearchName;
        @BindView(R.id.tv_search_director)
        TextView tvSearchDirector;
        @BindView(R.id.tv_search_starring)
        TextView tvSearchStarring;
        @BindView(R.id.tv_search_score)
        TextView tvSearchScore;
        @BindView(R.id.btn_film_search_film)
        Button btnFilmSearchFilm;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
