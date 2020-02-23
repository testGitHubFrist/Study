//package com.zsc.study.hadoop.hdfs;
//
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
//import org.apache.hadoop.util.Progressable;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URI;
//
///**
// * @Auther: zhangshanchuang
// * @Date: 19/3/20 20:41
// * @Description: java操作HDFS的API
// */
//public class JavaHDFSAPITest {
//
//    //HDFS地址
//    public static final String HDFS_PATH = "hdfs://localhost:9000";
//
//    // HDFS文件系统的操作对象
//    FileSystem fileSystem = null;
//    // 配置对象
//    Configuration configuration = null;
//
//    /**
//     * 初始化
//     */
//    @Before
//    public void setUp() throws Exception {
//        configuration = new Configuration();
//        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "user");
//        System.out.println("JavaHDFSAPITest setUp");
//
//    }
//
//
//    /**
//     * 功能描述: 创建目录
//     *
//     * @param:
//     * @return:
//     * @auther: zhangshanchuang
//     * @date: 19/3/21 下午2:22
//     */
//    @Test
//    public void mkdir() throws Exception {
//        fileSystem.mkdirs(new Path("/hdfs/test"));
//    }
//
//    /**
//     * 功能描述:创建文件
//     *
//     * @param:
//     * @return:
//     * @auther: zhangshanchuang
//     * @date: 19/3/21 下午2:25
//     */
//    @Test
//    public void create() throws Exception {
//        FSDataOutputStream outputStream = fileSystem.create(new Path("/hdfs/test/a.txt"));
//        outputStream.write("hello world".getBytes());
//        outputStream.flush();
//        outputStream.close();
//    }
//
//    /**
//     * 功能描述:查看文件内容
//     *
//     * @param:
//     * @return:
//     * @auther: zhangshanchuang
//     * @date: 19/3/21 下午2:27
//     */
//    @Test
//    public void cat() throws Exception {
//        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/hdfs/test/a.txt"));
//        IOUtils.copyBytes(fsDataInputStream,System.out,1024);
//        fsDataInputStream.close();
//    }
//
//
//    /**
//     * 上传大体积的本地文件到HDFS，并显示进度条
//     */
//    @Test
//    public void copyFromLocalFileWithProgress() throws Exception {
//        InputStream in = new BufferedInputStream(new FileInputStream(new File("/Users/user/Downloads/学习资料/thrift_sunli.ppt")));
//        FSDataOutputStream outputStream = fileSystem.create(new Path("/hdfs/test/thrift_sunli.ppt"), new Progressable() {
//            public void progress() {
//                // 进度条的输出
//                System.out.print(".");
//            }
//        });
//        IOUtils.copyBytes(in, outputStream, 4096);
//        in.close();
//        outputStream.close();
//    }
//    /**
//     * 释放资源
//     *
//     * @throws Exception
//     */
//    @After
//    public void tearDown() throws Exception {
//        configuration = null;
//        fileSystem = null;
//        System.out.println("JavaHDFSAPITest tearDown");
//    }
//}
