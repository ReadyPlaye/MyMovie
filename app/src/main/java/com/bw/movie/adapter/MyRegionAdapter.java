package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailActivity;
import com.bw.movie.bean.BeanRegion;
import com.bw.movie.event.RegionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRegionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanRegion.ResultBean> list;
    private final Context context;

    public MyRegionAdapter(Context context, List<BeanRegion.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_region_list, viewGroup, false);
        return new Holdeg(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String regionName = list.get(i).getName();
        String regionId = list.get(i).getId()+"";
        if (viewHolder instanceof Holdeg){
            Holdeg holdel = (Holdeg) viewHolder;
            holdel.tvRegionList.setText(regionName);
            holdel.tvRegionList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CinemaDetailActivity.class);
                    intent.putExtra("cinemaId",regionId);
                    context.startActivity(intent);

                   }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holdeg extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_region_list)
        TextView tvRegionList;
        public Holdeg(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
