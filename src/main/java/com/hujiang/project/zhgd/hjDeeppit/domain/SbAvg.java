package com.hujiang.project.zhgd.hjDeeppit.domain;

import java.math.BigDecimal;

/**
 * @program: Hujiang
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-09-12 10:04
 **/
public class SbAvg {

    private String subside;

    private Integer factorId;

    public String getSubside() {
        return subside;
    }

    public void setSubside(String subside) {
        this.subside = subside;
    }

    public Integer getFactorId() {
        return factorId;
    }

    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
    }

    @Override
    public String toString() {
        return "SbAvg{" +
                "subside='" + subside + '\'' +
                ", factorId=" + factorId +
                '}';
    }
}
