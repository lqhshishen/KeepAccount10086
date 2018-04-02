package com.perfay.keepaccount.module.Keepaccount.ui;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseFragment;
import com.perfay.keepaccount.module.Keepaccount.adapter.GetAccountAdapter;
import com.perfay.keepaccount.module.Keepaccount.adapter.PopRecycleAdapter;
import com.perfay.keepaccount.module.Keepaccount.contract.IncomeContract;
import com.perfay.keepaccount.module.Keepaccount.presenter.IncomePresenter;
import com.perfay.keepaccount.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2018/3/12.
 */

public class IncomeFragment extends BaseFragment<IncomePresenter> implements IncomeContract.view {


    @BindView(R.id.keepAccount_classify)
    TextView keepAccountClassify;
    @BindView(R.id.keepAccount_beizhu)
    EditText keepAccountBeizhu;
    @BindView(R.id.keepAccount_recycle)
    RecyclerView keepAccountRecycle;
    @BindView(R.id.keepAccount_money)
    EditText keepAccountMoney;

    String time;
    UseType useType;
    int money;
    Account account;
    String beizhu;
    @BindView(R.id.keepaccount_clear)
    Button keepaccountClear;
    Unbinder unbinder;


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
    public void setPresenter(IncomePresenter presenter) {
        if (this.presenter == null)
            this.presenter = new IncomePresenter();
    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void initView() {
//        EventBus.getDefault().register(getContext());
    }

    @Override
    public void getData() {
        presenter.getAccount();
        useTypeData = presenter.getClassify();
        useType = useTypeData.get(0);
        keepAccountClassify.setText(useType.getName());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_keepaccount;
    }


    List<Account> mData = new ArrayList<>();


    GetAccountAdapter adapter;

    @Override
    public void onGetAccount(final List<Account> data) {
        mData.addAll(data);
        adapter = new GetAccountAdapter(mData, getContext());
        adapter.setOnItemClickListener(new GetAccountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Account mAccount) {
                account = mAccount;
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        keepAccountRecycle.setLayoutManager(manager);
        keepAccountRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    PopupWindow pop;
    RecyclerView popRecyclerView;
    List<UseType> useTypeData;
    @Override
    public void showClassifyPop() {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.pop_classify_recycle, null);
        popRecyclerView = view.findViewById(R.id.pop_classify_recycle);
        PopRecycleAdapter adapter = new PopRecycleAdapter(presenter.getClassify(), getContext());
        popRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        popRecyclerView.setAdapter(adapter);
        pop = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT
                , ActionBar.LayoutParams.WRAP_CONTENT, true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable(0));
        pop.setAnimationStyle(R.style.pop_anim);
        pop.update();
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        adapter.setOnItemClickListener(new PopRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
//                Log.e(TAG, String.valueOf(pos));
//                Log.e(TAG, presenter.getClassify().get(pos).getName());
                useType = useTypeData.get(pos);
//                Log.e(TAG,useType.toString());
                keepAccountClassify.setText(useType.getName());
                pop.dismiss();
//                ToastUtils.showShort(getContext(),"你点击了" + presenter.getClassify().get(pos).getName());
            }
        });

    }
    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String min;
    String sec;

    @Override
    public void judge() {
        getTime();
        if (TextUtils.isEmpty(keepAccountMoney.getText())) {
            ToastUtils.showShort(getContext(),"请输入金额");
            return;
        }
        else money = Integer.parseInt(keepAccountMoney.getText().toString());
        if (account == null) {
            ToastUtils.showShort(getContext(),"请设置账户");
            return;
        }
//        if (useType == null) {
//
//        }
        presenter.addInf(time,useType,money,account,beizhu);
        ToastUtils.showShort(getContext(),"录入成功");
        EventBus.getDefault().post(new KeepAccountDatabase(time,useType,money,account));
        getActivity().finish();
    }

    @Override
    public void getTime() {
        long mTime = System.currentTimeMillis();
        cal = Calendar.getInstance();
        cal.setTimeInMillis(mTime);
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        day = String.valueOf(cal.get(Calendar.DATE));
        if (cal.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(cal.get(Calendar.HOUR));
        else
            hour = String.valueOf(cal.get(Calendar.HOUR) + 12);
        min = String.valueOf(cal.get(Calendar.MINUTE));
        sec = String.valueOf(cal.get(Calendar.SECOND));

        time = year + ":" + month + ":" + day + ":" + hour + ":" + min;

    }


    @Override
    public void onDestroy() {
//        EventBus.getDefault().unregister(getContext());
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.keepAccount_classify, R.id.keepaccount_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.keepAccount_classify:
                showClassifyPop();
                break;
            case R.id.keepaccount_clear:
                judge();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
