package com.perfay.keepaccount.module.Keepaccount.contract;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.Keepaccount.presenter.KeepAccountPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/13.
 */

public interface KeepAccountContract {
    interface view extends BaseView<KeepAccountPresenter> {
        void judge();

        void getAccount(List<Account> data);
    }

    interface presenter {

        void getAccount();


    }
}
