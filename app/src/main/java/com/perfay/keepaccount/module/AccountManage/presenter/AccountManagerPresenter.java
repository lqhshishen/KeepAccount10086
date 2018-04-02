package com.perfay.keepaccount.module.AccountManage.presenter;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.dao.AccountDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.AccountManage.contract.AccountManagerContract;
import com.perfay.keepaccount.module.AccountManage.ui.AccountManagerActivity;

/**
 * Created by liqihao on 2018/3/19.
 */

public class AccountManagerPresenter extends BasePresenter<AccountManagerActivity> implements AccountManagerContract.presenter {

    private AccountDao accountDao = new AccountDao(App.AppContext);



    @Override
    public void getAccount() {
        baseview.onGetAccount(accountDao.datas());

    }

    @Override
    public void delAccount(String name) {
        accountDao.delAccount(name);
    }

    @Override
    public void addAccount(String name) {
        accountDao.addAccount(new Account(name));
    }
}
