package com.perfay.keepaccount.module.Setting.contract;

import com.perfay.keepaccount.base.BaseView;
import com.perfay.keepaccount.module.Setting.presenter.SettingPresenter;

/**
 * Created by liqihao on 2018/3/12.
 */

public interface SettingContract {
    interface view extends BaseView<SettingPresenter> {

    }

    interface presenter {
        void deleteAll();
    }
}
