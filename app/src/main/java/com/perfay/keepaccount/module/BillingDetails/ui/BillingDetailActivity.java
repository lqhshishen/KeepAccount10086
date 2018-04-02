package com.perfay.keepaccount.module.BillingDetails.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseActivity;
import com.perfay.keepaccount.module.BillAdapter;
import com.perfay.keepaccount.module.BillingDetails.adapter.PopFilterAdapter;
import com.perfay.keepaccount.module.BillingDetails.adapter.ShowFilterItemAdapter;
import com.perfay.keepaccount.module.BillingDetails.contract.BillingDetailContract;
import com.perfay.keepaccount.module.BillingDetails.presenter.BillingDetailPresenter;
import com.perfay.keepaccount.module.Keepaccount.adapter.GetAccountAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillingDetailActivity extends BaseActivity<BillingDetailPresenter> implements BillingDetailContract.view {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.billiDetail_recycle)
    RecyclerView billiDetailRecycle;
    @BindView(R.id.billi_filterClear)
    TextView billiFilterClear;
    @BindView(R.id.billi_filterItem)
    RecyclerView billiFilterItem;
    @BindView(R.id.billi_filter)
    LinearLayout billiFilter;
    @BindView(R.id.billi_detail_nodata)
    TextView billiNoData;
//    @BindView(R.id.billi_filterFirst)
//    LinearLayout billiFilterFirst;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initToolBar(toolbar, true, "账单");
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.navbar_back);
        billiFilter.setVisibility(View.GONE);
        billiNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.billimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.billi_filter:
                showFilterPop();
                break;
            case R.id.billi_search:
                startActivity(new Intent(BillingDetailActivity.this, SearchActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    ShowFilterItemAdapter showFilterItemAdapter;
    @Override
    public void getData() {
        mainAdapter = new BillAdapter(mainData,this);
        billiDetailRecycle.setLayoutManager(new LinearLayoutManager(this));
        billiDetailRecycle.setAdapter(mainAdapter);
        showFilterItemAdapter = new ShowFilterItemAdapter(stringList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        billiFilterItem.setLayoutManager(manager);
        billiFilterItem.setAdapter(showFilterItemAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_billing_detail;
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
    public void setPresenter(BillingDetailPresenter presenter) {
        if (this.presenter == null)
            this.presenter = new BillingDetailPresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    /**
     * pop: 筛选页面popupwindow;
     * finish:右上角结束按钮
     * clear:确定按钮
     */
    PopupWindow pop;
    ImageView finish;
    TextView clear;
    RecyclerView filterItem;
    RecyclerView accountRecycle;
    GetAccountAdapter accountAdapter;
    public List<Account> data = new ArrayList<>();
    TextView lowTv;
    TextView highTv;

    @Override
    public void showFilterPop() {
        View popView = getLayoutInflater().inflate(R.layout.pop_item_filter, null);
        finish = popView.findViewById(R.id.pop_filter_finish);
        clear = popView.findViewById(R.id.pop_filter_clear);
        filterItem = popView.findViewById(R.id.pop_filter_recycle);
        accountRecycle = popView.findViewById(R.id.pop_item_account);
        lowTv = popView.findViewById(R.id.pop_item_filter_lowTv);
        highTv = popView.findViewById(R.id.pop_item_filter_highTv);
        presenter.getAccount();
        accountAdapter = new GetAccountAdapter(data,this);
        accountAdapter.notifyDataSetChanged();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.HORIZONTAL);
        accountRecycle.setLayoutManager(manager);
        accountRecycle.setAdapter(accountAdapter);

        pop = new PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT
                , ActionBar.LayoutParams.MATCH_PARENT, true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
//        pop.setBackgroundDrawable();
        pop.setAnimationStyle(R.style.pop_anim);
        presenter.getType();
        pop.update();
        pop.showAtLocation(popView, Gravity.NO_GRAVITY, 0, 0);
        popClick();
    }

    UseType searchUseType;
    Account searchAccount;
    int low,high;
    @Override
    public void popClick() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(lowTv.getText().toString()))
                     low = Integer.parseInt(lowTv.getText().toString());
                else low = 0;
                if (!TextUtils.isEmpty(highTv.getText().toString()))
                     high = Integer.parseInt(highTv.getText().toString());
                else high = 999999999;
                presenter.search(searchUseType,searchAccount,low,high);
                pop.dismiss();
                billiFilter.setVisibility(View.VISIBLE);
            }
        });
        accountAdapter.setOnItemClickListener(new GetAccountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Account mAccount) {
                searchAccount = mAccount;
            }
        });
        adapter.setOnItemClickListener(new PopFilterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                searchUseType = mData.get(pos);
            }
        });
    }

    List<UseType> mData = new ArrayList<>();
    PopFilterAdapter adapter;
    @Override
    public void onGetType(List<UseType> data) {
        mData = data;
        filterItem.setLayoutManager(new GridLayoutManager(this,5));
        adapter = new PopFilterAdapter(mData,this);
        filterItem.setAdapter(adapter);
    }
    List<KeepAccountDatabase> mainData = new ArrayList<>();
    BillAdapter mainAdapter;
    @Override
    public void onSearch(List<KeepAccountDatabase> data) {
        mainData.clear();
        mainData.addAll(data);
        mainAdapter.notifyDataSetChanged();
        if (data.size() == 0)
            billiNoData.setVisibility(View.VISIBLE);
        else billiNoData.setVisibility(View.GONE);
    }

    List<String>stringList = new ArrayList<>();
    @Override
    public void selectedSearchItem(List<String> data) {
        stringList.clear();
        stringList.addAll(data);
        showFilterItemAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.billi_filterClear)
    public void onViewClicked() {
        billiFilter.setVisibility(View.GONE);
        mainData.clear();
        mainAdapter.notifyDataSetChanged();
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
