package com.perfay.keepaccount;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.BillAdapter;
import com.perfay.keepaccount.module.BillingDetails.ui.BillingDetailActivity;
import com.perfay.keepaccount.module.BundleBean;
import com.perfay.keepaccount.module.CalendarView.ui.CalendarViewActivity;
import com.perfay.keepaccount.module.HomeContract;
import com.perfay.keepaccount.module.HomePresenter;
import com.perfay.keepaccount.module.Keepaccount.ui.KeepAccountActivity;
import com.perfay.keepaccount.module.Setting.ui.SettingActivity;
import com.perfay.keepaccount.utils.SharedPreferencesUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout AppBarLayout01;
    @BindView(R.id.countingTime_tv)
    TextView countingTimeTv;
    @BindView(R.id.money_tv)
    TextView moneyTv;
    @BindView(R.id.income_tv)
    TextView incomeTv;
    @BindView(R.id.expenditure_tv)
    TextView expenditureTv;
    @BindView(R.id.detail_recycle)
    RecyclerView detailRecycle;
    @BindView(R.id.addAccount_floBtn)
    FloatingActionButton addAccountFloBtn;
    @BindView(R.id.main_nav)
    NavigationView mainNav;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;

    View headView;

    TextView useCount;
    TextView dayCount;

    @Override
    public void initView() {
//       xx
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initToolBar(toolbar, true, "开心记账");
//        toolbar.setBackgroundResource(R.drawable.sidenav_bg);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_more);
        headView = mainNav.inflateHeaderView(R.layout.nav_head);
        useCount = headView.findViewById(R.id.nav_allNumber);
        dayCount = headView.findViewById(R.id.nav_allDay);
        useCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("usecount", 0)));
        dayCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("daycount", 0)));
        mainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    //账户明细
                    case R.id.navMenu_billiDetail:
                        startActivity(new Intent(MainActivity.this, BillingDetailActivity.class));
                        break;
                    //日历视图
                    case R.id.navMenu_calendarView:
                        startActivity(new Intent(MainActivity.this, CalendarViewActivity.class));
                        break;
                    /**
                     * 暂时没做
                     */
//                    //图标统计
//                    case R.id.navMenu_chartStatistics:
//                        Intent intent = new Intent(MainActivity.this, KeepAccountActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("type", new BundleBean("图表统计", false, 2));
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        break;
//                    //意见反馈
//                    case R.id.navMenu_info:
//                        break;
                    //设置
                    case R.id.navMenu_setting:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainDrawer.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void getData() {
        getJson();
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5abdf4568f4a9d7689000044");
        if (SharedPreferencesUtil.getInstance().getBoolean("3rd", true)) {
            presenter.saveType();
            SharedPreferencesUtil.getInstance().putBoolean("3rd", false);
        }
        if (!SharedPreferencesUtil.getInstance().getBoolean("3rd"))
            presenter.getAllType();
        presenter.getAllType();
        presenter.getData();
        presenter.getAllMsg();

        TelephonyManager TelephonyMgr = (TelephonyManager) this.getApplicationContext().getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        String szImei = TelephonyMgr.getDeviceId();
        MobclickAgent.onProfileSignIn(szImei);

    }



    @Override
    public int getLayout() {
        return R.layout.activity_main;
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
    public void setPresenter(HomePresenter presenter) {
        if (this.presenter == null)
            this.presenter = new HomePresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDelete(String event) {
        if (event.equals("delete"))
            this.data.clear();
        adapter.notifyDataSetChanged();
        presenter.getAllMsg();
        useCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("usecount")));
        dayCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("daycount")));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(KeepAccountDatabase data) {
        this.data.add(data);
        adapter.notifyDataSetChanged();
//        if (data.getMoney() > 0)
//            minCome += data.getMoney();
//        else mout += data.getMoney();
//        mall = minCome + mout;
//        moneyTv.setText("¥" + String.valueOf(mall));
//        incomeTv.setText(String.valueOf(minCome));
//        expenditureTv.setText(String.valueOf(mout * -1));
        presenter.getAllMsg();
        useCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("usecount")));
        dayCount.setText(String.valueOf(SharedPreferencesUtil.getInstance().getInt("daycount")));
    }

    @OnClick(R.id.addAccount_floBtn)
    public void onViewClicked() {
        Intent intent = new Intent(this, KeepAccountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", new BundleBean("记账", true, 0));
        intent.putExtras(bundle);
        startActivity(intent);
    }


    List<KeepAccountDatabase>data = new ArrayList<>();
    BillAdapter adapter;
    @Override
    public void onGetInf(List<KeepAccountDatabase> mData) {
        data.addAll(mData);
        adapter = new BillAdapter(data,this);
        detailRecycle.setLayoutManager(new LinearLayoutManager(this));
        detailRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     *
     * @param inCome 收入
     * @param out 支出
     * @param all 全部
     * @param time 时间
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onGetAllMsg(int inCome, int out, int all, String time) {
        incomeTv.setText(String.valueOf(inCome));
        expenditureTv.setText(String.valueOf(out * -1));
        moneyTv.setText("¥" + String.valueOf(all));
        countingTimeTv.setText(time);
    }

    @Override
    public void getJson() {
        Log.e("test","tttttest");
        String path = "D:\\question.json";
        StringBuilder laststr = new StringBuilder();
        File file = new File(path);
        BufferedReader reader = null;
        int line = 1;
        try {
            FileInputStream in = new FileInputStream(file);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                Log.e(TAG,"line" + line + tempString);
                laststr.append(tempString);
                line++;
            }

        } catch (IOException e) {
            Log.e("TAG",e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e(TAG,"test" + String.valueOf(file.length()));
        Log.e(TAG,file.getPath());
        Log.e(TAG, laststr.toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}