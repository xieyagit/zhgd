package com.hujiang.project.common;

import java.io.Serializable;

/**
 *@ClassName Result
 *@Description TODO
 *@Author xieya
 *@Date 2020/4/17  12:07
 */
public class Result implements Serializable {

    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}