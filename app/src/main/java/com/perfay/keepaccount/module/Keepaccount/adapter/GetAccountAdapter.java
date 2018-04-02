package com.perfay.keepaccount.module.Keepaccount.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/22.
 */

public class GetAccountAdapter extends RecyclerView.Adapter<GetAccountAdapter.ViewHolder> {


    private List<Account> data;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public GetAccountAdapter(List<Account> data,Context context) {
        this.data = data;
        this.context = context;
        for (int i = 0; i < data.size(); i++) {
            isClicks.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.getaccount_item,null);
        return new ViewHolder(view);
    }

    /**
     * 设置点击时的颜色
     */

    private List<Boolean> isClicks = new ArrayList<>();
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isClicks.get(position)) {
                holder.item.setBackground(context.getDrawable(R.drawable.bluebutton));
                holder.name.setTextColor(context.getResources().getColor(R.color.topMoney));
            } else {
                holder.item.setBackground(context.getDrawable(R.drawable.greybutton));
                holder.name.setTextColor(context.getResources().getColor(R.color.black));
            }
        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView,data.get(holder.getAdapterPosition()));
                for (int i = 0; i < isClicks.size(); i++) {
                    isClicks.set(i,false);
                }
                isClicks.set(holder.getAdapterPosition(),true);
                notifyDataSetChanged();
            }
        });
        holder.name.setText(data.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.getAccount_item);
            name = itemView.findViewById(R.id.getAccount_name);

        }
    }



    public interface OnItemClickListener{
        void onItemClick(View view,Account mAccount);
    }
}
