package com.perfay.keepaccount.module.Keepaccount.contract;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.Keepaccount.presenter.IncomePresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/12.
 */

public interface IncomeContract{
    interface view extends BaseView<IncomePresenter> {

        void onGetAccount(List<Account> data);

        void showClassifyPop();

        void judge();

        void getTime();

    }
    interface presenter {
        void getAccount();

        List<UseType> getClassify();

        void addInf(String time, UseType useType,int money,Account account,String beizhu);


        void addInf(KeepAccountDatabase data);


    }
}
