package com.perfay.keepaccount.module.Keepaccount.presenter;

import com.perfay.keepaccount.Database.dao.AccountDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.Keepaccount.contract.KeepAccountContract;
import com.perfay.keepaccount.module.Keepaccount.ui.KeepAccountActivity;

/**
 * Created by liqihao on 2018/3/13.
 */

public class KeepAccountPresenter extends BasePresenter<KeepAccountActivity> implements KeepAccountContract.presenter {

    AccountDao accountDao = new AccountDao(App.AppContext);



    @Override
    public void getAccount() {

    }
}
