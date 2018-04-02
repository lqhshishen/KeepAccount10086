package com.perfay.keepaccount.module;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.base.BaseView;

import java.util.List;

/**
 * Created by liqihao on 2018/3/9.
 */

public interface HomeContract {
    interface View extends BaseView<HomePresenter> {

        void onGetInf(List<KeepAccountDatabase> data);

        void onGetAllMsg(int inCome,int out,int all,String time);

        void getJson();

    }

    interface presenter {

        void getAllType();


        //把本地图标储存进数据库
        void saveType();

        void getData();

        void getAllMsg();

    }


}
