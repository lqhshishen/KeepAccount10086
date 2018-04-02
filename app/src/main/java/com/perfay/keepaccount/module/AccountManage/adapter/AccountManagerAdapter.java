package com.perfay.keepaccount.module.AccountManage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.R;

import java.util.List;

/**
 * Created by liqihao on 2018/3/21.
 */

public class AccountManagerAdapter extends RecyclerView.Adapter<AccountManagerAdapter.ViewHolder> {

    private List<Account> data;

    private Context context;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.itemClickListener = onItemClickListener;
    }

    public AccountManagerAdapter (List<Account> data,Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.account_manager_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.itemView,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView menu;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.manager_item);
            menu = itemView.findViewById(R.id.manager_showMenu);
            item = itemView.findViewById(R.id.account_manager_item);
        }
    }
}
