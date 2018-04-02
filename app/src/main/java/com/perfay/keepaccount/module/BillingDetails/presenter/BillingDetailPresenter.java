package com.perfay.keepaccount.module.BillingDetails.presenter;

import android.support.annotation.StringDef;
import android.util.Log;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.Database.dao.AccountDao;
import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.Database.dao.UseTypeDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.BillingDetails.contract.BillingDetailContract;
import com.perfay.keepaccount.module.BillingDetails.ui.BillingDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by liqihao on 2018/3/12.
 */

public class BillingDetailPresenter extends BasePresenter<BillingDetailActivity> implements BillingDetailContract.presenter {
    private UseTypeDao useTypeDao = new UseTypeDao(App.AppContext);

    private AccountDao accountDao = new AccountDao(App.AppContext);

    private KeepAccountDao dao = new KeepAccountDao(App.AppContext);

    @Override
    public void getType() {
        List<UseType>data = new ArrayList<>();
        for (int i = 0;i < dao.queryAllInf().size();i++)
            data.add(dao.queryAllInf().get(i).getUseType());
        baseview.onGetType(data);

    }



    @Override
    public void getAccount() {
        baseview.data = accountDao.datas();
    }

    private List<KeepAccountDatabase> datas = new ArrayList<>();
    private List<KeepAccountDatabase> new1Data = new ArrayList<>();
    private List<KeepAccountDatabase> new2Data = new ArrayList<>();
    private List<KeepAccountDatabase> new3Data = new ArrayList<>();




    @Override
    public void search(UseType useType, Account account, int low, int high) {
        List<String>stringData = new ArrayList<>();
        datas = dao.queryAllInf();
        int x = 0;
        new1Data.clear();
        new2Data.clear();
        new3Data.clear();
//        if (useType == null) {
//            new1Data.addAll(datas);
//        }
//        if (account == null) {
//            new2Data.addAll(new1Data);
//        }
        for (int i = 0;i < datas.size();i++) {
            if (useType != null && Objects.equals(datas.get(i).getUseType().getName(), useType.getName()))
                new1Data.add(datas.get(i));
            else if (useType == null)
                new1Data.addAll(datas);
        }
        for (int i = 0; i <new1Data.size();i ++) {
            if (account != null && new1Data.get(i).getAccount().getId() == account.getId())
                new2Data.add(new1Data.get(i));
            else if (account == null)
                new2Data.addAll(new1Data);
        }
        for (int i = 0; i < new2Data.size();i ++) {
            if (new2Data.get(i).getMoney() < 0)
                x = new2Data.get(i).getMoney() * -1;
            else x = new2Data.get(i).getMoney();
            if (x > low && x < high)
                new3Data.add(new2Data.get(i));
        }
        baseview.onSearch(new3Data);


        if (useType != null)
            stringData.add(useType.getName());
        if (account != null)
            stringData.add(account.getName());
        stringData.add(String.valueOf(low) + "-" + String.valueOf(high));
        baseview.selectedSearchItem(stringData);
    }

}
