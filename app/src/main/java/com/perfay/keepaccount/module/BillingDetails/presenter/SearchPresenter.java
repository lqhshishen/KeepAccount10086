package com.perfay.keepaccount.module.BillingDetails.presenter;

import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.BillingDetails.contract.SearchContract;
import com.perfay.keepaccount.module.BillingDetails.ui.SearchActivity;

/**
 * Created by liqihao on 2018/3/12.
 */

public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.presenter {
    KeepAccountDao dao = new KeepAccountDao(App.AppContext);

    @Override
    public void search(String word) {
        baseview.onSearch(dao.searchByWord(word));
    }
}
