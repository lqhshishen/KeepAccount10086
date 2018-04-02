package com.perfay.keepaccount.module.BillingDetails.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.BillAdapter;
import com.perfay.keepaccount.module.BillingDetails.contract.SearchContract;
import com.perfay.keepaccount.module.BillingDetails.presenter.SearchPresenter;
import com.perfay.keepaccount.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.view {

    @BindView(R.id.searchActivity_edt)
    EditText searchActivityEdt;
    @BindView(R.id.search_cancel)
    ImageView searchCancel;
    @BindView(R.id.searchActivity_sure)
    TextView searchActivitySure;
    @BindView(R.id.searchActivity_recycle)
    RecyclerView searchActivityRecycle;
    @BindView(R.id.searchNoData)
    TextView searchNoData;


    BillAdapter adapter;
    List<KeepAccountDatabase> data = new ArrayList<>();



    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void getData() {
        adapter = new BillAdapter(data,this);
        searchActivityRecycle.setLayoutManager(new LinearLayoutManager(this));
        searchActivityRecycle.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(SearchPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new SearchPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @OnClick({R.id.search_cancel, R.id.searchActivity_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_cancel:
                finish();
                break;
            case R.id.searchActivity_sure:
                InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                if (TextUtils.isEmpty(searchActivityEdt.getText().toString()))
                    ToastUtils.showShort(this,"请输入需要搜索的内容");
                else presenter.search(searchActivityEdt.getText().toString());
                break;
        }
    }

    @Override
    public void onSearch(List<KeepAccountDatabase> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
        if (data.size() == 0)
            searchNoData.setVisibility(View.VISIBLE);
        else searchNoData.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
