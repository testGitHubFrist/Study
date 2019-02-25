package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by work on 2019/2/23.
 * 组合提交偏移量
 */
public class CommitCombination {

    public static void main(String[] args) {

        //配置文件
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //创建kafka消费者
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        String topic = "producer";
        kafkaConsumer.subscribe(Collections.singleton(topic));
        //轮询消息
        try {
            while (true) {
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
                //异步提交
                kafkaConsumer.commitAsync();
            }
        } catch (Exception e) {

        } finally {
            //重试机制提交偏移量
            kafkaConsumer.commitSync();
        }
    }
}
