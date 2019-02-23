package com.zsc.study.kafka.product;

import com.alibaba.fastjson.JSONObject;
import com.zsc.study.util.PropertiesHelper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/14 23:55
 * @Description: 生产者测试1 创建一个生产者并发送消息
 *
 * 消费消息
 * ./kafka-console-consumer.sh  --zookeeper localhost:2181  --topic test --from-beginning
 *
 * 查看主题
 * ./kafka-topics.sh --zookeeper localhost:2181 --describe --topic test
 */
public class ProducerTest1 {
    private static final String KAFKA_GROUP = PropertiesHelper.getEnvProperties("kafka.broker.group", "producerKafka").toString();

    private static final String KAFKA_ID = PropertiesHelper.getEnvProperties("kafka.client.id", "producerKafka").toString();

    private static final String KEY_CONFIG = PropertiesHelper.getEnvProperties("key.serializer.class.config", "producerKafka").toString();

    private static final String VALUE_CONFIG = PropertiesHelper.getEnvProperties("value.serializer.class.config", "producerKafka").toString();

    public static void main(String[] args) {

        //新建一个properties对象
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("client.id", "KafkaProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //创建生产者
        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(properties);
        String topic = "producer";
        //设置生产者发送消息
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "test");
        try {
            Future<RecordMetadata> future =  kafkaProducer.send(producerRecord);
            RecordMetadata recordMetadata = future.get();
            System.out.println("test:"+JSONObject.toJSON(recordMetadata));
        } catch (Exception e) {
            System.out.println("exception："+e.getMessage());
        }

    }
}
