package com.zsc.study.kafka.consume;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * Created by work on 2019/2/23.
 * 再均衡监听器
 */
public class ConsumerRebalanceListenerTest {

    //偏移量
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    //
    private static KafkaConsumer<String, String> kafkaConsumer =null;

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.129:9092,192.168.1.129:9093");
        properties.put("group.id", "KafkaConsumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer= new KafkaConsumer(properties);
        String topic = "producer";
        //订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        //轮询消息
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String>  record:consumerRecords) {
                    
                }
                kafkaConsumer.commitAsync(currentOffsets,null);

            }
        } catch (Exception e) {

        } finally {
            kafkaConsumer.commitSync(currentOffsets);
            kafkaConsumer.close();
        }
    }

    /**
     * 再均衡监听器
     */
    private class HandleRealance implements ConsumerRebalanceListener {

        /**
         * 方法会在再均衡开始之前和消费者停止读取消息之后被调用。如果在这里提交偏移量，下一个接管分区的消费者就知道该从哪里开始读取了
         * @param collection
         */
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            System.out.print("Lost  partitions  in rebalance.Committing current offset:"+currentOffsets);
            kafkaConsumer.commitSync(currentOffsets);

        }

        /**
         * 方法会在重新分配分区之后和消费者开始读取消息之前被调用。
         * @param collection
         */
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        }
    }
}
