package com.perfay.keepaccount.module;

import android.util.Log;

import com.perfay.keepaccount.Database.bean.Account;
import com.perfay.keepaccount.Database.bean.UseType;
import com.perfay.keepaccount.Database.dao.AccountDao;
import com.perfay.keepaccount.Database.dao.KeepAccountDao;
import com.perfay.keepaccount.Database.dao.UseTypeDao;
import com.perfay.keepaccount.MainActivity;
import com.perfay.keepaccount.R;
import com.perfay.keepaccount.app.App;
import com.perfay.keepaccount.base.BasePresenter;
import com.perfay.keepaccount.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by liqihao on 2018/3/9.
 */

public class HomePresenter extends BasePresenter<MainActivity> implements HomeContract.presenter{

    private AccountDao accountDao = new AccountDao(App.AppContext);

    @Override
    public void getAllType() {
    }

    /**
     * 存储本地图标文件,方便查询
     */
    @Override
    public void saveType() {
        UseTypeDao useTypeDao = new UseTypeDao(App.AppContext);
        List<UseType> datas = new ArrayList<>();
        List<Account> accountData = new ArrayList<>();


        datas.add(new UseType("办公", R.mipmap.bangong,R.mipmap.bangongn,R.mipmap.bangongs));
        datas.add(new UseType("宠物",R.mipmap.chongwu,R.mipmap.chongwun,R.mipmap.chongwus));
        datas.add(new UseType("电脑",R.mipmap.diannao,R.mipmap.diannaon,R.mipmap.diannaos));
        datas.add(new UseType("服饰",R.mipmap.fushi,R.mipmap.fushin,R.mipmap.fushis));
        datas.add(new UseType("购物",R.mipmap.gouwu,R.mipmap.gouwun,R.mipmap.gouwus));
        datas.add(new UseType("家具",R.mipmap.jiaju,R.mipmap.jiajun,R.mipmap.jiajus));
        datas.add(new UseType("宠物",R.mipmap.chongwu,R.mipmap.chongwun,R.mipmap.chongwus));
        datas.add(new UseType("奖品",R.mipmap.jiangpin,R.mipmap.jiangpinn,R.mipmap.jiangpins));
        datas.add(new UseType("健身",R.mipmap.jianshen,R.mipmap.jianshenn,R.mipmap.jianshens));
        datas.add(new UseType("交通",R.mipmap.jiaotong,R.mipmap.jiaotongn,R.mipmap.jiaotongs));
        datas.add(new UseType("教育",R.mipmap.jiaoyu,R.mipmap.jiaoyun,R.mipmap.jiaoyus));
        datas.add(new UseType("加油",R.mipmap.jiayou,R.mipmap.jiayoun,R.mipmap.jiayous));
        datas.add(new UseType("宠物",R.mipmap.chongwu,R.mipmap.chongwun,R.mipmap.chongwus));
        datas.add(new UseType("捐赠",R.mipmap.juanzeng,R.mipmap.juanzengn,R.mipmap.juanzengs));
        datas.add(new UseType("快递",R.mipmap.kuaidi,R.mipmap.kuaidin,R.mipmap.kuaidis));
        datas.add(new UseType("理财",R.mipmap.licai,R.mipmap.licain,R.mipmap.licais));
        datas.add(new UseType("理发",R.mipmap.lifa,R.mipmap.lifan,R.mipmap.lifas));
        datas.add(new UseType("零食",R.mipmap.lingshi,R.mipmap.lingshin,R.mipmap.lingshis));
        datas.add(new UseType("礼品",R.mipmap.lipin,R.mipmap.lipinn,R.mipmap.lipins));
        datas.add(new UseType("旅行",R.mipmap.lvxing,R.mipmap.lvxingn,R.mipmap.lvxings));
        datas.add(new UseType("宠物",R.mipmap.chongwu,R.mipmap.chongwun,R.mipmap.chongwus));
        datas.add(new UseType("美容",R.mipmap.meirong,R.mipmap.meirongn,R.mipmap.meirongs));
        datas.add(new UseType("汽车",R.mipmap.qiche,R.mipmap.qichen,R.mipmap.qiches));
        datas.add(new UseType("其他",R.mipmap.qita,R.mipmap.qitan,R.mipmap.qitas));
        datas.add(new UseType("人情",R.mipmap.renqing,R.mipmap.renqingn,R.mipmap.renqings));
        datas.add(new UseType("日常",R.mipmap.richang,R.mipmap.richangn,R.mipmap.richangns));
        datas.add(new UseType("实验",R.mipmap.shiyan,R.mipmap.shiyann,R.mipmap.shiyans));
        datas.add(new UseType("蔬菜",R.mipmap.shucai,R.mipmap.shucain,R.mipmap.shucais));
        datas.add(new UseType("宠物",R.mipmap.chongwu,R.mipmap.chongwun,R.mipmap.chongwus));
        datas.add(new UseType("水电费",R.mipmap.shuidianfei,R.mipmap.shuidianfein,R.mipmap.shuidianfeis));
        datas.add(new UseType("水果",R.mipmap.shuiguo,R.mipmap.shuiguon,R.mipmap.shuiguos));
        datas.add(new UseType("书籍",R.mipmap.shuji,R.mipmap.shujin,R.mipmap.shujis));
        datas.add(new UseType("数码",R.mipmap.shuma,R.mipmap.shuman,R.mipmap.shumas));
        datas.add(new UseType("体育",R.mipmap.tiyu,R.mipmap.tiyun,R.mipmap.tiyus));
        datas.add(new UseType("通讯",R.mipmap.tongxun,R.mipmap.tongxunn,R.mipmap.tongxuns));
        datas.add(new UseType("维修",R.mipmap.weixiu,R.mipmap.weixiun,R.mipmap.weixius));
        datas.add(new UseType("学习",R.mipmap.xuexi,R.mipmap.xuexin,R.mipmap.xuexis));
        datas.add(new UseType("药品",R.mipmap.yaopin,R.mipmap.yaopinn,R.mipmap.yaopins));
        datas.add(new UseType("研究",R.mipmap.yanjiu,R.mipmap.yanjiun,R.mipmap.yanjius));
        datas.add(new UseType("餐饮",R.mipmap.canyin,R.mipmap.canyinn,R.mipmap.canyins));
        datas.add(new UseType("饮料",R.mipmap.yinliao,R.mipmap.yinliaon,R.mipmap.yinliaos));
        datas.add(new UseType("医疗",R.mipmap.yiliao,R.mipmap.yiliaon,R.mipmap.yiliaos));
        datas.add(new UseType("音乐",R.mipmap.yueqi,R.mipmap.yinyuen,R.mipmap.yinyues));
        datas.add(new UseType("游戏",R.mipmap.youxi,R.mipmap.youxin,R.mipmap.youxis));
        datas.add(new UseType("娱乐",R.mipmap.yule,R.mipmap.yulen,R.mipmap.yules));
        datas.add(new UseType("珠宝",R.mipmap.zhubao,R.mipmap.zhubaon,R.mipmap.zhubaos));
        useTypeDao.saveAllType(datas);


        accountData.add(new Account("支付宝"));
        accountData.add(new Account("微信"));
        accountData.add(new Account("信用卡"));
        accountData.add(new Account("现金"));

        accountDao.saveAllAccount(accountData);
    }


    private KeepAccountDao dao = new KeepAccountDao(App.AppContext);
    @Override
    public void getData() {
        baseview.onGetInf(dao.queryAllInf());
    }



//    void onGetAllMsg(int inCome,int out,int all,String time);

    @Override
    public void getAllMsg() {

        String time;
        int a = -1;
        int inCome = 0,out = 0,all = 0;

        for (int i = 0;i < dao.queryLessThatn0().size();i++) {
            out += dao.queryLessThatn0().get(i).getMoney();
        }

        for (int i = 0;i < dao.queryMoreThan0().size();i++) {
            inCome += dao.queryMoreThan0().get(i).getMoney();
        }

        all = inCome + out;

        time = dao.headTime();

        baseview.onGetAllMsg(inCome,out,all,time);

        int count = 1,j = 1;
        if (dao.queryAllInf().size()==0)
            count = 0;
        List<Integer>dayList = new ArrayList<>();
        for (int i = 0; i < dao.queryAllInf().size(); i++) {
            dayList.add(Integer.parseInt(dao.getDay(i)));
        }
        for (int i = 0;j <dayList.size();j++,i++) {
            Log.e("day", String.valueOf(dayList.get(i)) + String.valueOf(dao.queryAllInf().size()));
            if (!Objects.equals(dayList.get(j), dayList.get(i)))
                count++;
        }
        SharedPreferencesUtil.getInstance().putInt("daycount",count);
        SharedPreferencesUtil.getInstance().putInt("usecount",dao.queryAllInf().size());
    }


}
