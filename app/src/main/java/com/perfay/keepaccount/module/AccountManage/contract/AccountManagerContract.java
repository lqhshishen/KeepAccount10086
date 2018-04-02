package com.perfay.keepaccount.module.AccountManage.contract;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.AccountManage.presenter.AccountManagerPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/19.
 */

public interface AccountManagerContract {

    interface view extends BaseView<AccountManagerPresenter> {

        void onGetAccount(List<Account> data);

        void showPop(int pos);

        void addPop();

        void popClick();

    }

    interface presenter {

        void getAccount();

        void delAccount(String name);

        void addAccount(String name);

    }
}
