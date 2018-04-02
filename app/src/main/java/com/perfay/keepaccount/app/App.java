package com.perfay.keepaccount.app;

import android.app.Application;
import android.content.Context;

import com.perfay.keepaccount.utils.LogUtils;
import com.perfay.keepaccount.utils.SharedPreferencesUtil;

/**
 * APP初始化类
 * Created by wangwenzhang on 2017/11/9.
 */

public class App extends Application {
    public static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isShow=true;
        AppContext = getApplicationContext();
        initPrefs();
    }
    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }
}
