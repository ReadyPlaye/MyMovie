package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.event.CinemaListEvent;
import com.bw.movie.event.MoviceCinemaEvent;
import com.bw.movie.event.RegionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyMovieCinemaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<String> list;

    public MyMovieCinemaListAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cinema_listlist, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String s = list.get(i);
        if (viewHolder instanceof MovieHolder) {
            MovieHolder holder = (MovieHolder) viewHolder;
            holder.tvCinemaListTimeList.setText(s);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MoviceCinemaEvent("1",s));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cinema_list_time_list)
        TextView tvCinemaListTimeList;
        public MovieHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
