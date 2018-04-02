package com.perfay.keepaccount.module.Keepaccount.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.BundleBean;
import com.perfay.keepaccount.module.ChartStatistics.ui.ChartStatisticsExpenditureFragment;
import com.perfay.keepaccount.module.ChartStatistics.ui.ChartStatisticsIncomeFragment;
import com.perfay.keepaccount.module.Keepaccount.adapter.KeepAccountPagerAdapter;
import com.perfay.keepaccount.module.Keepaccount.contract.KeepAccountContract;
import com.perfay.keepaccount.module.Keepaccount.presenter.KeepAccountPresenter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeepAccountActivity extends BaseActivity<KeepAccountPresenter> implements KeepAccountContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.keepAccount_tab)
    TabLayout keepAccountTab;
    @BindView(R.id.keepAccount_vp)
    ViewPager keepAccountVp;

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> title = new ArrayList<>();
    BundleBean bundleBean;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) bundleBean = (BundleBean) bundle.get("type");
        if (bundleBean != null) initToolBar(toolbar, true, bundleBean.getTitle());
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_back);
        title.add("支出");
        title.add("收入");
        judge();
        KeepAccountPagerAdapter pagerAdapter = new KeepAccountPagerAdapter(getSupportFragmentManager(), fragmentList, title);
        keepAccountVp.setAdapter(pagerAdapter);
        keepAccountTab.setupWithViewPager(keepAccountVp);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (bundleBean.isHaveFinish())
//            getMenuInflater().inflate(R.menu.finish_toolbar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.finish:
                finish();
                break;
            case R.id.billi_filter:
                break;
            case R.id.billi_search:
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
        return R.layout.activity_keep_account;
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
    public void setPresenter(KeepAccountPresenter presenter) {
        if (this.presenter == null) this.presenter = new KeepAccountPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void judge() {
        fragmentList.clear();
        if (bundleBean.getType() == 0) {
            fragmentList.add(new ExpenditureFragment());
            fragmentList.add(new IncomeFragment());
        } else if (bundleBean.getType() == 2) {
            fragmentList.add(new ChartStatisticsExpenditureFragment());
            fragmentList.add(new ChartStatisticsIncomeFragment());
        }
    }


    List<Account> accountData;
    @Override
    public void getAccount(List<Account> data) {
        accountData = data;
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
