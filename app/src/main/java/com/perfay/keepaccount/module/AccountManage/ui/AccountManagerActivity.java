package com.perfay.keepaccount.module.AccountManage.ui;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.AccountManage.adapter.AccountManagerAdapter;
import com.perfay.keepaccount.module.AccountManage.contract.AccountManagerContract;
import com.perfay.keepaccount.module.AccountManage.presenter.AccountManagerPresenter;
import com.perfay.keepaccount.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountManagerActivity extends BaseActivity<AccountManagerPresenter> implements AccountManagerContract.view {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.account_managerRecycle)
    RecyclerView accountManagerRecycle;
    @BindView(R.id.accountManage_addNew)
    Button accountManageAddNew;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar, true, "账号管理");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_back);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finish_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.finish:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getData() {
        presenter.getAccount();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_account_manager;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(AccountManagerPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new AccountManagerPresenter();
    }

    @Override
    public void onShowNoMore() {

    }


    @OnClick(R.id.accountManage_addNew)
    public void onViewClicked() {
        addPop();
    }

    List<Account> mData = new ArrayList<>();
    AccountManagerAdapter adapter;
    @Override
    public void onGetAccount(List<Account> data) {
        Log.e(TAG,"data.size = " + data.size() );
        mData.addAll(data);
        accountManagerRecycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AccountManagerAdapter(mData,this);
        accountManagerRecycle.setAdapter(adapter);
        adapter.setOnItemClickListener(new AccountManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showPop(position);
            }
        });
    }

    @Override
    public void showPop(final int pos) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("是否删除此账号")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG,String.valueOf(pos));
                        adapter.notifyDataSetChanged();
                        presenter.delAccount(mData.get(pos).getName());
                        mData.remove(pos);
                        ToastUtils.showShort(AccountManagerActivity.this,"删除成功");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialog.show();
    }


    EditText add;
    ImageView finish;
    TextView clear;
    PopupWindow pop;
    @Override
    public void addPop() {
        View popView = getLayoutInflater().inflate(R.layout.pop_add_account, null);
        finish = popView.findViewById(R.id.pop_add_finish);
        clear = popView.findViewById(R.id.pop_add_clear);
        add = popView.findViewById(R.id.pop_add_edt);
        pop = new PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT
                , ActionBar.LayoutParams.WRAP_CONTENT, true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
//        pop.setBackgroundDrawable();
        pop.setAnimationStyle(R.style.pop_anim);
        pop.update();
        pop.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
        popClick();
    }

    @Override
    public void popClick() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add.getText() == null || !Objects.equals(add.getText().toString(), " ")){
                    presenter.addAccount(add.getText().toString());
                    pop.dismiss();
                    mData.add(new Account(add.getText().toString()));
                    adapter.notifyDataSetChanged();
                    ToastUtils.showShort(AccountManagerActivity.this,"添加成功");
                }

                else
                    ToastUtils.showShort(AccountManagerActivity.this,"输入为空");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
