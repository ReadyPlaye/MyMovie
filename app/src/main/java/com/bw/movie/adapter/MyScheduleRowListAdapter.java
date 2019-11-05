package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bw.movie.R;
import com.bw.movie.event.MovieTicketsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyScheduleRowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final int ity;
    private final List<String> arrayList;

    public MyScheduleRowListAdapter(Context context, int ity, List<String> arrayList) {
        super();
        this.context = context;
        this.ity = ity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_row_list, viewGroup, false);
        return new RowListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String s = arrayList.get(i);
        if (viewHolder instanceof RowListHolder) {
            RowListHolder holder = (RowListHolder) viewHolder;
            if (s.equals("2")) {
                holder.ivScheduleRowSeat.setImageResource(R.drawable.oval_e8b);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i1 = ity + 1;
                    int i2 = i + 1;
//                    Log.e("---------","行"+i1+"列"+i2);
                    String seat = i1+"-"+i2;
                    String s1 = arrayList.get(i);
                    if (s1.equals("2")){
                        ToastUtils.showShort("票已售出！");
                    } else if (s1.equals("1")){
                        arrayList.set(i,"3");
                        holder.ivScheduleRowSeat.setImageResource(R.drawable.oval_e81);
                        ToastUtils.showShort("占座");
                        EventBus.getDefault().post(new MovieTicketsEvent("3",seat));
                    } else if (s1.equals("3")){
                        arrayList.set(i,"1");
                        holder.ivScheduleRowSeat.setImageResource(R.drawable.oval_fff);
                        ToastUtils.showShort("取消");
                        EventBus.getDefault().post(new MovieTicketsEvent("1",seat));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RowListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_schedule_row_seat)
        ImageView ivScheduleRowSeat;
        public RowListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
