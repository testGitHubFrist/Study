package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by work on 2019/2/23.
 * 消费者同步提交偏移量：
 * 把auto.commit.offerset设为false，让应用程序决定何时提交偏移量。使用commitSync()提交偏移量最简单也是最可靠。
 * 这个api会提交poll()方法返回的最新偏移量，提交成功后马上返回，如果提交失败就抛出异常。
 * commitSyn()会一直重试
 * 注意：
 * 手动提交有一个不足之处，在broker对提交请求作出回应之前，应用程序会一直阻塞，这样限制应用程序的吞吐量
 */
public class CommitSync {
    public static void main(String[] args) {

        //配置文件
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //创建kafka消费者
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        String topic = "producer";
        kafkaConsumer.subscribe(Collections.singleton(topic));
        //轮询消息
        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord consumerRecord : consumerRecords) {
                System.out.print("消费者:"
                        + "主题：" + consumerRecord.topic() + "\n"
                        + "键值：" + consumerRecord.key() + "\n"
                        + "消息值：" + consumerRecord.value() + "\n"
                        + "分区：" + consumerRecord.partition() + "\n"
                        + "偏移量：" + consumerRecord.offset() + "\n"
                );
            }
            //手动提交消息
            kafkaConsumer.commitSync();
        }
    }
}
