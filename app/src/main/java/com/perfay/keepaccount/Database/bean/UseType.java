package com.perfay.keepaccount.Database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by liqihao on 2018/3/19.
 */
//消费|收入的类别表
@DatabaseTable(tableName = "tab_usetype")
public class UseType {
    //类别ID
    @DatabaseField(useGetSet = true,generatedId = true,columnName = "id")
    private int id;
    //本地的图片地址
    @DatabaseField(useGetSet = true,columnName = "img")
    private int img;
    //类别名称
    @DatabaseField(useGetSet = true,columnName = "name")
    private String name;

    @DatabaseField(useGetSet = true,columnName = "imgn")
    private int imgn;

    @DatabaseField(useGetSet = true,columnName = "imgs")
    private int imgs;

    public UseType(String name,int img,int imgn,int imgs) {
        this.name = name;
        this.img = img;
        this.imgn = imgn;
        this.imgs = imgs;
    }

    public UseType() {}

    public int getImgn() {
        return imgn;
    }

    public void setImgn(int imgn) {
        this.imgn = imgn;
    }

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "userType{" + "id = " + id + ",name = " + name + "}";
    }
}
