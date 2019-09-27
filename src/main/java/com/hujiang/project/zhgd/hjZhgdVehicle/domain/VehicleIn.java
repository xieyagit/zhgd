package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VehicleIn {

    @JsonProperty
    private String method;
    @JsonProperty
    private String xmcode;
    @JsonProperty
    private String ticket;
    @JsonProperty
    private String  callSoft;
    @JsonProperty
    private String  account;
    @JsonProperty
    private String  psd;
    @JsonProperty
    private List<VehicleInList> data;

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

    public List<VehicleInList> getData() {
        return data;
    }

    public void setData(List<VehicleInList> data) {
        this.data = data;
    }
}
