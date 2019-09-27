package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import org.springframework.web.multipart.MultipartFile;

public class AliOcrBankCard {


    private String configStr;
    private String url;

    public String getConfigStr() {
        return configStr;
    }

    public void setConfigStr(String configStr) {
        this.configStr = configStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
