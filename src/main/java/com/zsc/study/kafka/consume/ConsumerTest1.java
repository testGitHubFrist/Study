package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/16 19:15
 * @Description: kafka消费者测试1
 * 消费消息
 * ./kafka-console-consumer.sh  --zookeeper 192.168.1.128:2181  --topic producer --from-beginning
 * <p>
 * 查看主题
 * ./kafka-topics.sh --list --zookeeper 192.168.1.128:2181 --topic producer
 * 查看所有topic
 * ./kafka-topics.sh --list --zookeeper 192.168.1.128:2181
 */
public class ConsumerTest1 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);
        String topic = "producer";
        //订阅主题
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
            }
        } catch (Exception e) {

        } finally {
            kafkaConsumer.close();
        }
    }
}
