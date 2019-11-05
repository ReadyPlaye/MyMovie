package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyCinemaLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<String> list;

    public MyCinemaLabelAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_label, viewGroup, false);
        return new HolderLabla(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String s = list.get(i);
        if (viewHolder instanceof HolderLabla){
            HolderLabla labla = (HolderLabla) viewHolder;
            labla.tvLabla.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderLabla extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_labla)
        TextView tvLabla;

        public HolderLabla(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
