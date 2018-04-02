package com.perfay.keepaccount.Database.dao;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.j256.ormlite.dao.Dao;
import com.perfay.keepaccount.Database.DatabaseHelper;
import com.perfay.keepaccount.Database.bean.KeepAccountDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/20.
 */

public class KeepAccountDao {
    private Dao<KeepAccountDatabase,Integer> keepAccountDao;
    private DatabaseHelper helper;

    public KeepAccountDao(Context context) {
        helper = DatabaseHelper.getHelper(context);
        try {
            keepAccountDao = helper.getDao(KeepAccountDatabase.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addInf(KeepAccountDatabase inf) {
        try {
            keepAccountDao.create(inf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delInf(KeepAccountDatabase inf) {
        try {
            keepAccountDao.delete(inf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delInfById(Integer id) {
        try {
            keepAccountDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInf(KeepAccountDatabase inf) {
        try {
            keepAccountDao.update(inf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<KeepAccountDatabase> queryAllInf() {
        ArrayList<KeepAccountDatabase> datas = null;
        try {
            datas = (ArrayList<KeepAccountDatabase>) keepAccountDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public List<KeepAccountDatabase> queryLessThatn0() {
        List<KeepAccountDatabase> datas = null;
        try {
            datas = keepAccountDao.queryBuilder().where().lt("money",0).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public List<KeepAccountDatabase> queryMoreThan0() {
        List<KeepAccountDatabase> datas = null;
        try {
            datas = keepAccountDao.queryBuilder().where().gt("money",0).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }


    public String getDay(int i) {
        String[] mTime = new String[4];
        if (queryAllInf().size() != 0) {
            mTime = queryAllInf().get(i).getTime().split(":", 4);
        }
        return mTime[2];
    }

    /**
     * 获取主页时间（第一条时间到最后一条时间)
     * @return
     */
    public String headTime() {
        StringBuilder sTime = new StringBuilder("");
        StringBuilder ssTime = new StringBuilder("");
        if (queryAllInf().size() != 0) {
            String[] mTime = queryAllInf().get(0).getTime().split(":",4);
            String[] xTime = queryAllInf().get(queryAllInf().size() - 1).getTime().split(":",4);
            for (int i = 0;i < 3;i++) {
                if (i!=2) {
                    sTime.append(mTime[i]).append(".");
                    ssTime.append(xTime[i]).append(".");
                }
                else {
                    sTime.append(mTime[i]);
                    ssTime.append(xTime[i]);
                }
            }
            return String.valueOf(sTime) + " - " + String.valueOf(ssTime);
        }
        Log.e("sTime", String.valueOf(sTime) + " - " + String.valueOf(ssTime));
        return "暂无记录";

    }

    public String allTheTime(){
        return queryAllInf().size() == 0 ? "暂无记录" :queryAllInf().get(0).getTime() + " - "
                + queryAllInf().get(queryAllInf().size() - 1).getTime();
    }


    /**
     *
     * @param time 根据时间搜索
     *             格式: xxxx:xx:xx
     * @return
     */
    public List<KeepAccountDatabase> queryByTime(String time) {
        List<KeepAccountDatabase> datas = new ArrayList<>();
        try {
            datas = keepAccountDao.queryBuilder().where().like("time",time + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public List<KeepAccountDatabase> searchByWord(String word) {
        List<KeepAccountDatabase> data = new ArrayList<>();

        for (int i = 0; i < queryAllInf().size();i++) {
            if (queryAllInf().get(i).getAccount().getName().contains(word))
                data.add(queryAllInf().get(i));
        }

        for (int i = 0; i < queryAllInf().size();i++) {
            if (queryAllInf().get(i).getUseType().getName().contains(word))
                data.add(queryAllInf().get(i));
        }
        return data;
    }

    public void deleteAllData() {
        try {
            keepAccountDao.delete(queryAllInf());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
