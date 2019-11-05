package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.BeanSchedue;
import com.bw.movie.event.ChooseHallEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyChooseHallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<BeanSchedue.ResultBean> list;
    private final Context context;

    public MyChooseHallAdapter(Context context, List<BeanSchedue.ResultBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_choose_hall, viewGroup, false);
        return new HallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String screeningHall = list.get(i).getScreeningHall();
        double fare = list.get(i).getFare();
        String beginTime = list.get(i).getBeginTime();
        String endTime = list.get(i).getEndTime();
        String hallId = list.get(i).getHallId() + "";
        String scheduleId = list.get(i).getId() + "";
        if (viewHolder instanceof HallHolder){
            HallHolder holder = (HallHolder) viewHolder;
            holder.tvChooseHallHall.setText(screeningHall);
            holder.tvChooseHallPrice.setText(fare+"元");
            holder.tvChooseHallTime.setText(beginTime+"——"+endTime);
            EventBus.getDefault().post(new ChooseHallEvent(hallId,scheduleId,fare));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new ChooseHallEvent(hallId,scheduleId,fare));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HallHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_choose_hall_hall)
        TextView tvChooseHallHall;
        @BindView(R.id.tv_choose_hall_price)
        TextView tvChooseHallPrice;
        @BindView(R.id.tv_choose_hall_time)
        TextView tvChooseHallTime;
        public HallHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
