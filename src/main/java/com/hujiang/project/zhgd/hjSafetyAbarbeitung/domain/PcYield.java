package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

public class PcYield {
    private Integer checkdSum;
    private Integer noQualified;
    private Integer qualified;
    private Double yield;

    public Integer getCheckdSum() {
        return checkdSum;
    }

    public void setCheckdSum(Integer checkdSum) {
        this.checkdSum = checkdSum;
    }

    public Integer getNoQualified() {
        return noQualified;
    }

    public void setNoQualified(Integer noQualified) {
        this.noQualified = noQualified;
    }

    public Integer getQualified() {
        return qualified;
    }

    public void setQualified(Integer qualified) {
        this.qualified = qualified;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    @Override
    public String toString() {
        return "PcYield{" +
                "checkdSum=" + checkdSum +
                ", noQualified=" + noQualified +
                ", qualified=" + qualified +
                ", yield=" + yield +
                '}';
    }
}
