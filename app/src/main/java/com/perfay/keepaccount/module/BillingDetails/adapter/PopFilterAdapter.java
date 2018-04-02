package com.perfay.keepaccount.module.BillingDetails.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/21.
 */

public class PopFilterAdapter extends RecyclerView.Adapter<PopFilterAdapter.ViewHolder> {

    private List<UseType>data;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public PopFilterAdapter(List<UseType>data,Context context) {
        this.data = data;
        this.context = context;
        for (int i = 0;i < 100;i++)
            isClicks.add(false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_filter_item,null);
        return new ViewHolder(view);
    }
    private List<Boolean> isClicks = new ArrayList<>();
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (isClicks.get(holder.getAdapterPosition())) {
            Glide.with(context)
                    .load(data.get(position).getImgs())
                    .into(holder.img);
        }else {
            Glide.with(context)
                    .load(data.get(position).getImgn())
                    .into(holder.img);
        }
        holder.name.setText(data.get(position).getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView,holder.getAdapterPosition());
                for (int i = 0; i < isClicks.size(); i++)
                    isClicks.set(i,false);
                isClicks.set(holder.getAdapterPosition(),true);
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
        TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pop_filter_itemImg);
            name = itemView.findViewById(R.id.pop_filter_itemName);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view,int pos);
    }
}
