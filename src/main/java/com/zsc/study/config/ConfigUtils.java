package com.zsc.study.config;

import com.zsc.study.util.LogUtil;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/27 14:20
 * @Description: 配置工具类
 */
public class ConfigUtils {

    //缓存
    private static Map<String, ConfigBase> configMap = new ConcurrentHashMap<>();


    /**
     * 功能描述: 获取配置类
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午2:27
     */
    public static <T extends ConfigBase> T getConfig(Class<T> type) {
        T config = (T) configMap.get(type.getName());
        if (config == null) {
            synchronized (ConfigUtils.class) {
                config = (T) configMap.get(type.getName());
                if (config == null) {
                    try {
                        //获取构造函数
                        Constructor<T> constructor = type.getDeclaredConstructor();
                        //设置权限
                        constructor.setAccessible(true);
                        //实例化
                        config = constructor.newInstance();
                        config.load();
                    } catch (Exception e) {
                        LogUtil.frameworkLogger.error("反射生成配置类出错", e);
                    }
                    configMap.put(type.getName(), config);
                }
            }
        }
        return config;
    }
}
