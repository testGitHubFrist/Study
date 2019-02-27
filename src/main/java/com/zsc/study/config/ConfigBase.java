package com.zsc.study.config;

import com.zsc.study.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 功能描述:
 *
 * @param: 配置基类
 * @return:
 * @auther: zhangshanchuang
 * @date: 19/2/27 上午11:13
 */
public abstract class ConfigBase {

    private String fileName;

    private Properties properties = new Properties();

    public String getFileName() {
        return fileName;
    }

    public abstract void setFileName();

    /**
     * 功能描述: 加载配置文件
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 上午11:29
     */
    public void load() {
        setFileName();
        try {
            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (Exception e) {
            LogUtil.fileLogger.error("［ConfigBase.load()］:读取文件异常 file:{},exception:{}", fileName, e.getMessage());
        }
    }


    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午2:15
     */
    public String getPropertiesString(String key) {
        return properties.getProperty(key);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午2:15
     */
    public int getPropertiesInt(String key) {
        String stringValue = getPropertiesString(key);
        return stringValue == null ? 0 : Integer.valueOf(stringValue);
    }


    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午2:15
     */
    public boolean getPropertiesBoolean(String key) {
        String stringValue = properties.getProperty(key);
        return stringValue == null ? false : Boolean.valueOf(stringValue);
    }
}
