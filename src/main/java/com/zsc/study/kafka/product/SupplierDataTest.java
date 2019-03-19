package com.zsc.study.kafka.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsc.study.kafka.EnumKafkaTopic;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HostParams;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/2/28 15:50
 * @Description:
 */
public class SupplierDataTest {


    public static void main(String[] args) throws Exception {

        HttpClient httpClient = new HttpClient();
        KakfaProducerFactory kakfaProducerFactory = KakfaProducerFactory.getInstance();
        for (int i =0;i<10000;i++) {
            HttpMethod method = new PostMethod("http://interfacetest.homeinns.com/HomeinnsAgentApi/api/HotelInfo/CityListData");

            ((PostMethod) method).setParameter("Terminal_License","elong");
            ((PostMethod) method).setParameter("Terminal_Seq","2");
            ((PostMethod) method).setParameter("Terminal_OprId","12");

            httpClient.executeMethod(method);

            CityListDataResponse cityListDataResponse = JSONObject.parseObject(method.getResponseBodyAsString(),CityListDataResponse.class);
            List<City> list = cityListDataResponse.getCityList();
            List<String> msgs=new ArrayList<>();
            for (City city :list){
                msgs.add(JSON.toJSONString(city));
            }

           
            kakfaProducerFactory.sendbatch(msgs, EnumKafkaTopic.supplier_data_topic);

        }

    }


}
