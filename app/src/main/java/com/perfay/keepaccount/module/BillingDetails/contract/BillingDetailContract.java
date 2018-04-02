package com.perfay.keepaccount.module.BillingDetails.contract;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.BillingDetails.presenter.BillingDetailPresenter;

import java.util.List;

/**
 * Created by liqihao on 2018/3/12.
 */

public interface BillingDetailContract {
    interface view extends BaseView<BillingDetailPresenter> {
        void showFilterPop();

        void popClick();

        void onGetType(List<UseType> data);

        void onSearch(List<KeepAccountDatabase> data);

        void selectedSearchItem(List<String> data);
    }

    interface presenter {

        void getType();

        void getAccount();

        void search(UseType useType, Account account,int low,int high);

    }

}
