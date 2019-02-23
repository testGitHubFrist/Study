package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * Created by work on 2019/2/23.
 * 再均衡监听器
 */
public class ConsumerRebalanceListenerTest {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer kafkaConsumer= new KafkaConsumer(properties);
        String topic = "producer";
        //订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic),new SaveOffsetsOnRebalance(kafkaConsumer));
        //轮询消息
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String>  record:consumerRecords) {
                    System.out.print("消费者:"
                            + "主题：" + record.topic() + "\n"
                            + "键值：" + record.key() + "\n"
                            + "消息值：" + record.value() + "\n"
                            + "分区：" + record.partition() + "\n"
                            + "偏移量：" + record.offset() + "\n"
                    );
                }
            }
        } catch (Exception e) {

        } finally {

        }
    }
}
