package com.perfay.keepaccount.module.Setting.presenter;

import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.module.Setting.contract.SettingContract;
import com.perfay.keepaccount.module.Setting.ui.SettingActivity;

/**
 * Created by liqihao on 2018/3/12.
 */

public class SettingPresenter extends BasePresenter<SettingActivity> implements SettingContract.presenter {

    KeepAccountDao dao = new KeepAccountDao(App.AppContext);
    @Override
    public void deleteAll() {
        dao.deleteAllData();
    }
}
