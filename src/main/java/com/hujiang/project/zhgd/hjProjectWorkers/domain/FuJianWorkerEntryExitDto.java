package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import java.io.Serializable;

/**
 *@ClassName WorkerEntryExitDto
 *@Description TODO
 *@Author xieya
 *@Date 2020/4/20  14:18
 */
public class FuJianWorkerEntryExitDto implements Serializable {

    /**证件类型
     01 居民身份证
     02 军官证
     03 武警警官证
     04 士兵证
     05 军队离退休干部证
     06 残疾人证
     07 残疾军人证（1-8 级）
     08 护照
     09 港澳同胞回乡证
     10 港澳居民来往内地通行证
     11 中华人民共和国往来港澳通行证
     12 台湾居民来往大陆通行证
     13 大陆居民往来台湾通行证
     14 外交官证
     15 领事馆证
     16 海员证
     17 香港身份证
     18 台湾身份证
     19 澳门身份证
     20 外国人身份证件
     21 高校毕业生自主创业证
     22 就业失业登记证
     23 台胞证
     24 退休证
     25 离休证
     99 其它证件*/
    private String idCardType;
    /**证件号码。 AES*/
    private String idCardNumber;
    /**进退场日期，yyyy-MM-dd*/
    private String date;
    /**类型  1 进场   0 退场*/
    private int type;

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}