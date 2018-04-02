package com.perfay.keepaccount.Database.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.perfay.keepaccount.Database.DatabaseHelper;
import com.perfay.keepaccount.Database.bean.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/21.
 */

public class AccountDao {

    private Dao<Account,Integer> accountDao;

    private DatabaseHelper helper;

    public AccountDao(Context context) {
        helper = DatabaseHelper.getHelper(context);
        try {
            accountDao = helper.getDao(Account.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        try {
            accountDao.create(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAllAccount(List<Account> datas){
        for (int i = 0; i < datas.size(); i++) {
            try {
                accountDao.create(datas.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param name 账户名称
     *  根据名称查询数据然后删除
     */
    public void delAccount(String name) {
        try {
            accountDao.delete(accountDao.queryBuilder().where().eq("name",name).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Account> datas() {
        ArrayList<Account> data = null;
        try {
            data = (ArrayList<Account>)accountDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
