package com.perfay.keepaccount.Database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by liqihao on 2018/3/21.
 */
@DatabaseTable(tableName = "tab_account")
public class Account {

    @DatabaseField(useGetSet = true,generatedId = true,columnName = "id")
    private int id;

    @DatabaseField(useGetSet = true,columnName = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account(){}

    public Account(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" + "id = " + id +
                ",name = " + name + "}";
    }
}
