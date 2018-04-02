package com.perfay.keepaccount.Database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by liqihao on 2018/3/19.
 */

@DatabaseTable(tableName = "tab_keepaccount")
public class KeepAccountDatabase {

    @DatabaseField(useGetSet = true,generatedId = true,columnName = "id")
    private int id;

    @DatabaseField(useGetSet = true,columnName = "time")
    private String time;

    @DatabaseField(useGetSet = true,columnName = "usetype",foreign = true,foreignAutoRefresh = true)
    private UseType useType;

    @DatabaseField(useGetSet = true,columnName = "money")
    private int money;

    @DatabaseField(useGetSet = true,columnName = "moneytype",foreign = true,foreignAutoRefresh = true)
    private Account account;

    @DatabaseField(useGetSet = true,columnName = "beizhu",canBeNull = true)
    private String beizhu;

    public KeepAccountDatabase(){}

    public KeepAccountDatabase (String time,int money) {
        this.time = time;
        this.money = money;
    }

    public KeepAccountDatabase(String time,UseType useType,int money,Account account) {
        this.time = time;
        this.useType = useType;
        this.money = money;
        this.account = account;
    }

    public KeepAccountDatabase(String time,UseType useType,int money,Account account,String beizhu) {
        this.time = time;
        this.useType = useType;
        this.money = money;
        this.account = account;
        this.beizhu = beizhu;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UseType getUseType() {
        return useType;
    }

    public void setUseType(UseType useType) {
        this.useType = useType;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "KeepAccountDatabase{" + "id = " + id + ",time = " + time +
                ",useType = " + useType + ",money = " + money + ",moneyType = " + account + "}";
    }
}
