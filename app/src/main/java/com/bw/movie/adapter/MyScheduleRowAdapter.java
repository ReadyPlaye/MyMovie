package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.BeanMovieSchedule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyScheduleRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<BeanMovieSchedule.ResultBean> list;
    private final int row;
    private int rownul=0;

    public MyScheduleRowAdapter(Context context, List<BeanMovieSchedule.ResultBean> list, int row) {
        super();
        this.context = context;
        this.list = list;
        this.row = row;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_row, viewGroup, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String s = i + 1 + "";
//        Log.e("---------","++行"+rownul);
        List<String> arrayList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            String row = list.get(j).getRow();
            String status = list.get(j).getStatus() + "";
            if (row.equals(s)){
                rownul++;
                arrayList.add(status);

            }
        }
        String seat = list.get(rownul-1).getSeat();
//        Log.e("---------","行"+seat);
        int seatrow = Integer.parseInt(seat);

        if (viewHolder instanceof RowHolder){
            RowHolder holder = (RowHolder) viewHolder;
            MyScheduleRowListAdapter adapter = new MyScheduleRowListAdapter(context,i,arrayList);
            GridLayoutManager linearLayoutManager = new GridLayoutManager(context,seatrow);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.recyRowAdapter.setLayoutManager(linearLayoutManager);
            holder.recyRowAdapter.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return row;
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recy_row_adapter)
        RecyclerView recyRowAdapter;
        public RowHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
