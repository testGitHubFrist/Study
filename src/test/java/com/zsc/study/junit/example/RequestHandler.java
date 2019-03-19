package com.zsc.study.junit.example;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 17:32
 * @Description:
 */
public interface RequestHandler {
    Response process(Request request) throws Exception;
}
