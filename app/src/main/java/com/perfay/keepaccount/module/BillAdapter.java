package com.perfay.keepaccount.module;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.R;

import java.util.List;

/**
 * Created by liqihao on 2018/3/27.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<KeepAccountDatabase> data;

    private Context context;

    public BillAdapter(List<KeepAccountDatabase> data,Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.billadapter_item,null);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (data.get(position).getMoney() < 0) {
            holder.money.setText(String.valueOf(data.get(position).getMoney()));
            holder.money.setTextColor(context.getResources().getColor(R.color.colorDown));
        }

        else {
            holder.money.setTextColor(context.getResources().getColor(R.color.colorUp));
            holder.money.setText("+" + String.valueOf(data.get(position).getMoney()));
        }
        holder.name.setText(data.get(position).getUseType().getName());
        Glide.with(context)
                .load(data.get(position).getUseType().getImg())
                .into(holder.img);
        holder.timeAndAccount.setText(data.get(position).getAccount().getName() + "  " + data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView timeAndAccount;
        ImageView img;
        TextView money;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.billadapter_name);
            timeAndAccount = itemView.findViewById(R.id.billadapter_account_time);
            img = itemView.findViewById(R.id.billadapter_img);
            money = itemView.findViewById(R.id.billadapter_money);
        }
    }
}
