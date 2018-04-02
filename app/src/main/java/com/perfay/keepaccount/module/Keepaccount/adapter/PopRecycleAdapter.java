package com.perfay.keepaccount.module.Keepaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/23.
 */

public class PopRecycleAdapter extends RecyclerView.Adapter<PopRecycleAdapter.ViewHolder> {

    private List<UseType> data;

    private Context context;

    private List<Boolean> isClick = new ArrayList<>();

    private OnItemClickListener listener;

    public PopRecycleAdapter(List<UseType> data,Context context) {
        this.data = data;
        this.context = context;
        for (int i = 0; i < data.size(); i++) {
            isClick.add(false);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view ,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
    @Override
    public PopRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pop_img_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PopRecycleAdapter.ViewHolder holder, final int position) {
        holder.name.setText(data.get(holder.getAdapterPosition()).getName());
        if (isClick.get(position))
            Glide.with(context)
            .load(data.get(holder.getAdapterPosition()).getImgs())
            .into(holder.img);
        else Glide.with(context)
             .load(data.get(holder.getAdapterPosition()).getImgn())
             .into(holder.img);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.itemView,holder.getAdapterPosition());
                for (int i = 0; i < isClick.size(); i++) {
                    isClick.set(i,false);
                }
                isClick.set(holder.getAdapterPosition(),true);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        LinearLayout linearLayout;

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pop_img_img);
            linearLayout = itemView.findViewById(R.id.pop_img_item);
            name = itemView.findViewById(R.id.pop_img_name);
        }
    }
}
