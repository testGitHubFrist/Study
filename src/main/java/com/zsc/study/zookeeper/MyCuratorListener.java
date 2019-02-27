package com.zsc.study.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/25 17:32
 * @Description:
 */
public class MyCuratorListener implements CuratorListener {
    @Override
    public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

        System.out.println(JSON.toJSON(event));

    }
}
