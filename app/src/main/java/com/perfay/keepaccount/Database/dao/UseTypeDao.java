package com.perfay.keepaccount.Database.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.perfay.keepaccount.Database.DatabaseHelper;
import com.perfay.keepaccount.Database.bean.UseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqihao on 2018/3/20.
 */

public class UseTypeDao {
    private Dao<UseType,Integer> useTypeDao;

    private DatabaseHelper helper;

    public UseTypeDao(Context context) {

        helper = DatabaseHelper.getHelper(context);
        try {
            useTypeDao = helper.getDao(UseType.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUseType(UseType useType) {
        try {
            useTypeDao.create(useType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUseType(UseType useType) {
        try {
            useTypeDao.delete(useType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UseType> queryAllType() {
        ArrayList<UseType> allType = null;
        try {
            allType = (ArrayList<UseType>)useTypeDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allType;
    }

    public void saveAllType(List<UseType> datas){

        for (int i = 0; i < datas.size(); i++) {
            try {
                useTypeDao.create(datas.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    public UseType searchTypeByName


}


