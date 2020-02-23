package com.zsc.study.config;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public class HystrixInitConfiguration implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(HystrixInitConfiguration.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        PropertiesConfiguration hystrixConfig = new PropertiesConfiguration();
        try{
            System.setProperty(DynamicPropertyFactory.ENABLE_JMX,"true");
            hystrixConfig.load("conf/custom/hystrix.properties");
            ConfigurationManager.install(hystrixConfig);
        }catch (ConfigurationException e){
            throw new IllegalArgumentException("载入hystrix.properties失败，请检查配置!"+e.getMessage(),e);
        }catch (Exception e){
            throw new IllegalArgumentException("安装hystrix配置项目失败。" + e.getMessage(), e);
        }
    }
}