package com.perfay.keepaccount.module.Keepaccount.presenter;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.Database.dao.AccountDao;
import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.Database.dao.UseTypeDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.Keepaccount.contract.ExpenditureContract;
import com.perfay.keepaccount.module.Keepaccount.ui.ExpenditureFragment;

import java.util.List;

/**
 * Created by liqihao on 2018/3/12.
 */

public class ExpenditurePresenter extends BasePresenter<ExpenditureFragment> implements ExpenditureContract.presenter {

    private UseTypeDao useTypeDao = new UseTypeDao(App.AppContext);

    private AccountDao accountDao = new AccountDao(App.AppContext);

    private KeepAccountDao keepAccountDao = new KeepAccountDao(App.AppContext);

    @Override
    public void getAccount() {
        baseview.onGetAccount(accountDao.datas());
    }

    @Override
    public List<UseType> getClassify() {
        return useTypeDao.queryAllType();
    }

    @Override
    public void addInf(String time, UseType useType, int money, Account account, String beizhu) {
        if (beizhu == null)
            keepAccountDao.addInf(new KeepAccountDatabase(time,useType,money,account));
        else
            keepAccountDao.addInf(new KeepAccountDatabase(time,useType,money,account,beizhu));
    }

    @Override
    public void addInf(KeepAccountDatabase data) {
        keepAccountDao.addInf(data);
    }
}
