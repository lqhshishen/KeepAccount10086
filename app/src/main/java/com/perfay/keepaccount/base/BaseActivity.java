package com.perfay.keepaccount.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.perfay.keepaccount.R;

/**
 * Activity的基类
 * Created by wangwenzhang on 2017/11/9.
 */

public abstract class BaseActivity <P extends BasePresenter>extends AppCompatActivity implements BaseView<P> {
    protected P presenter;


    protected String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setPresenter(presenter);
        if (presenter != null) {
            this.presenter.attachView(this);
        } else {
            Log.e("test","presenter is empty");
        }
        initView();
        getData();
    }

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 获取数据
     */
    public abstract void getData();

    /**
     * 设置布局文件id
     * @return
     */
    public abstract int getLayout();

    /**
     * 布局销毁 调用presenter置空view，防止内存溢出
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnable, String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.topMoney));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnable);
    }






}
