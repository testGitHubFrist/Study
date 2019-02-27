package com.zsc.study.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/27 11:37
 * @Description: 日志输出服务类
 */
public class LogUtil {

    public final static Logger fileLogger = LoggerFactory.getLogger("fileLogger");

    public final static Logger frameworkLogger = LoggerFactory.getLogger("frameworkLogger");

    public final static Logger kafkaLogger = LoggerFactory.getLogger("kafkaLogger");

}
