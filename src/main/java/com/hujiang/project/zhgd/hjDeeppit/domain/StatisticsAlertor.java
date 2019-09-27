package com.hujiang.project.zhgd.hjDeeppit.domain;

/**
 * @program: Hujiang
 * @description:
 * @author: tony
 * @create: 2019-09-16 11:44
 **/
public class StatisticsAlertor {

    //等级
    private  String level;

    //条数
    private int cnt;
    //比例
    private String r;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }


    @Override
    public String toString() {
        return "StatisticsAlertor{" +
                "level='" + level + '\'' +
                ", cnt=" + cnt +
                ", r='" + r + '\'' +
                '}';
    }
}
