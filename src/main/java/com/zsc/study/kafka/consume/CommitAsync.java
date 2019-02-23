package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by work on 2019/2/23.
 * 消费者异步提交偏移量：
 * 手动提交有一个不足之处，在broker对提交请求作出回应之前，
 * 应用程序会一直阻塞，这样限制应用程序的吞吐量。我们可以通过降低提交频率来提升吞吐量，
 * 但如果发生了再均衡，会增加重复消息的量。
 * 在成功提交或者碰到无法恢复的错误之前，commitSyn()会一直重试，但是commitAsyn()不会,
 * 这也是commitAsyn()不好的一个地方。它之所以不进行重试，是因为在它收到服务器响应的时候可能有一个更大的偏移量已经提交成功。
 * 假设我们发出一个请求用于提交偏移量2000，这个时候发生短暂的通信问题，服务器接收不到请求，自然也不会做出任何响应。
 * 与此同时，我们处理了另外一批消息，并成功提交了偏移量3000，如果commitAsyn()重新尝试提交偏移量2000，它有可能在偏移量3000之后提交成功。
 * 这个时候如果在发生再均衡，就会出现重复消息。
 */
public class CommitAsync {

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
            }
        } catch (Exception e) {

        } finally {
            //异步提交
            kafkaConsumer.commitAsync();
        }
    }
}
