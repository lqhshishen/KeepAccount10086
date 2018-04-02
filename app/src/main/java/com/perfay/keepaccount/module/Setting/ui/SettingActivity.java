package com.perfay.keepaccount.module.Setting.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.AccountManage.ui.AccountManagerActivity;
import com.perfay.keepaccount.module.Setting.contract.SettingContract;
import com.perfay.keepaccount.module.Setting.presenter.SettingPresenter;
import com.perfay.keepaccount.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.accountManage)
    LinearLayout accountManage;
//    @BindView(R.id.inviteFriend)
//    LinearLayout inviteFriend;
//    @BindView(R.id.setting_feedback)
//    LinearLayout settingFeedback;
    @BindView(R.id.deleteAlldata)
    LinearLayout deleteAlldata;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar, true, "设置");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
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
    public void setPresenter(SettingPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new SettingPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @OnClick({R.id.accountManage, R.id.deleteAlldata})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //分类管理
            case R.id.deleteAlldata:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确定删除所有数据吗?")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteAll();
                                ToastUtils.showShort(SettingActivity.this, "删除成功");
                                EventBus.getDefault().post("delete");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialog.show();
                break;
            //账号管理
            case R.id.accountManage:
                startActivity(new Intent(SettingActivity.this, AccountManagerActivity.class));
                break;

//            //分享
//            case R.id.inviteFriend:
//                break;

//            //反馈
//            case R.id.setting_feedback:
//                break;

        }
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



    @OnClick(R.id.deleteAlldata)
    public void onViewClicked() {
    }
}
