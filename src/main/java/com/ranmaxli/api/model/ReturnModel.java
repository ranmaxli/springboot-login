package com.ranmaxli.api.model;

import java.io.Serializable;

/**
 * Created by ranli2 on 9/27/2017.
 */
public class ReturnModel implements Serializable {

    private int code;
    private String msg;
    private Object data;
    private Long responsetime;

    public ReturnModel(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.responsetime = System.currentTimeMillis();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getResponsetime() {return responsetime;}

    public void setResponsetime(Long responsetime) {
        this.responsetime = responsetime;
    }
}