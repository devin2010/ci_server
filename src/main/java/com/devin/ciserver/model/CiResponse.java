package com.devin.ciserver.model;
/**
 * 通用响应对象, 封装数据如下
 *
 * @author lcy
 */
public class CiResponse<T> {
    private String flag;//SUCCESS/FAILURE
    private String errorMsg;
    private T data;
    public CiResponse() {
    }

    public CiResponse(String flag, String errorMsg, T data) {
        this.flag = flag;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public CiResponse(String flag, T data) {
        this.flag = flag;
        this.data = data;
    }
    public CiResponse(String flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
    public CiResponse(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}