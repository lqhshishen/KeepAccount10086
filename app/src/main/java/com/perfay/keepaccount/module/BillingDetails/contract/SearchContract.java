package com.perfay.keepaccount.module.BillingDetails.contract;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.BillingDetails.presenter.SearchPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/12.
 */

public interface SearchContract {
    interface view extends BaseView<SearchPresenter> {
        void onSearch(List<KeepAccountDatabase>data);
    }
    interface presenter {
        void search(String word);
    }
}
