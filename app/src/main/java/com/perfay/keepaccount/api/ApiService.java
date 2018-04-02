package com.perfay.keepaccount.api;

import com.perfay.keepaccount.utils.RetrofitFactory;

import retrofit2.Retrofit;


/**
 * 该类用于调用网络请求并且返回相应的数据类
 * Created by wangwenzhang on 2017/11/9.
 */

public class ApiService {
    private static Api model;
    public static Api getBaseModle(){
        if (model==null){
            Retrofit retrofit = RetrofitFactory.getRetrofit();
            model = retrofit.create(Api.class);
        }
        return model;
    }
}
