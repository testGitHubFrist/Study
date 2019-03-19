package com.zsc.study.junit.example.impl;

import com.zsc.study.junit.example.Request;
import com.zsc.study.junit.example.Response;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 17:57
 * @Description:
 */
public class ErrorResponse implements Response {

    private Request originalRequest;
    private Exception originalException;

    public ErrorResponse(Request originalRequest, Exception originalException) {
        this.originalRequest = originalRequest;
        this.originalException = originalException;
    }

    public Request getOriginalRequest() {
        return originalRequest;
    }

    public void setOriginalRequest(Request originalRequest) {
        this.originalRequest = originalRequest;
    }

    public Exception getOriginalException() {
        return originalException;
    }

    public void setOriginalException(Exception originalException) {
        this.originalException = originalException;
    }
}
