package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.BeanCinemaComment;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyCinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<BeanCinemaComment.ResultBean> list;

    public MyCinemaAdapter(Context context, List<BeanCinemaComment.ResultBean> result) {
        super();
        this.context = context;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cinema_list, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        long commentTime = list.get(i).getCommentTime();
        String commentHeadPic = list.get(i).getCommentHeadPic();
        String commentUserName = list.get(i).getCommentUserName();
        String commentContent = list.get(i).getCommentContent();
        int isGreat = list.get(i).getIsGreat();
        if (viewHolder instanceof ListHolder) {
            ListHolder holder = (ListHolder) viewHolder;
            holder.tvCinemaListName.setText(commentUserName);
            holder.tvCinemaListTitey.setText(commentContent);
            Glide.with(context).load(commentHeadPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.ivCinemaListName);
            if (isGreat==1){
                holder.checkCinemaList.setChecked(true);
            } else {
                holder.checkCinemaList.setChecked(false);
            }
            String format = "MM-dd HH:mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String time = dateFormat.format(commentTime);
            holder.tvCinemaListTime.setText(time);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cinema_list_name)
        ImageView ivCinemaListName;
        @BindView(R.id.tv_cinema_list_name)
        TextView tvCinemaListName;
        @BindView(R.id.tv_cinema_list_time)
        TextView tvCinemaListTime;
        @BindView(R.id.tv_cinema_list_titey)
        TextView tvCinemaListTitey;
        @BindView(R.id.check_cinema_list)
        CheckBox checkCinemaList;
        public ListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
