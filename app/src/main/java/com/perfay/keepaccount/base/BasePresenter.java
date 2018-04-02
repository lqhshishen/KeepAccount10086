package com.perfay.keepaccount.base;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Presenter的基类
 * Created by wangwenzhang on 2017/11/9.
 */

public class BasePresenter <V extends BaseView>{
    protected V baseview;//泛型 view  所有的view都要继承BaseView
    protected CompositeDisposable mCompositeSubscription;//rxjava 存放观察者

    protected void addSubscription(Disposable disposable){//添加观察者
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }
    public void unSubscription(){//解除订阅
        if (mCompositeSubscription != null){
            mCompositeSubscription.dispose();
        }
    }
    public void attachView(V view){//绑定view
        this.baseview=view;
    }

    public void detachView(){//销毁view
        this.baseview=null;
        unSubscription();
    }
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
