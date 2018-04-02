package com.perfay.keepaccount.module.CalendarView.presenter;

import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.CalendarView.contract.CalendarViewContract;
import com.perfay.keepaccount.module.CalendarView.ui.CalendarViewActivity;

/**
 * Created by liqihao on 2018/3/12.
 */

public class CalendarViewPresenter extends BasePresenter<CalendarViewActivity> implements CalendarViewContract.presenter {

    private KeepAccountDao dao = new KeepAccountDao(App.AppContext);

    @Override
    public void getDataByTime(String time) {

        baseview.onGetData(dao.queryByTime(time));
    }
}
