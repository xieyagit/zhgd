package com.hujiang.project.zhgd.hjProjectWorkers.domain;

public class SignParam {


    /** ID */
    private Integer id;
    /** 简易劳动合同上传状态（0、否。1、是） */
    private Integer contract;
    /** 工人进场承诺书上传状态（0、否。1、是） */
    private Integer entrance;
    /** 工人退场承诺书上传状态（0、否。1、是） */
    private Integer exeunt;
    /** 两制“工作”确认书上传状态（0、否。1、是） */
    private Integer workConfirm;
    /** 身份证正反面文件上传状态（0、否。1、是） */
    private Integer iDCardPDF;
    /** 安全教育培训是否合格（0、否。1、是） */
    private Integer isTrain;
    /** 信息是否公开（0、否。1、是） */
    private Integer information;

    public Integer getInformation() {
        return information;
    }

    public void setInformation(Integer information) {
        this.information = information;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public Integer getEntrance() {
        return entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    public Integer getExeunt() {
        return exeunt;
    }

    public void setExeunt(Integer exeunt) {
        this.exeunt = exeunt;
    }

    public Integer getWorkConfirm() {
        return workConfirm;
    }

    public void setWorkConfirm(Integer workConfirm) {
        this.workConfirm = workConfirm;
    }

    public Integer getiDCardPDF() {
        return iDCardPDF;
    }

    public void setiDCardPDF(Integer iDCardPDF) {
        this.iDCardPDF = iDCardPDF;
    }

    public Integer getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(Integer isTrain) {
        this.isTrain = isTrain;
    }

    @Override
    public String toString() {
        return "SignParam{" +
                "id=" + id +
                ", contract=" + contract +
                ", entrance=" + entrance +
                ", exeunt=" + exeunt +
                ", workConfirm=" + workConfirm +
                ", iDCardPDF=" + iDCardPDF +
                ", isTrain=" + isTrain +
                ", information=" + information +
                '}';
    }
}
