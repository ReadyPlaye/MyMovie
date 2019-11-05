package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.BeanRegionList;
import com.bw.movie.event.MoviceCinemaEvent;
import com.bw.movie.event.RegionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRegionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanRegionList.ResultBean> list;
    private final Context context;

    public MyRegionListAdapter(Context context, List<BeanRegionList.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_region_list, viewGroup, false);
        return new Holdelreg(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String regionName = list.get(i).getRegionName();
        String s = list.get(i).getRegionId() + "";
        if (viewHolder instanceof Holdelreg){
            Holdelreg holdel = (Holdelreg) viewHolder;
            holdel.tvRegionList.setText(regionName);
            holdel.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MoviceCinemaEvent("2",s));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holdelreg extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_region_list)
        TextView tvRegionList;
        public Holdelreg(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
