package com.zsc.study.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zsc.study.kafka.model.Detail;
import com.zsc.study.kafka.model.ResopnseMode;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/21 16:17
 * @Description:
 */
public class KafkaProductTest {

    KafkaProducer kafkaProducer;
    Properties properties;

    @Before
    public void init() {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("client.id", "KafkaProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<String, String>(properties);
    }

    @Test
    public void testSendMessage() throws Exception {
        String message = "";
        for (int i = 0; i < 100; i++) {
            ResopnseMode cityListDataResponse = this.supplierString();
            List<Detail> cities = cityListDataResponse.getChannels();
            for (Detail detail : cities) {
                message = message + detail.getName() + "\t";
            }
            System.out.println("写入MQ数据次数：" + i);

        }
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("kafka", message);
        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
        RecordMetadata recordMetadata = future.get();
        System.out.println(JSONObject.toJSON(recordMetadata));
    }

    public  ResopnseMode supplierString() throws Exception {
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new PostMethod("https://www.douban.com/j/app/radio/channels");

        httpClient.executeMethod(method);

        ResopnseMode cityListDataResponse = JSONObject.parseObject(method.getResponseBodyAsString(), ResopnseMode.class);

        return cityListDataResponse;
    }

}
