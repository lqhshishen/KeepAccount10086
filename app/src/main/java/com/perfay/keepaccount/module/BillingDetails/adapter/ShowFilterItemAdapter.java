package com.perfay.keepaccount.module.BillingDetails.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perfay.keepaccount.R;

import java.util.List;

/**
 * Created by liqihao on 2018/3/30.
 */

public class ShowFilterItemAdapter extends RecyclerView.Adapter<ShowFilterItemAdapter.ViewHolder> {


    private List<String>data;

    private Context context;

    public ShowFilterItemAdapter(List<String>data,Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.getaccount_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.view.setBackground(context.getDrawable(R.drawable.bluebutton));
        }
        holder.item.setTextColor(context.getResources().getColor(R.color.topMoney));
        holder.item.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout view;
        TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.getAccount_name);
            view = itemView.findViewById(R.id.getAccount_item);
        }
    }
}
