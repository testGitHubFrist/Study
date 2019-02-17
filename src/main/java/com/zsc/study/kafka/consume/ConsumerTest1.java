package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/16 19:15
 * @Description: kafka消费者测试1
 */
public class ConsumerTest1 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer(properties);
        String topic = "producer";
        //订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        //轮询消息

    }
}
