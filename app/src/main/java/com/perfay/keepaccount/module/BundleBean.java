package com.perfay.keepaccount.module;

import java.io.Serializable;

/**
 * Created by liqihao on 2018/3/13.
 */

public class BundleBean implements Serializable{
    /**
     * title :页面标题
     * isHaveFinish：是否有完成按钮
     * type: 0 : 记账页面
     *      1：分类管理页面
     *      2:图表统计页面
     */
    private String title;
    private boolean isHaveFinish;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHaveFinish() {
        return isHaveFinish;
    }

    public void setHaveFinish(boolean haveFinish) {
        isHaveFinish = haveFinish;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BundleBean (String title,boolean isHaveFinish, int type) {
        this.title = title;
        this.isHaveFinish = isHaveFinish;
        this.type = type;
    }
}
