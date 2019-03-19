package com.zsc.study.kafka.product;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: zhangshanchuang
 * @Date: 18/7/27 19:35
 * @Description:
 */
@ApiModel
public class CityListDataResponse  {

    @ApiModelProperty("返回信息集合")
    @JSONField(name = "cityList")
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "CityListDataResponse{" +
                "cityList=" + cityList +
                '}';
    }
}
