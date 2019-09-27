package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class ResultOnt {


    private String method;
    private String xmcode;
    private String xmid;
    private String xmnum;
    private String ticket;
    private String  callSoft;
    private String  account;
    private String  psd;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getXmcode() {
        return xmcode;
    }

    public void setXmcode(String xmcode) {
        this.xmcode = xmcode;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getXmnum() {
        return xmnum;
    }

    public void setXmnum(String xmnum) {
        this.xmnum = xmnum;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getCallSoft() {
        return callSoft;
    }

    public void setCallSoft(String callSoft) {
        this.callSoft = callSoft;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    @Override
    public String toString() {
        return "ResultOnt{" +
                "method='" + method + '\'' +
                ", xmcode='" + xmcode + '\'' +
                ", xmid='" + xmid + '\'' +
                ", xmnum='" + xmnum + '\'' +
                ", ticket='" + ticket + '\'' +
                ", callSoft='" + callSoft + '\'' +
                ", account='" + account + '\'' +
                ", psd='" + psd + '\'' +
                '}';
    }
}
