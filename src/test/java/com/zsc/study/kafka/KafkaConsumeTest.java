//package com.zsc.study.kafka;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Collections2;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URI;
//import java.util.Collections;
//import java.util.Properties;
//
///**
// * @Auther: zhangshanchuang
// * @Date: 19/3/21 16:17
// * @Description:
// */
//public class KafkaConsumeTest {
//
//    Properties properties;
//    KafkaConsumer<String, String> kafkaConsumer;
//
//    //HDFS地址
//    public static final String HDFS_PATH = "hdfs://localhost:9000";
//
//    // HDFS文件系统的操作对象
//    FileSystem fileSystem = null;
//    // 配置对象
//    Configuration configuration = null;
//
//    @Before
//    public void init() {
//        properties = new Properties();
//        properties.put("bootstrap.servers", "localhost:9092");
//        properties.put("group.id", "KafkaConsumer");
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaConsumer = new KafkaConsumer(properties);
//    }
//
//    @Test
//    public void testConsumer() throws Exception {
//        kafkaConsumer.subscribe(Collections.singleton("kafka"));
//        //轮询消息
//        try {
//            while (true) {
//                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
//                for (ConsumerRecord consumerRecord : consumerRecords) {
//                    System.out.println("消费数据为：" + consumerRecord.value());
//                    writeHDFS((String) consumerRecord.value());
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            kafkaConsumer.close();
//        }
//    }
//
//    public void writeHDFS(String msg) throws Exception {
//        Path path = new Path("/hdfs/test/kafka/kafka.txt");
//        configuration = new Configuration();
//        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "user");
//
//        //创建文件
//        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
//        fsDataOutputStream.write(msg.getBytes());
//        fsDataOutputStream.flush();
//        fsDataOutputStream.close();
//    }
//
//}
