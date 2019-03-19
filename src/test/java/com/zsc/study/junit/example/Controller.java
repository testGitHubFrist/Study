package com.zsc.study.junit.example;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 17:32
 * @Description:
 */
public interface Controller {
    Response processRequset(Request request);

    void addHandler(Request request, RequestHandler requestHandler);
}
