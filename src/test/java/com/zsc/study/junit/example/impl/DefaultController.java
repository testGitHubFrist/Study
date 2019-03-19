package com.zsc.study.junit.example.impl;

import com.zsc.study.junit.example.Controller;
import com.zsc.study.junit.example.Request;
import com.zsc.study.junit.example.RequestHandler;
import com.zsc.study.junit.example.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 17:37
 * @Description:
 */
public class DefaultController implements Controller {

    private Map requestHandlers = new HashMap();

    protected RequestHandler getHandler(Request request) {
        if (this.requestHandlers.containsKey(request.getName())) {
            throw new RuntimeException("cannot find handler for request name [" + request.getName() + "]");
        }

        return (RequestHandler) this.requestHandlers.get(request.getName());

    }

    @Override
    public Response processRequset(Request request) {
        try {
            return getHandler(request).process(request);
        } catch (Exception e) {
            return new ErrorResponse(request, e);
        }
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {

        if (this.requestHandlers.containsKey(request.getName())) {
            throw new RuntimeException("this handler already been register for request name [" + request.getName() + "]");
        } else {
            requestHandlers.put(request.getName(), requestHandler);
        }
    }
}
