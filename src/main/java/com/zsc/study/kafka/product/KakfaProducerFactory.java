package com.zsc.study.kafka.product;

import com.alibaba.fastjson.JSON;
import com.zsc.study.config.ConfigUtils;
import com.zsc.study.kafka.EnumKafkaTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/27 15:06
 * @Description: kafka连接工厂（懒汉模式）
 */
public class KakfaProducerFactory {

    private static KakfaProducerFactory kakfaProducerFactory = null;

    private ExecutorService threadPool = Executors.newFixedThreadPool(5);

    private static KafkaProducer kafkaProducer;

    /**
     * 私有构造
     */
    private KakfaProducerFactory() {
        KafkaProducerConfig kafkaProducerConfig = ConfigUtils.getConfig(KafkaProducerConfig.class);
        //新建一个properties对象
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("client.id", "KafkaProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //创建生产者
        kafkaProducer = new KafkaProducer<String, String>(properties);
    }

    /**
     * 功能描述: 获取实例
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午3:25
     */
    public static KakfaProducerFactory getInstance() {

        if (kakfaProducerFactory == null) {
            synchronized (KakfaProducerFactory.class) {
                if (kakfaProducerFactory == null) {
                    kakfaProducerFactory = new KakfaProducerFactory();
                }
            }
        }
        return kakfaProducerFactory;
    }

    /**
     * 功能描述: 异步发送消息
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午3:35
     */
    public void send(final String message, final EnumKafkaTopic enumKafkaTopic) {
        threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                kafkaProducer.send(new ProducerRecord<String, String>(enumKafkaTopic.getTopic(), message));
                return 1;
            }
        });
    }


    /**
     * 功能描述: 异步发送消息
     *
     * @param:
     * @return:
     * @auther: zhangshanchuang
     * @date: 19/2/27 下午3:35
     */
    public void sendbatch(final List<String> messages, final EnumKafkaTopic enumKafkaTopic) {
        for (String s:messages) {
            threadPool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    kafkaProducer.send(new ProducerRecord<String, String>(enumKafkaTopic.getTopic(), s));
                    return 1;
                }
            });
        }
    }

}
