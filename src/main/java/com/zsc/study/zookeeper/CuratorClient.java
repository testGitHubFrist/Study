package com.zsc.study.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/25 16:44
 * @Description: Curator测试用例项目
 */
public class CuratorClient {

    private static String hosts = "localhost:2181,localhost:2182,localhost:2183";

    public static void main(String[] args){

        try {
            RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(5000,15000,3);
            //连接zookeeper
            CuratorFramework zkc = CuratorFrameworkFactory.newClient(hosts,retryPolicy);
            zkc.start();
            zkc.getCuratorListenable().addListener(new MyCuratorListener());
            zkc.create().withMode(CreateMode.EPHEMERAL).forPath("/Curator","Curator Test!!!".getBytes());
            byte[] s= zkc.getData().forPath("/Curator");

            System.out.println(new String(s));
            zkc.delete().forPath("/Curator");
        }catch (Exception e){
            System.out.println("异常："+e.getMessage());
        }


    }
}
