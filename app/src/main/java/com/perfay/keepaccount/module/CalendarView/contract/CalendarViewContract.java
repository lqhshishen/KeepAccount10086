package com.perfay.keepaccount.module.CalendarView.contract;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.CalendarView.presenter.CalendarViewPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/12.
 */

public interface CalendarViewContract {
    interface view extends BaseView<CalendarViewPresenter> {
        void onGetData(List<KeepAccountDatabase> data);
    }

    interface presenter {
        void getDataByTime(String time);
    }

}
