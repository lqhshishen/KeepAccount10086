package com.perfay.keepaccount.module.ChartStatistics.ui;

import com.github.mikephil.charting.charts.LineChart;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.base.BaseFragment;
import com.perfay.keepaccount.module.ChartStatistics.contract.ChartStatisticsExpenditureContract;
import com.perfay.keepaccount.module.ChartStatistics.presenter.ChartStatisticsExpenditurePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liqihao on 2018/3/15.
 */

public class ChartStatisticsExpenditureFragment extends BaseFragment<ChartStatisticsExpenditurePresenter> implements ChartStatisticsExpenditureContract.view {


    @BindView(R.id.chart_mainChart)
    LineChart chartMainChart;
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
    public void setPresenter(ChartStatisticsExpenditurePresenter presenter) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public void initView() {
        ButterKnife.bind(getActivity());
    }

    @Override
    public void getData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_chartstatisticsexpenditure;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
