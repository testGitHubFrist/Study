package com.zsc.study.kafka.consume;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
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
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);
        String topic = "producer";
        //订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        //轮询消息
        try {
            //无限循环
            while (true) {
                //轮询消息
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
                for (ConsumerRecord<String,String> record:consumerRecords) {
                    System.out.println("消费者消息的消息主题"+JSONObject.toJSONString(record.topic())+"\n"
                            +"消息的key"+record.key()+"\n"
                            +"消息的value"+record.value()+"\n"
                            +"主题的分区"+record.partition()+"\n"
                            +"消息的偏移量"+record.offset()+"\n");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }finally {
            kafkaConsumer.close();
        }

    }
}
