package com.perfay.keepaccount.module.CalendarView.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.BillAdapter;
import com.perfay.keepaccount.module.CalendarView.contract.CalendarViewContract;
import com.perfay.keepaccount.module.CalendarView.presenter.CalendarViewPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarViewActivity extends BaseActivity<CalendarViewPresenter> implements CalendarViewContract.view {

    @BindView(R.id.calendar_view)
    MaterialCalendarView calendarView;


    CalendarDay currentDate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    List<KeepAccountDatabase> mData = new ArrayList<>();
    BillAdapter adapter;
    int year, month, day;
    @BindView(R.id.caldendar_recycle)
    RecyclerView caldendarRecycle;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar, true, "日历视图");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_back);


        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        calendarView.setSelectedDate(new Date());

        adapter = new BillAdapter(mData, this);
        caldendarRecycle.setLayoutManager(new LinearLayoutManager(this));
        caldendarRecycle.setAdapter(adapter);

        String time = String.valueOf(cal.get(Calendar.YEAR)) + ":" + String.valueOf(cal.get(Calendar.MONTH) + 1) + ":" + String.valueOf(cal.get(Calendar.DATE));
        presenter.getDataByTime(time);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull final CalendarDay date, boolean selected) {
                currentDate = date;
                Log.e("date", currentDate.toString());
                CalendarViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(CalendarViewActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
                        getTime(date);
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getData() {


    }


    @Override
    public int getLayout() {
        return R.layout.activity_calendar_view;
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
    public void setPresenter(CalendarViewPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new CalendarViewPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    public void getTime(CalendarDay date) {
        if (date != null) {
            int year = date.getYear();
            int month = (date.getMonth() + 1);
            Log.e("testMon", String.valueOf(month) + "dsadadas");
            int day = date.getDay();
            String time = String.valueOf(year) + ":" + String.valueOf(month) + ":" + String.valueOf(day);
            Log.e("getTimeTest",time);
            presenter.getDataByTime(time);
        }
    }

    @Override
    public void onGetData(List<KeepAccountDatabase> data) {
        mData.clear();
        mData.addAll(data);
        adapter.notifyDataSetChanged();
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
