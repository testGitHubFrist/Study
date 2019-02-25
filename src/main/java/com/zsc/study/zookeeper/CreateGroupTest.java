package com.zsc.study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/21 17:54
 * @Description:
 * 当观察的znode被创建、删除或其数据被更新时，设置在exists上的观察将会被触发；
 *
 * 当观察的znode被删除或数据被更新时，设置在getData上的观察将会被触发；
 *
 * 当观察的znode的子节点被创建、删除或znode自身被删除时，设置在getChildren上的观察将会被触发，可通过观察事件的类型来判断被删除的是znode还是它的子节点。
 */
public class CreateGroupTest {

    private static String hosts = "localhost:2181,localhost:2182,localhost:2183";

    private static String createPath = "/master";

    public static void main(String[] args) {

        try {
            CreateGroup createGroup = new CreateGroup();
            createGroup.connect(hosts);
            //当观察的znode被创建、删除或其数据被更新时，设置在exists上的观察将会被触发；（注意单次监听器，没用一次设置一次，最好放到坚挺群组中）
            createGroup.exists(createPath,createGroup);
            //创建节点
            createGroup.createPath(createPath,createPath.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            //当观察的znode被删除或数据被更新时，设置在getData上的观察将会被触发；
            createGroup.readData(createPath,createGroup,null);
            //设置值
            createGroup.setData(createPath,"test".getBytes(),-1);
            createGroup.deletePath(createPath,-1);
            Thread.sleep(100000);
        }catch (Exception e){
            System.out.println("Exception:"+e.getMessage());
        }
    }

}
