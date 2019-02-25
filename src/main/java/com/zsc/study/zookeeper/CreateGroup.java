package com.zsc.study.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/21 17:18
 * @Description:
 */
public class CreateGroup implements Watcher {

    //会话超时时间
    private static final int SESSION_TIME_OUT = 1000;

    private ZooKeeper zk = null;

    //同步计数器
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public CreateGroup() {
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("监听事件开始>>>>>>>>>{event:" + JSON.toJSON(event) + "}");
        if (event.getState() == Event.KeeperState.SyncConnected) {
            //计数器减一
            countDownLatch.countDown();
        }
        /**
         * WatchedEvent一般包括zookeeper会话状态（keeperState）、事件类型（EventType）
         * zookeeper会话状态（keeperState）：Disconnected、SyncConnected、AuthFailed、ConnectedReadOnly、SaslAuthenticated、Expired
         * 事件类型（EventType）:NodeCreated、NodeDeleted、NodeDataChanged、NodeChildrenChanged、None
         */
    }

    /**
     * 创建zk对象
     * 当客户端连接上zookeeper时会执行process(event)里的countDownLatch.countDown()，计数器的值变为0，则countDownLatch.await()方法返回。
     *
     * @param hostsList
     * @throws Exception
     */
    public void connect(String hostsList) throws Exception {
        this.zk = new ZooKeeper(hostsList, SESSION_TIME_OUT, this);
        //阻塞程序继续执行
        countDownLatch.await();
    }

    /**
     * @param path
     * @param data
     */
    public void createPath(String path, byte data[], List<ACL> acl, CreateMode createMode) throws Exception {
        zk.create(path, data, acl, createMode);
        System.out.println("Created " + path);
    }

    /**
     * 当观察的znode被创建、删除或其数据被更新时，设置在exists上的观察将会被触发；
     */
    public void exists(String path, Watcher watcher) throws Exception {
        zk.exists(path, watcher);

    }


    /**
     * 设置值
     *
     * @param path
     * @param data
     * @param version
     * @throws Exception
     */
    public void setData(String path, byte data[], int version) throws Exception {
        zk.setData(path, data, version);
    }

    /**
     * 读取数据
     *
     * @param path
     * @param watcher
     * @param stat
     * @throws Exception
     */
    public void readData(String path, Watcher watcher, Stat stat) throws Exception {
        zk.getData(path, watcher, stat);
    }

    public void deletePath(String path, int version) throws Exception {
        zk.delete(path, version);
    }

    /**
     * 关闭zk
     *
     * @throws InterruptedException
     */
    public void close() throws InterruptedException {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                throw e;
            } finally {
                zk = null;
                System.gc();
            }
        }
    }
}
